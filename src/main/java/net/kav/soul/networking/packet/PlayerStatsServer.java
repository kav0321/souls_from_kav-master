package net.kav.soul.networking.packet;

import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.kav.soul.networking.ModMessages;
import net.kav.soul.util.IEntityDataSaver;
import net.kav.soul.util.SoulData;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayNetworkHandler;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class PlayerStatsServer {




    public static void receive(MinecraftClient client , ClientPlayNetworkHandler handler, PacketByteBuf buf, PacketSender responseSender)
    {


        (((IEntityDataSaver) client.player)).getPersistentData().putInt("random",buf.readInt());
      //  (((IEntityDataSaver) client.player)).getPersistentData().putInt("Intimidation_factor",buf.readInt());
        //(((IEntityDataSaver) client.player)).getPersistentData().putInt("Insanity",buf.readInt());


    }

 //   public static void receiver(MinecraftClient client , ClientPlayNetworkHandler handler, PacketByteBuf buf, PacketSender responseSender)
    //    {
    //
    //
    //        SoulData.addNbtPoints(((IEntityDataSaver) client.player),1,"soul");
    //        (((IEntityDataSaver) client.player)).getPersistentData().putInt("soul",buf.readInt());
    //        client.player.sendMessage(Text.of("soul"+ ((IEntityDataSaver) client.player).getPersistentData().getInt("soul")),true);
    //
    //        //SoulData.synSoul(((IEntityDataSaver) client.player).getPersistentData().getInt("soul"), (ServerPlayerEntity) client.player);
    //    }

    public static void send(ServerPlayerEntity player, Identifier channelName,PacketByteBuf buf)
    {
        SoulData.synSoul(((IEntityDataSaver) player).getPersistentData().getInt("soul"), (ServerPlayerEntity) player);
        //SoulData.addNbtPoints(((IEntityDataSaver) player),1,"soul");
        //player.sendMessage(Text.of("soul"+ ((IEntityDataSaver) player).getPersistentData().getInt("soul")),true);
    }



}
