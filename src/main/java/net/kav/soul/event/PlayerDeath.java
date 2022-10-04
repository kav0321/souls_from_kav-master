package net.kav.soul.event;

import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.entity.event.v1.ServerPlayerEvents;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;

import net.kav.soul.util.IEntityDataSaver;
import net.kav.soul.util.SoulData;
import net.minecraft.server.network.ServerPlayerEntity;

public class PlayerDeath implements  ServerPlayerEvents.AfterRespawn{
    @Override
    public void afterRespawn(ServerPlayerEntity oldPlayer, ServerPlayerEntity newPlayer, boolean alive) {

       // ClientPlayNetworking.send(ModMessages.EXAMPLE_ID, PacketByteBufs.create());
        //ClientPlayNetworking.send(ModMessages.GAINING_SOUL_ID, PacketByteBufs.create());
    }
}
