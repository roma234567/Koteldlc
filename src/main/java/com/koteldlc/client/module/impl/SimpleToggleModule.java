package com.koteldlc.client.module.impl;

import com.koteldlc.client.gui.settings.BooleanSetting;
import com.koteldlc.client.module.Category;
import com.koteldlc.client.module.Module;

public class SimpleToggleModule extends Module {
    public SimpleToggleModule(String name, String description, Category category) {
        super(name, description, -1, category);
        register(new BooleanSetting("enabled", true));
    }
}
