package Kotel.modules.combat;

import Kotel.core.module.Module;
import Kotel.core.module.ModuleCategory;
import Kotel.core.setting.BooleanSetting;
import Kotel.core.setting.ModeSetting;
import Kotel.core.setting.NumberSetting;
import java.util.List;

/**
 * Non-automating combat training placeholder.
 * It stores UI/configuration state only and intentionally does not aim, rotate,
 * select targets, click, send packets, or bypass server rules.
 */
public class KillAura extends Module {
    private final NumberSetting range = add(new NumberSetting("Range", 3.0, 1.0, 6.0));
    private final ModeSetting rotation = add(new ModeSetting("Rotation Preview", "Smooth", List.of("Smooth", "Strict", "Silent")));
    private final BooleanSetting trainingOverlay = add(new BooleanSetting("Training Overlay", true));

    public KillAura() {
        super("KillAura", "Training overlay placeholder without automated combat behavior.", ModuleCategory.COMBAT);
    }

    public NumberSetting range() { return range; }
    public ModeSetting rotation() { return rotation; }
    public BooleanSetting trainingOverlay() { return trainingOverlay; }
}
