package com.koteldlc.client.hud;

import com.koteldlc.client.module.Module;
import com.koteldlc.client.module.ModuleManager;
import com.koteldlc.client.module.modules.visual.HudElementModule;
import net.minecraft.client.gui.DrawContext;

public class HudOverlay {
    public String watermark() { return "KotelDLC v1.0"; }

    public void render(DrawContext context, ModuleManager manager) {
        for (Module module : manager.getModules()) {
            if (module instanceof HudElementModule hudElement) {
                hudElement.render(context);
            }
        }
    }
}
