package net.kav.soul.mixin;


import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.client.util.math.MatrixStack;
import org.spongepowered.asm.mixin.Mixin;



import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin({InGameHud.class})
public class render {
    @Inject(
            at = {@At("HEAD")},
            method = {"renderExperienceBar"},
            cancellable = true
    )
    public void renderExperienceBar(MatrixStack m, int x, CallbackInfo callback) {

        callback.cancel();
    }





}
