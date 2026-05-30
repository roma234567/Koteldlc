package Kotel.core.setting;

public class NumberSetting extends Setting<Double> {
    private final double min; private final double max;
    public NumberSetting(String name, double value, double min, double max) { super(name, value); this.min = min; this.max = max; }
    @Override public void setValue(Double value) { super.setValue(Math.max(min, Math.min(max, value))); }
    public double min() { return min; } public double max() { return max; }
}
