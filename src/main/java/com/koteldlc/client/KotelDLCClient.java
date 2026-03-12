package com.koteldlc.client;

import com.koteldlc.client.config.ConfigManager;
import com.koteldlc.client.event.EventBus;
import com.koteldlc.client.gui.ClickGUI;
import com.koteldlc.client.hud.HudOverlay;
import com.koteldlc.client.keybind.KeybindManager;
import com.koteldlc.client.module.ModuleManager;
import net.fabricmc.api.ClientModInitializer;

public class KotelDLCClient implements ClientModInitializer {
    public static final String NAME = "KotelDLC";
    public static final String VERSION = "1.0";
    public static final EventBus EVENT_BUS = new EventBus();
    public static final ModuleManager MODULE_MANAGER = new ModuleManager();
    public static final ConfigManager CONFIG_MANAGER = new ConfigManager();
    public static final KeybindManager KEYBIND_MANAGER = new KeybindManager();
    public static final HudOverlay HUD_OVERLAY = new HudOverlay();
    public static final ClickGUI CLICK_GUI = new ClickGUI();

    @Override
    public void onInitializeClient() {
        MODULE_MANAGER.registerDefaults();
        KEYBIND_MANAGER.register(MODULE_MANAGER.getModules());
        CONFIG_MANAGER.load(MODULE_MANAGER);
        CLICK_GUI.rebuild(MODULE_MANAGER);
    }
}
