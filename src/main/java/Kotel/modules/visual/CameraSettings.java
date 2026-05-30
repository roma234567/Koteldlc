package Kotel.modules.visual;

import Kotel.core.module.ModuleCategory;
import Kotel.core.module.PremiumModule;

public class CameraSettings extends PremiumModule {
    public CameraSettings() {
        super("CameraSettings", "Camera FOV, bobbing, and shake preferences.", ModuleCategory.VISUAL);
        number("FOV", 90, 30, 140);
        bool("No Hurt Shake", true);
        status("Configured");
    }
}
