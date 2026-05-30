package Kotel.modules.visual;

import Kotel.core.module.ModuleCategory;
import Kotel.core.module.PremiumModule;

public class FreeCam extends PremiumModule {
    public FreeCam() {
        super("FreeCam", "Detached camera planner for screenshots and replays.", ModuleCategory.VISUAL);
        number("Speed", 1.0, 0.1, 5.0);
        bool("Freeze Player Preview", true);
        status("Configured");
    }
}
