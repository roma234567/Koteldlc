package com.koteldlc.client.gui.settings;

public class SliderSetting extends Setting<Double> {
    private final double min;
    private final double max;
    private final double step;

    public SliderSetting(String name, double value, double min, double max) {
        this(name, value, min, max, 0.1);
    }

    public SliderSetting(String name, double value, double min, double max, double step) {
        super(name, value);
        this.min = min;
        this.max = max;
        this.step = Math.max(0.0001, step);
        setValue(value);
    }

    @Override
    public void setValue(Double value) {
        double clamped = Math.max(min, Math.min(max, value));
        super.setValue(clamped);
    }

    public void increment() {
        setValue(getValue() + step);
    }

    public void decrement() {
        setValue(getValue() - step);
    }

    public double getMin() { return min; }
    public double getMax() { return max; }
    public double getStep() { return step; }
}
