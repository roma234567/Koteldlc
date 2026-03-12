package com.koteldlc.client.gui;

import com.koteldlc.client.module.Module;
import com.koteldlc.client.module.ModuleManager;
import com.koteldlc.client.module.modules.visual.HudElementModule;

import java.util.ArrayList;
import java.util.List;

/**
 * Минимальный ClickGUI с двумя секциями: ESP Settings и HUD Settings.
 */
public class ClickGUI {
    private final List<Panel> panels = new ArrayList<>();
    private HudElementModule draggingHud;
    private int dragHudOffsetX;
    private int dragHudOffsetY;

    public void rebuild(ModuleManager moduleManager) {
        panels.clear();

        Panel espSettings = new Panel("ESP Settings", 20, 20);
        Panel hudSettings = new Panel("HUD Settings", 160, 20);

        for (Module module : moduleManager.getModules()) {
            if (module.getName().endsWith("ESP")) {
                espSettings.getButtons().add(new ModuleButton(module));
            }
            if (module instanceof HudElementModule) {
                hudSettings.getButtons().add(new ModuleButton(module));
            }
        }

        panels.add(espSettings);
        panels.add(hudSettings);
    }

    public void beginHudDrag(HudElementModule element, int mouseX, int mouseY) {
        draggingHud = element;
        dragHudOffsetX = mouseX - element.getX();
        dragHudOffsetY = mouseY - element.getY();
    }

    public void onMouseDrag(int mouseX, int mouseY) {
        if (draggingHud != null) {
            draggingHud.setPosition(mouseX - dragHudOffsetX, mouseY - dragHudOffsetY);
        }
    }

    public void endHudDrag() {
        draggingHud = null;
    }

    public List<Panel> getPanels() {
        return panels;
    }
}
