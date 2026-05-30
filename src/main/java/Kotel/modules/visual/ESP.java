package Kotel.modules.visual;

import Kotel.core.module.ModuleCategory;
import Kotel.core.module.PremiumModule;

public class ESP extends PremiumModule {
    public ESP() {
        super("ESP", "Entity overlay style manager.", ModuleCategory.VISUAL);
        mode("Style", "Box", "Box", "Glow", "Corner");
        bool("Names", true);
        status("Configured");
    }
}
