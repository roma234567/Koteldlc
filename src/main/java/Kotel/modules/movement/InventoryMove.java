package Kotel.modules.movement;

import Kotel.core.module.ModuleCategory;
import Kotel.core.module.PremiumModule;

public class InventoryMove extends PremiumModule {
    public InventoryMove() {
        super("InventoryMove", "Input-state display for inventory movement practice.", ModuleCategory.MOVEMENT);
        bool("Allow Jump Key", true);
        bool("Show Keys", true);
        status("Configured");
    }
}
