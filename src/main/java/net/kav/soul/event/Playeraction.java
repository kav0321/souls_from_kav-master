package net.kav.soul.event;

import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ActionResult;

public interface Playeraction{
    int x1 = 0;
    int y = 0;




       /* if(MinecraftClient.getInstance().player.handSwinging)
        {

            ItemStack stack = new ItemStack(MinecraftClient.getInstance().player.getActiveItem().getItem());
            float xs = stack.getAttributeModifiers(EquipmentSlot.MAINHAND).get(EntityAttributes.GENERIC_ATTACK_SPEED).size();
            SoulData.removeFloatpoint(((IEntityDataSaver) MinecraftClient.getInstance().player), xs, "Stamina");

            PacketByteBuf buf= PacketByteBufs.create();
            buf.writeFloat(xs);
            ClientPlayNetworking.send(HANDSWING,buf);

        }*/
        Event<Playeraction> EVENT = EventFactory.createArrayBacked(Playeraction.class,
                (listeners) -> (player) -> {
                    for (Playeraction listener : listeners) {
                        ActionResult result = listener.swing(player);

                        if(result != ActionResult.PASS) {
                            return result;
                        }
                    }

                    return ActionResult.PASS;
                });

        ActionResult swing(PlayerEntity player);






}
