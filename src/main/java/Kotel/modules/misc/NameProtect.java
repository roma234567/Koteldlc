package Kotel.modules.misc;

import Kotel.core.module.ModuleCategory;
import Kotel.core.module.PremiumModule;

public class NameProtect extends PremiumModule {
    public NameProtect() {
        super("NameProtect", "Nickname masking for streams and screenshots.", ModuleCategory.MISC);
        bool("Protect Self", true);
        bool("Protect Friends", true);
        status("Configured");
    }
}
