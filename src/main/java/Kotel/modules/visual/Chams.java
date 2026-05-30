package Kotel.modules.visual;

import Kotel.core.module.ModuleCategory;
import Kotel.core.module.PremiumModule;

public class Chams extends PremiumModule {
    public Chams() {
        super("Chams", "Entity material preview and color configuration.", ModuleCategory.VISUAL);
        mode("Material", "Flat", "Flat", "Glass", "Pulse");
        bool("Through Walls Preview", false);
        status("Configured");
    }
}
