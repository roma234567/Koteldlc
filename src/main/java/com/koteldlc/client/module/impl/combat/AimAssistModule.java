package com.koteldlc.client.module.impl.combat;

import com.koteldlc.client.config.modules.BypassProfileRepository;
import com.koteldlc.client.gui.settings.BooleanSetting;
import com.koteldlc.client.gui.settings.ModeSetting;
import com.koteldlc.client.gui.settings.SliderSetting;
import com.koteldlc.client.module.Category;
import com.koteldlc.client.module.Module;
import com.koteldlc.client.utils.RotationUtils;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;

import java.util.Comparator;
import java.util.List;

public class AimAssistModule extends Module {
    private final SliderSetting horizontalSpeed;
    private final SliderSetting verticalSpeed;
    private final SliderSetting maxAngle;
    private final SliderSetting range;
    private final BooleanSetting throughWalls;
    private final BooleanSetting onlyOnClick;
    private final BooleanSetting predictMovement;
    private final BooleanSetting silentAim;
    private final ModeSetting targetMode;
    private final SliderSetting randomDelay;

    private float silentYaw;
    private float silentPitch;
    private boolean silentReady;
    private long lastAimAt;

    public AimAssistModule(BypassProfileRepository repository) {
        super("AIM ASSIST", "Плавный assist-aim под PvP", -1, Category.COMBAT);
        horizontalSpeed = register(new SliderSetting("horizontalSpeed", 0.15, 0.05, 0.5, 0.01));
        verticalSpeed = register(new SliderSetting("verticalSpeed", 0.10, 0.05, 0.5, 0.01));
        maxAngle = register(new SliderSetting("maxAngle", 45.0, 15.0, 180.0, 1.0));
        range = register(new SliderSetting("range", 4.5, 3.0, 6.0, 0.1));
        throughWalls = register(new BooleanSetting("throughWalls", false));
        onlyOnClick = register(new BooleanSetting("onlyOnClick", false));
        predictMovement = register(new BooleanSetting("predictMovement", false));
        silentAim = register(new BooleanSetting("silentAim", false));
        targetMode = register(new ModeSetting("targetMode", "Closest", List.of("Closest", "LowestHealth", "LowestArmor")));
        randomDelay = register(new SliderSetting("randomDelay", 0, 0, 500, 1));

        repository.registerDynamicSettings(this, "combat", "aimassist");
        repository.applyValueOverrides(this, "combat", "aimassist");
    }

    @Override
    public void onTick() {
        MinecraftClient mc = MinecraftClient.getInstance();
        if (mc.player == null || mc.world == null) return;
        if (onlyOnClick.getValue() && !mc.options.attackKey.isPressed()) return;

        PlayerEntity target = findTarget(mc);
        if (target == null) return;

        int delay = (int) randomDelay.getValue().doubleValue();
        if (delay > 0 && System.currentTimeMillis() - lastAimAt < delay) return;

        Vec3d targetPos = predictMovement.getValue() ? target.getPos().add(target.getVelocity().multiply(3)) : target.getPos();
        double dx = targetPos.x - mc.player.getX();
        double dy = (targetPos.y + target.getStandingEyeHeight()) - mc.player.getEyeY();
        double dz = targetPos.z - mc.player.getZ();

        float[] rot = RotationUtils.getRotations(dx, dy, dz);
        float nextYaw = smooth(mc.player.getYaw(), rot[0], horizontalSpeed.getValue().floatValue());
        float nextPitch = smooth(mc.player.getPitch(), rot[1], verticalSpeed.getValue().floatValue());

        if (silentAim.getValue()) {
            silentYaw = nextYaw;
            silentPitch = nextPitch;
            silentReady = true;
        } else {
            mc.player.setYaw(nextYaw);
            mc.player.setPitch(nextPitch);
        }
        lastAimAt = System.currentTimeMillis();
    }

    public boolean shouldApplySilent() {
        return isToggled() && silentAim.getValue() && silentReady;
    }

    public float getSilentYaw() {
        return silentYaw;
    }

    public float getSilentPitch() {
        return silentPitch;
    }

    public void clearSilent() {
        silentReady = false;
    }

    private PlayerEntity findTarget(MinecraftClient mc) {
        return mc.world.getPlayers().stream()
                .filter(p -> p != mc.player)
                .filter(p -> p.isAlive() && !p.isSpectator())
                .filter(p -> mc.player.distanceTo(p) <= range.getValue())
                .filter(p -> throughWalls.getValue() || mc.player.canSee(p))
                .filter(p -> angleTo(mc.player.getYaw(), mc.player.getPitch(), p) <= maxAngle.getValue())
                .min(getComparator(mc))
                .orElse(null);
    }

    private Comparator<PlayerEntity> getComparator(MinecraftClient mc) {
        return switch (targetMode.getValue()) {
            case "LowestHealth" -> Comparator.comparingDouble(PlayerEntity::getHealth);
            case "LowestArmor" -> Comparator.comparingInt(PlayerEntity::getArmor);
            default -> Comparator.comparingDouble(mc.player::distanceTo);
        };
    }

    private double angleTo(float yaw, float pitch, PlayerEntity target) {
        Vec3d delta = target.getEyePos().subtract(MinecraftClient.getInstance().player.getEyePos());
        float[] need = RotationUtils.getRotations(delta.x, delta.y, delta.z);
        float yawDiff = Math.abs(MathHelper.wrapDegrees(need[0] - yaw));
        float pitchDiff = Math.abs(MathHelper.wrapDegrees(need[1] - pitch));
        return Math.hypot(yawDiff, pitchDiff);
    }

    private float smooth(float from, float to, float speed) {
        float diff = MathHelper.wrapDegrees(to - from);
        return from + diff * speed;
    }
}
