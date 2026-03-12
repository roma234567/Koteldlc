package com.koteldlc.client.gui;

import java.util.ArrayList;
import java.util.List;

public class Panel {
    public int x;
    public int y;
    public int width = 120;
    public int height = 14;
    public boolean dragging;
    public int dragOffsetX;
    public int dragOffsetY;
    private final String title;
    private final List<ModuleButton> buttons = new ArrayList<>();

    public Panel(String title, int x, int y) {
        this.title = title;
        this.x = x;
        this.y = y;
    }

    public String getTitle() {
        return title;
    }

    public List<ModuleButton> getButtons() {
        return buttons;
    }
}
