package Kotel.modules.visual;

import Kotel.core.module.ModuleCategory;
import Kotel.core.module.PremiumModule;

public class AspectRatio extends PremiumModule {
    public AspectRatio() {
        super("AspectRatio", "Custom aspect ratio preview.", ModuleCategory.VISUAL);
        number("Ratio", 1.777, 1.0, 3.0);
        bool("Letterbox Preview", false);
        status("Configured");
    }
}
