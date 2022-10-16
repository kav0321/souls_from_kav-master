package net.kav.soul.event;

import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.kav.soul.networking.ModMessages;
import net.kav.soul.util.IEntityDataSaver;
import net.kav.soul.util.SoulData;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.network.PacketByteBuf;

public class ClientStamina implements ClientTickEvents.EndWorldTick{
    int x1 = 0;
    int y = 0;

    @Override
    public void onEndTick(ClientWorld world) {


        y++;
        x1++;
        if (x1 >= 10) {
            x1 = 0;

        }
        if (MinecraftClient.getInstance().player.handSwinging == false && x1==9) {
            PacketByteBuf buf = PacketByteBufs.create();
            //add sound


            float x = SoulData.addFloatpoint(((IEntityDataSaver) MinecraftClient.getInstance().player), 0.7f, "Stamina");
            buf.writeFloat(0.7f);

            // player.sendMessage(Text.of(Integer.toString(x1)),true);
            //player.sendMessage(Text.of("Succ"),true);

           ClientPlayNetworking.send(ModMessages.STAMINA, buf);
        }
    }
}
