package Kotel.modules.misc;

import Kotel.core.module.ModuleCategory;
import Kotel.core.module.PremiumModule;

public class Disabler extends PremiumModule {
    public Disabler() {
        super("Disabler", "Compatibility diagnostics panel; no anti-cheat bypass logic.", ModuleCategory.MISC);
        mode("Diagnostics", "Passive", "Passive", "Verbose", "Off");
        bool("Export Report", false);
        status("Configured");
    }
}
