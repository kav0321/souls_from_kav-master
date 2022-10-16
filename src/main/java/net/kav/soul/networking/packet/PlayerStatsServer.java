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
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.command.CommandOutput;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.network.ServerPlayNetworkHandler;
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


    public static void sendstats(MinecraftServer server, ServerPlayerEntity player, ServerPlayNetworkHandler handler, PacketByteBuf buf, PacketSender responseSender)
    {

        float x=buf.readFloat();
        (((IEntityDataSaver) player)).getPersistentData().putFloat("soul",x);
       // player.sendMessage(Text.of(Float.toString(x)),true);

    }

    public static void getsta(MinecraftServer server, ServerPlayerEntity player, ServerPlayNetworkHandler handler, PacketByteBuf buf, PacketSender responseSender)
    {

        float x=buf.readFloat();
        SoulData.addFloatpoint(((IEntityDataSaver) player),x,"Stamina");
        //player.sendMessage(Text.of(Float.toString(x)),true);

    }


    public static void sendswing(MinecraftServer server, ServerPlayerEntity player, ServerPlayNetworkHandler handler, PacketByteBuf buf, PacketSender responseSender)
    {

        float x=buf.readFloat();

        SoulData.removeFloatpoint(((IEntityDataSaver) player),x,"Stamina");

    }

    public static void geteffecs(MinecraftServer server, ServerPlayerEntity player, ServerPlayNetworkHandler handler, PacketByteBuf buf, PacketSender responseSender)
    {

        String xa= buf.readString();

        server.getCommandManager().execute(new ServerCommandSource(CommandOutput.DUMMY,player.getPos(),player.getRotationClient(), player.getWorld(),4,"", Text.of("k"), server,player),"effect give "+xa+" minecraft:weakness 4 3 ");
        server.getCommandManager().execute(new ServerCommandSource(CommandOutput.DUMMY,player.getPos(),player.getRotationClient(), player.getWorld(),4,"", Text.of("k"), server,player),"effect give "+xa+" minecraft:mining_fatigue 4 3");
        server.getCommandManager().execute(new ServerCommandSource(CommandOutput.DUMMY,player.getPos(),player.getRotationClient(), player.getWorld(),4,"", Text.of("k"), server,player),"effect give "+xa+" minecraft:slowness 2 10");
    }


}
