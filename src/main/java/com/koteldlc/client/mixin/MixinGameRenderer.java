package com.koteldlc.client.mixin;

import com.koteldlc.client.KotelDLCClient;
import com.koteldlc.client.module.impl.visual.ESPModule;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(net.minecraft.client.gui.hud.InGameHud.class)
public class MixinGameRenderer {
    @Inject(method = "render", at = @At("TAIL"))
    private void koteldlc$renderArrows(DrawContext context, net.minecraft.client.render.RenderTickCounter tickCounter, CallbackInfo ci) {
        if (MinecraftClient.getInstance().player == null) return;
        if (KotelDLCClient.MODULE_MANAGER.getByName("ESP") instanceof ESPModule esp) {
            esp.render2D(context);
        }
    }
}
