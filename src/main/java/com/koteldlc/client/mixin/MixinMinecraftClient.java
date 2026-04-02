package com.koteldlc.client.mixin;

import com.koteldlc.client.KotelDLCClient;
import com.koteldlc.client.module.Module;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(net.minecraft.client.MinecraftClient.class)
public class MixinMinecraftClient {
    @Inject(method = "tick", at = @At("TAIL"))
    private void koteldlc$onTick(CallbackInfo ci) {
        for (Module module : KotelDLCClient.MODULE_MANAGER.getModules()) {
            if (module.isToggled()) {
                module.onTick();
            }
        }
    }
}
