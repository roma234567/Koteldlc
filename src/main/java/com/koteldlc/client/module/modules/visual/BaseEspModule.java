package com.koteldlc.client.module.modules.visual;

import com.koteldlc.client.gui.settings.BooleanSetting;
import com.koteldlc.client.gui.settings.ColorSetting;
import com.koteldlc.client.gui.settings.SliderSetting;
import com.koteldlc.client.module.Category;
import com.koteldlc.client.module.Module;
import com.mojang.blaze3d.systems.RenderSystem;

/**
 * База для ESP-модулей. Содержит общие настройки цвета/толщины/мигания.
 */
public abstract class BaseEspModule extends Module {
    protected final ColorSetting color;
    protected final SliderSetting thickness;
    protected final BooleanSetting blink;

    protected BaseEspModule(String name, String description, int defaultColor) {
        super(name, description, -1, Category.RENDER);
        this.color = register(new ColorSetting("color", defaultColor));
        this.thickness = register(new SliderSetting("thickness", 2.0, 1.0, 6.0));
        this.blink = register(new BooleanSetting("blink", false));
    }

    public void prepareRenderState() {
        RenderSystem.enableBlend();
        RenderSystem.defaultBlendFunc();
        RenderSystem.lineWidth((float) thickness.getValue().doubleValue());
    }
}
