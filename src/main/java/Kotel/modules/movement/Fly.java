package Kotel.modules.movement;

import Kotel.core.module.ModuleCategory;
import Kotel.core.module.PremiumModule;

public class Fly extends PremiumModule {
    public Fly() {
        super("Fly", "Creative-style flight controls for local/test environments.", ModuleCategory.MOVEMENT);
        number("Vertical Speed", 0.8, 0.1, 5.0);
        number("Horizontal Speed", 1.2, 0.1, 5.0);
        bool("Glide Down", true);
        status("Configured");
    }
}
