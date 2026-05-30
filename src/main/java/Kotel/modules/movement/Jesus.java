package Kotel.modules.movement;

import Kotel.core.module.ModuleCategory;
import Kotel.core.module.PremiumModule;

public class Jesus extends PremiumModule {
    public Jesus() {
        super("Jesus", "Water-edge movement indicator and liquid surface helper.", ModuleCategory.MOVEMENT);
        bool("Show Liquid Edges", true);
        mode("Surface", "Water", "Water", "Lava", "Both");
        status("Configured");
    }
}
