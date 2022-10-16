package net.kav.soul.mixin;

import net.kav.soul.event.Miss;
import net.kav.soul.event.Playeraction;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LivingEntity.class)
public class Playerswingmiss {

    @Inject(at = @At("HEAD"), method = "swingHand(Lnet/minecraft/util/Hand;Z)V", cancellable = true)
    private void attack(Hand hand, boolean fromServerPlayer, CallbackInfo info) {
        ActionResult result;
        if((LivingEntity) (Object) this instanceof PlayerEntity)
        {
            result = Miss.Miss.invoker().Miss((PlayerEntity) (Object) this);
        }
        else
        {
            result= ActionResult.FAIL;
        }

        if(result == ActionResult.FAIL) {
            info.cancel();
        }
    }
}
