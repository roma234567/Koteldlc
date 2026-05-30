package Kotel.modules.visual;

import Kotel.core.module.ModuleCategory;
import Kotel.core.module.PremiumModule;

public class SeeInvisible extends PremiumModule {
    public SeeInvisible() {
        super("SeeInvisible", "Invisible-entity outline preference for permitted contexts.", ModuleCategory.VISUAL);
        mode("Display", "Silhouette", "Silhouette", "Name Only", "Glow");
        number("Alpha", 0.35, 0.0, 1.0);
        status("Configured");
    }
}
