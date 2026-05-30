package Kotel.modules.visual;

import Kotel.core.module.ModuleCategory;
import Kotel.core.module.PremiumModule;

public class ViewModel extends PremiumModule {
    public ViewModel() {
        super("ViewModel", "Hand position, scale, and tilt profile.", ModuleCategory.VISUAL);
        number("X", 0, -2, 2);
        number("Y", 0, -2, 2);
        number("Scale", 1, 0.2, 2);
        status("Configured");
    }
}
