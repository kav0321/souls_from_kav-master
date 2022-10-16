package net.kav.soul.event;

import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.kav.soul.data.ItemType;
import net.kav.soul.networking.ModMessages;
import net.kav.soul.util.GlobalSoul;
import net.kav.soul.util.IEntityDataSaver;
import net.kav.soul.util.SoulData;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;

import java.util.List;

public class MissOveride implements Miss{
    @Override
    public ActionResult Miss(Object player) {
        int x12=0;
        String xereca;

        if(player instanceof PlayerEntity)

        {

       x12=GlobalSoul.getItemtype();

            float x= SoulData.removeFloatpoint(((IEntityDataSaver) player),x12,"Stamina");
        PacketByteBuf buf = PacketByteBufs.create();
        buf.writeFloat(x);


        GlobalSoul.setStamina(x);
        ClientPlayNetworking.send(ModMessages.HANDSWING, buf);
        return ActionResult.PASS;
        }
       else
           return ActionResult.FAIL;
    }


}
