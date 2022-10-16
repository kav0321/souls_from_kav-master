package net.kav.soul.mixin;


import net.kav.soul.event.PlayerBlock;
import net.kav.soul.event.Playeraction;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(PlayerEntity.class)
public class swingweaponmixin {



   // @Inject(at = @At("HEAD"), method = "attack()V", cancellable = true)
    //    private void attack(Entity target, CallbackInfo info) {
    //        ActionResult result = Playeraction.EVENT.invoker().swing((PlayerEntity) (Object) this);
    //
    //        if(result == ActionResult.FAIL) {
    //            info.cancel();
    //        }
    //    }
}
