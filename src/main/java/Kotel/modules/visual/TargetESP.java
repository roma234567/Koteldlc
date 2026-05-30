package Kotel.modules.visual;

import Kotel.core.module.ModuleCategory;
import Kotel.core.module.PremiumModule;

public class TargetESP extends PremiumModule {
    public TargetESP() {
        super("TargetESP", "Target marker and ring renderer configuration.", ModuleCategory.VISUAL);
        mode("Marker", "Ring", "Ring", "Arrow", "Ghost");
        number("Scale", 1.0, 0.2, 3.0);
        status("Configured");
    }
}
