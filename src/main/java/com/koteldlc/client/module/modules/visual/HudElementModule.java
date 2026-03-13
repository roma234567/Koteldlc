package com.koteldlc.client.module.modules.visual;

import com.koteldlc.client.gui.settings.BooleanSetting;
import com.koteldlc.client.gui.settings.ColorSetting;
import com.koteldlc.client.gui.settings.ModeSetting;
import com.koteldlc.client.gui.settings.SliderSetting;
import com.koteldlc.client.module.Category;
import com.koteldlc.client.module.Module;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;

import java.util.List;

/**
 * Базовый HUD-элемент с настройками позиции, цвета, тени и шрифта.
 */
public abstract class HudElementModule extends Module {
    private final SliderSetting x = register(new SliderSetting("x", 8, 0, 4000));
    private final SliderSetting y = register(new SliderSetting("y", 8, 0, 4000));
    private final BooleanSetting visible = register(new BooleanSetting("visible", true));
    private final ColorSetting color = register(new ColorSetting("color", 0xFFFFFFFF));
    private final BooleanSetting shadow = register(new BooleanSetting("shadow", true));
    private final ModeSetting font = register(new ModeSetting("font", "Default", List.of("Default", "Mono", "Bold")));

    protected HudElementModule(String name, String description, int defaultX, int defaultY) {
        super(name, description, -1, Category.HUD);
        x.setValue((double) defaultX);
        y.setValue((double) defaultY);
    }

    public void render(DrawContext context) {
        if (!isToggled() || !visible.getValue()) {
            return;
        }
        String value = buildText();
        MinecraftClient client = MinecraftClient.getInstance();
        if (client == null || client.textRenderer == null) {
            return;
        }
        context.drawText(client.textRenderer, value, getX(), getY(), color.getValue(), shadow.getValue());
    }

    protected abstract String buildText();

    public int getX() { return (int) Math.round(x.getValue()); }
    public int getY() { return (int) Math.round(y.getValue()); }
    public void setPosition(int newX, int newY) {
        x.setValue((double) Math.max(0, newX));
        y.setValue((double) Math.max(0, newY));
    }

    public String getFont() {
        return font.getValue();
    }
}
