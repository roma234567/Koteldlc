package Kotel.modules.visual;

import Kotel.core.module.ModuleCategory;
import Kotel.core.module.PremiumModule;

public class KillEffect extends PremiumModule {
    public KillEffect() {
        super("KillEffect", "Celebration effect preset after confirmed eliminations.", ModuleCategory.VISUAL);
        mode("Effect", "Bloom", "Bloom", "Lightning", "Spark");
        bool("Sound Cue", true);
        status("Configured");
    }
}
