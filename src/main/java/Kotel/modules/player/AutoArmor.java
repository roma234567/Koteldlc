package Kotel.modules.player;

import Kotel.core.module.ModuleCategory;
import Kotel.core.module.PremiumModule;

public class AutoArmor extends PremiumModule {
    public AutoArmor() {
        super("AutoArmor", "Armor comparison overlay and equip recommendation list.", ModuleCategory.PLAYER);
        mode("Priority", "Protection", "Protection", "Durability", "Balanced");
        bool("Manual Confirm", true);
        status("Configured");
    }
}
