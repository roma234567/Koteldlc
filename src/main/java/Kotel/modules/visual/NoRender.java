package Kotel.modules.visual;

import Kotel.core.module.ModuleCategory;
import Kotel.core.module.PremiumModule;

public class NoRender extends PremiumModule {
    public NoRender() {
        super("NoRender", "Visual clutter filter toggles.", ModuleCategory.VISUAL);
        bool("Fire", true);
        bool("Pumpkin", true);
        bool("Particles", false);
        status("Configured");
    }
}
