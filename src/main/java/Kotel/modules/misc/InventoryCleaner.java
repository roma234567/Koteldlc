package Kotel.modules.misc;

import Kotel.core.module.ModuleCategory;
import Kotel.core.module.PremiumModule;

public class InventoryCleaner extends PremiumModule {
    public InventoryCleaner() {
        super("InventoryCleaner", "Inventory organization checklist and slot planner.", ModuleCategory.MISC);
        number("Keep Slots", 9, 0, 36);
        bool("Confirm Drops", true);
        status("Configured");
    }
}
