package com.koteldlc.client.gui;

import com.koteldlc.client.module.Category;
import com.koteldlc.client.module.Module;
import com.koteldlc.client.module.ModuleManager;
import com.koteldlc.client.module.modules.visual.HudElementModule;

import java.util.ArrayList;
import java.util.List;

/**
 * ClickGUI с панелями по категориям + отдельная панель HUD.
 */
public class ClickGUI {
    private final List<Panel> panels = new ArrayList<>();
    private HudElementModule draggingHud;
    private int dragHudOffsetX;
    private int dragHudOffsetY;

    public void rebuild(ModuleManager moduleManager) {
        panels.clear();

        int x = 20;
        int y = 20;
        for (Category category : Category.values()) {
            Panel panel = new Panel(formatCategory(category), x, y);
            for (Module module : moduleManager.getModules()) {
                if (module.getCategory() == category) {
                    panel.getButtons().add(new ModuleButton(module));
                }
            }
            if (!panel.getButtons().isEmpty()) {
                panels.add(panel);
                x += 140;
            }
        }

        Panel hudSettings = new Panel("HUD", 20, 220);
        for (Module module : moduleManager.getModules()) {
            if (module instanceof HudElementModule) {
                hudSettings.getButtons().add(new ModuleButton(module));
            }
        }
        if (!hudSettings.getButtons().isEmpty()) {
            panels.add(hudSettings);
        }
    }

    private String formatCategory(Category category) {
        return switch (category) {
            case COMBAT -> "Combat";
            case MOVEMENT -> "Movement";
            case RENDER -> "Visuals";
            case HUD -> "HUD";
            case PLAYER -> "Player";
            case MISC -> "Miscellaneous";
        };
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
