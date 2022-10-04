package net.kav.soul.networking.packet;

import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.kav.soul.util.IEntityDataSaver;
import net.kav.soul.util.SoulData;
import net.minecraft.entity.LivingEntity;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayNetworkHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;

public class PlayerStatsClient {

    private static int Health_of_dead_entity;


public static void receive(MinecraftServer server, ServerPlayerEntity player, ServerPlayNetworkHandler handler, PacketByteBuf buf, PacketSender responseSender)
{


    SoulData.addNbtPoints(((IEntityDataSaver) player),1,"soul");
    SoulData.addNbtPoints(((IEntityDataSaver) player),0,"Intimidation_factor");
    SoulData.addNbtPoints(((IEntityDataSaver) player),0,"Insanity");

    player.sendMessage(Text.of("soul"+ ((IEntityDataSaver) player).getPersistentData().getInt("soul")),true);
    SoulData.synSoul(((IEntityDataSaver) player).getPersistentData().getInt("soul"), player);
}

    public static int GET_HEALTH_OF_DEAD_ENTITY(LivingEntity entity)
    {
        Health_of_dead_entity = (int) entity.getMaxHealth();
        return Health_of_dead_entity;
    }





}
