package com.koteldlc.client.gui;

import com.koteldlc.client.module.Module;

/**
 * Базовая кнопка модуля в ClickGUI.
 * ЛКМ — переключение модуля, ПКМ — открытие/закрытие меню его настроек.
 */
public class ModuleButton {
    private final Module module;
    private boolean settingsOpen;

    public ModuleButton(Module module) {
        this.module = module;
    }

    public void onLeftClick() {
        module.toggle();
    }

    public void onRightClick() {
        settingsOpen = !settingsOpen;
    }

    public Module getModule() {
        return module;
    }

    public boolean isSettingsOpen() {
        return settingsOpen;
    }
}
