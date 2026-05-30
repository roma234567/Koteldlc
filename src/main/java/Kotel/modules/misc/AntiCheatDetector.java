package Kotel.modules.misc;

import Kotel.core.module.ModuleCategory;
import Kotel.core.module.PremiumModule;

public class AntiCheatDetector extends PremiumModule {
    public AntiCheatDetector() {
        super("AntiCheatDetector", "Server rule-set detector notes and warning display.", ModuleCategory.MISC);
        mode("Sensitivity", "Normal", "Low", "Normal", "High");
        bool("Show Banner", true);
        status("Configured");
    }
}
