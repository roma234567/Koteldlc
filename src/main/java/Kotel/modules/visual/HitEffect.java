package Kotel.modules.visual;

import Kotel.core.module.ModuleCategory;
import Kotel.core.module.PremiumModule;

public class HitEffect extends PremiumModule {
    public HitEffect() {
        super("HitEffect", "Hit confirmation particles and screen accent.", ModuleCategory.VISUAL);
        mode("Effect", "Particles", "Particles", "Flash", "Combo");
        number("Amount", 12, 0, 64);
        status("Configured");
    }
}
