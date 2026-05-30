package Kotel.modules.visual;

import Kotel.core.module.ModuleCategory;
import Kotel.core.module.PremiumModule;

public class Fullbright extends PremiumModule {
    public Fullbright() {
        super("Fullbright", "Brightness and gamma profile manager.", ModuleCategory.VISUAL);
        number("Gamma", 8.0, 1.0, 16.0);
        mode("Tone", "Clean", "Clean", "Warm", "Cold");
        status("Configured");
    }
}
