package Kotel.modules.visual;

import Kotel.core.module.ModuleCategory;
import Kotel.core.module.PremiumModule;

public class CrossHair extends PremiumModule {
    public CrossHair() {
        super("CrossHair", "Custom crosshair editor.", ModuleCategory.VISUAL);
        mode("Shape", "Dot", "Dot", "Cross", "Circle");
        number("Gap", 3, 0, 16);
        status("Configured");
    }
}
