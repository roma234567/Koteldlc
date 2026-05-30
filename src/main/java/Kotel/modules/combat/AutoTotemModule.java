package Kotel.modules.combat;

import Kotel.core.module.ModuleCategory;
import Kotel.core.module.PremiumModule;

public class AutoTotemModule extends PremiumModule {
    public AutoTotemModule() {
        super("AutoTotemModule", "Offhand totem reminder and inventory priority planner.", ModuleCategory.COMBAT);
        number("Health Threshold", 8.0, 1.0, 20.0);
        bool("Show Toast", true);
        mode("Priority", "Totem First", "Totem First", "Crystal Safe", "Manual Confirm");
        status("Configured");
    }
}
