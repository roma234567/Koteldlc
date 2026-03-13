package com.koteldlc.client.gui

import com.koteldlc.client.ModuleManager
import com.koteldlc.client.modules.Category
import com.koteldlc.client.modules.Module
import net.minecraft.client.MinecraftClient
import net.minecraft.client.gui.DrawContext
import kotlin.math.min

data class ModuleButton(
    val module: Module,
    var settingsOpen: Boolean = false,
    var animation: Float = 0f
)

data class GuiPanel(
    val title: String,
    var x: Int,
    var y: Int,
    var width: Int,
    var height: Int,
    var dragging: Boolean = false,
    var dragOffsetX: Int = 0,
    var dragOffsetY: Int = 0
)

/**
 * ClickGUI в стиле референса:
 * - полупрозрачный темный фон
 * - левая колонка категорий
 * - карточки модулей
 * - ПКМ открывает settings
 * - перетаскивание окна мышью
 */
class ClickGUI(private val moduleManager: ModuleManager) {
    private val client get() = MinecraftClient.getInstance()

    val rootPanel = GuiPanel("MoonLike", 240, 120, 460, 260)
    val combatButtons = mutableListOf<ModuleButton>()
    val visualButtons = mutableListOf<ModuleButton>()
    var selectedCategory: Category = Category.COMBAT

    fun rebuild() {
        combatButtons.clear()
        visualButtons.clear()
        moduleManager.all().forEach { module ->
            val button = ModuleButton(module)
            when (module.category) {
                Category.COMBAT -> combatButtons += button
                Category.VISUAL -> visualButtons += button
            }
        }
    }

    fun render(context: DrawContext, mouseX: Int, mouseY: Int, delta: Float) {
        val text = client.textRenderer ?: return

        // Backdrop + panel (как на скрине: глубокий полупрозрачный черный)
        context.fill(0, 0, client.window.scaledWidth, client.window.scaledHeight, 0x66000000)
        drawRoundedRect(context, rootPanel.x, rootPanel.y, rootPanel.width, rootPanel.height, 0xCC111417.toInt())
        drawRoundedRect(context, rootPanel.x + 8, rootPanel.y + 8, 92, rootPanel.height - 16, 0xB014171A.toInt())

        // Лого/заголовок
        context.drawText(text, "☾", rootPanel.x + 20, rootPanel.y + 14, 0xFF2CC9C9.toInt(), true)
        context.drawText(text, "combat", rootPanel.x + 20, rootPanel.y + 52, catColor(Category.COMBAT), true)
        context.drawText(text, "visual", rootPanel.x + 20, rootPanel.y + 70, catColor(Category.VISUAL), true)

        val buttons = activeButtons()
        val leftX = rootPanel.x + 112
        val topY = rootPanel.y + 16
        val colWidth = (rootPanel.width - 130) / 2

        // Две колонки карточек модулей
        buttons.forEachIndexed { index, btn ->
            val col = index % 2
            val row = index / 2
            val cardX = leftX + col * (colWidth + 8)
            val cardY = topY + row * 36
            val cardH = if (btn.settingsOpen) 64 else 30

            val target = if (btn.module.enabled) 1f else 0f
            btn.animation += (target - btn.animation) * min(1f, delta * 0.22f)
            val pulse = (24 * btn.animation).toInt()
            val cardColor = 0xC0191D22 + (pulse shl 8)

            drawRoundedRect(context, cardX, cardY, colWidth, cardH, cardColor)
            context.drawText(text, btn.module.name, cardX + 8, cardY + 8, 0xFFE8ECEF.toInt(), false)
            context.drawText(text, if (btn.module.enabled) "ON" else "OFF", cardX + colWidth - 22, cardY + 8, 0xFF9AB0BC.toInt(), false)

            if (btn.settingsOpen) {
                val summary = btn.module.settings.entries.take(2).joinToString(" • ") { "${it.key}:${it.value}" }
                context.drawText(text, summary, cardX + 8, cardY + 24, 0xFF9BA7B0.toInt(), false)
                context.drawText(text, "RMB: close settings", cardX + 8, cardY + 38, 0xFF6E7A83.toInt(), false)
            }
        }
    }

    fun mouseClicked(mouseX: Double, mouseY: Double, button: Int): Boolean {
        if (isInside(rootPanel.x, rootPanel.y, rootPanel.width, 20, mouseX, mouseY)) {
            if (button == 0) {
                rootPanel.dragging = true
                rootPanel.dragOffsetX = (mouseX - rootPanel.x).toInt()
                rootPanel.dragOffsetY = (mouseY - rootPanel.y).toInt()
                return true
            }
        }

        // category clicks
        if (isInside(rootPanel.x + 18, rootPanel.y + 50, 64, 14, mouseX, mouseY) && button == 0) {
            selectedCategory = Category.COMBAT
            return true
        }
        if (isInside(rootPanel.x + 18, rootPanel.y + 68, 64, 14, mouseX, mouseY) && button == 0) {
            selectedCategory = Category.VISUAL
            return true
        }

        // module cards
        val buttons = activeButtons()
        val leftX = rootPanel.x + 112
        val topY = rootPanel.y + 16
        val colWidth = (rootPanel.width - 130) / 2

        buttons.forEachIndexed { index, btn ->
            val col = index % 2
            val row = index / 2
            val cardX = leftX + col * (colWidth + 8)
            val cardY = topY + row * 36
            val cardH = if (btn.settingsOpen) 64 else 30
            if (isInside(cardX, cardY, colWidth, cardH, mouseX, mouseY)) {
                when (button) {
                    0 -> btn.module.toggle()
                    1 -> btn.settingsOpen = !btn.settingsOpen
                }
                return true
            }
        }

        return false
    }

    fun mouseDragged(mouseX: Double, mouseY: Double): Boolean {
        if (!rootPanel.dragging) return false
        val maxX = client.window.scaledWidth - rootPanel.width
        val maxY = client.window.scaledHeight - rootPanel.height
        rootPanel.x = (mouseX - rootPanel.dragOffsetX).toInt().coerceIn(0, maxX)
        rootPanel.y = (mouseY - rootPanel.dragOffsetY).toInt().coerceIn(0, maxY)
        return true
    }

    fun resetLayout() {
        rootPanel.x = 240
        rootPanel.y = 120
        selectedCategory = Category.COMBAT
        (combatButtons + visualButtons).forEach { it.settingsOpen = false }
    }

    fun mouseReleased(): Boolean {
        val wasDragging = rootPanel.dragging
        rootPanel.dragging = false
        return wasDragging
    }

    private fun activeButtons(): List<ModuleButton> = when (selectedCategory) {
        Category.COMBAT -> combatButtons
        Category.VISUAL -> visualButtons
    }

    private fun catColor(category: Category): Int {
        val active = selectedCategory == category
        return if (active) 0xFF2CC9C9.toInt() else 0xFF727A84.toInt()
    }

    private fun isInside(x: Int, y: Int, w: Int, h: Int, mx: Double, my: Double): Boolean {
        return mx >= x && my >= y && mx <= x + w && my <= y + h
    }

    private fun drawRoundedRect(context: DrawContext, x: Int, y: Int, w: Int, h: Int, color: Int) {
        // DrawContext не дает нативный rounded rect без custom shader;
        // имитируем через прямоугольники и скругленные углы 2px.
        context.fill(x + 2, y, x + w - 2, y + h, color)
        context.fill(x, y + 2, x + w, y + h - 2, color)
    }
}
