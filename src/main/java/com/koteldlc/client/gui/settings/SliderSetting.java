package com.koteldlc.client.gui.settings;

public class SliderSetting extends Setting<Double> {
    private final double min;
    private final double max;

    public SliderSetting(String name, double value, double min, double max) {
        super(name, value);
        this.min = min;
        this.max = max;
    }

    public double getMin() { return min; }
    public double getMax() { return max; }
}
