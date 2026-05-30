package Kotel.modules.player;

import Kotel.core.module.ModuleCategory;
import Kotel.core.module.PremiumModule;

public class AutoTool extends PremiumModule {
    public AutoTool() {
        super("AutoTool", "Tool ranking overlay for the selected block.", ModuleCategory.PLAYER);
        mode("Priority", "Speed", "Speed", "Durability", "Silk Touch");
        bool("Show Hint", true);
        status("Configured");
    }
}
