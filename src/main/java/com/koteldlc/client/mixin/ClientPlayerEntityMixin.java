package com.koteldlc.client.mixin;

import com.koteldlc.client.KotelDLCClient;
import net.minecraft.client.network.ClientPlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ClientPlayerEntity.class)
public class ClientPlayerEntityMixin {

    @Inject(method = "sendChatMessage", at = @At("HEAD"), cancellable = true)
    private void koteldlc$handleClientCommands(String message, CallbackInfo ci) {
        if (KotelDLCClient.COMMAND_MANAGER.handle(message)) {
            ci.cancel();
        }
    }
}
