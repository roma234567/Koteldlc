package Kotel.modules.visual;

import Kotel.core.module.ModuleCategory;
import Kotel.core.module.PremiumModule;

public class Arrows extends PremiumModule {
    public Arrows() {
        super("Arrows", "Off-screen entity arrows.", ModuleCategory.VISUAL);
        number("Radius", 42, 10, 120);
        bool("Distance Text", true);
        status("Configured");
    }
}
