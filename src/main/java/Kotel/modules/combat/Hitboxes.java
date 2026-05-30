package Kotel.modules.combat;

import Kotel.core.module.ModuleCategory;
import Kotel.core.module.PremiumModule;

public class Hitboxes extends PremiumModule {
    public Hitboxes() {
        super("Hitboxes", "Client-side hitbox outline and reach-training visualizer.", ModuleCategory.COMBAT);
        number("Outline Size", 0.12, 0.0, 1.0);
        bool("Only Players", true);
        mode("Overlay", "Glow", "Glow", "Box", "Corner");
        status("Configured");
    }
}
