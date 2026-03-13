package com.koteldlc.client.gui

import net.minecraft.client.MinecraftClient
import net.minecraft.client.gui.DrawContext

data class HudWidget(
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
 * HUD в стиле референса:
 * - отдельные полупрозрачные блоки Potions/Staffs/Keybinds
 * - центральная watermark карточка
 * - перетаскивание каждого блока мышью
 */
class HudOverlay {
    private val client get() = MinecraftClient.getInstance()

    val potions = HudWidget("Potions", 96, 180, 120, 82)
    val staffs = HudWidget("Staffs", 490, 180, 120, 82)
    val keybinds = HudWidget("Keybinds", 542, 370, 120, 72)
    val watermark = HudWidget("Watermark", 340, 270, 106, 22)

    fun render(context: DrawContext) {
        val tr = client.textRenderer ?: return

        renderBlock(context, potions, listOf("Regeneration 2      1:04", "Strength 3          0:24", "Poison              0:01"))
        renderBlock(context, staffs, listOf("lineByDodge Helper   14:25", "K0R23M9R Booster     0:30", "Global Timer         4:19"))
        renderBlock(context, keybinds, listOf("HitBoxes             H", "KillAura             R", "Speed                V"))

        drawRounded(context, watermark.x, watermark.y, watermark.width, watermark.height, 0xCC111417.toInt())
        context.drawText(tr, "☀", watermark.x + 6, watermark.y + 7, 0xFFFFCC4D.toInt(), false)
        context.drawText(tr, "LineByDodge", watermark.x + 18, watermark.y + 7, 0xFFE8ECEF.toInt(), false)
    }

    fun mouseClicked(mouseX: Double, mouseY: Double, button: Int): Boolean {
        if (button != 0) return false
        for (widget in widgets()) {
            if (inside(widget, mouseX, mouseY)) {
                widget.dragging = true
                widget.dragOffsetX = (mouseX - widget.x).toInt()
                widget.dragOffsetY = (mouseY - widget.y).toInt()
                return true
            }
        }
        return false
    }

    fun mouseDragged(mouseX: Double, mouseY: Double): Boolean {
        var dragged = false
        val maxW = client.window.scaledWidth
        val maxH = client.window.scaledHeight
        for (widget in widgets()) {
            if (!widget.dragging) continue
            val maxX = (maxW - widget.width).coerceAtLeast(0)
            val maxY = (maxH - widget.height).coerceAtLeast(0)
            widget.x = (mouseX - widget.dragOffsetX).toInt().coerceIn(0, maxX)
            widget.y = (mouseY - widget.dragOffsetY).toInt().coerceIn(0, maxY)
            dragged = true
        }
        return dragged
    }

    fun resetPositions() {
        potions.x = 96; potions.y = 180
        staffs.x = 490; staffs.y = 180
        keybinds.x = 542; keybinds.y = 370
        watermark.x = 340; watermark.y = 270
    }

    fun mouseReleased(): Boolean {
        var changed = false
        for (widget in widgets()) {
            if (widget.dragging) {
                widget.dragging = false
                changed = true
            }
        }
        return changed
    }

    fun savePositions(): Map<String, Pair<Int, Int>> = mapOf(
        "potions" to (potions.x to potions.y),
        "staffs" to (staffs.x to staffs.y),
        "keybinds" to (keybinds.x to keybinds.y),
        "watermark" to (watermark.x to watermark.y)
    )

    fun loadPositions(data: Map<String, Pair<Int, Int>>) {
        data["potions"]?.let { potions.x = it.first; potions.y = it.second }
        data["staffs"]?.let { staffs.x = it.first; staffs.y = it.second }
        data["keybinds"]?.let { keybinds.x = it.first; keybinds.y = it.second }
        data["watermark"]?.let { watermark.x = it.first; watermark.y = it.second }
    }

    private fun widgets() = listOf(potions, staffs, keybinds, watermark)

    private fun renderBlock(context: DrawContext, widget: HudWidget, lines: List<String>) {
        val tr = client.textRenderer ?: return
        drawRounded(context, widget.x, widget.y, widget.width, widget.height, 0xCC111417.toInt())
        context.drawText(tr, widget.title, widget.x + 8, widget.y + 7, 0xFFE8ECEF.toInt(), false)
        var y = widget.y + 22
        for (line in lines) {
            context.drawText(tr, "• $line", widget.x + 8, y, 0xFFB7C1C9.toInt(), false)
            y += 12
            if (y > widget.y + widget.height - 8) break
        }
    }

    private fun inside(widget: HudWidget, mx: Double, my: Double): Boolean {
        return mx >= widget.x && my >= widget.y && mx <= widget.x + widget.width && my <= widget.y + widget.height
    }

    private fun drawRounded(context: DrawContext, x: Int, y: Int, w: Int, h: Int, color: Int) {
        context.fill(x + 2, y, x + w - 2, y + h, color)
        context.fill(x, y + 2, x + w, y + h - 2, color)
    }
}
