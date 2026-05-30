package Kotel.modules.visual;

import Kotel.core.module.ModuleCategory;
import Kotel.core.module.PremiumModule;

public class BlockESP extends PremiumModule {
    public BlockESP() {
        super("BlockESP", "Block highlight list and colors.", ModuleCategory.VISUAL);
        mode("Block Set", "Ores", "Ores", "Containers", "Custom");
        bool("Tracers", false);
        status("Configured");
    }
}
