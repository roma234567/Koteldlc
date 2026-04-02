package com.koteldlc.client.mixin;

import com.koteldlc.client.KotelDLCClient;
import com.koteldlc.client.module.impl.visual.ESPModule;
import net.minecraft.client.util.math.MatrixStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(net.minecraft.client.render.WorldRenderer.class)
public class MixinWorldRenderer {
    @Inject(method = "render", at = @At("TAIL"))
    private void koteldlc$renderESP(CallbackInfo ci) {
        if (KotelDLCClient.MODULE_MANAGER.getByName("ESP") instanceof ESPModule esp && esp.isToggled()) {
            esp.renderWorld(new MatrixStack());
        }
    }
}
