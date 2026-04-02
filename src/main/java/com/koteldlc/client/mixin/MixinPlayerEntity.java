package com.koteldlc.client.mixin;

import com.koteldlc.client.KotelDLCClient;
import com.koteldlc.client.module.impl.combat.AimAssistModule;
import net.minecraft.client.network.ClientPlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ClientPlayerEntity.class)
public class MixinPlayerEntity {
    @Unique private float oldYaw;
    @Unique private float oldPitch;

    @Inject(method = "sendMovementPackets", at = @At("HEAD"))
    private void koteldlc$applySilentRotation(CallbackInfo ci) {
        if (!(KotelDLCClient.MODULE_MANAGER.getByName("AIM ASSIST") instanceof AimAssistModule aim) || !aim.shouldApplySilent()) {
            return;
        }
        ClientPlayerEntity self = (ClientPlayerEntity) (Object) this;
        oldYaw = self.getYaw();
        oldPitch = self.getPitch();
        self.setYaw(aim.getSilentYaw());
        self.setPitch(aim.getSilentPitch());
    }

    @Inject(method = "sendMovementPackets", at = @At("TAIL"))
    private void koteldlc$restoreSilentRotation(CallbackInfo ci) {
        if (!(KotelDLCClient.MODULE_MANAGER.getByName("AIM ASSIST") instanceof AimAssistModule aim) || !aim.shouldApplySilent()) {
            return;
        }
        ClientPlayerEntity self = (ClientPlayerEntity) (Object) this;
        self.setYaw(oldYaw);
        self.setPitch(oldPitch);
        aim.clearSilent();
    }
}
