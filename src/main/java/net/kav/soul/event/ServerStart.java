package net.kav.soul.event;

import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerChunkEvents;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.fabricmc.fabric.api.networking.v1.*;
//import net.kav.soul.networking.ModMessages;
import net.kav.soul.networking.ModMessages;
import net.kav.soul.util.IEntityDataSaver;
import net.kav.soul.util.SoulData;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.resource.LifecycledResourceManager;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayNetworkHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.world.chunk.WorldChunk;

import static net.kav.soul.event.EntityDeath.getSoulincrease;

public class ServerStart implements ServerLifecycleEvents.StartDataPackReload{



    @Override
    public void startDataPackReload(MinecraftServer server, LifecycledResourceManager resourceManager) {
        PacketByteBuf buf= PacketByteBufs.create();
        //add sound
        int  x=SoulData.addNbtPoints(((IEntityDataSaver) server.getPlayerManager().getPlayerList()),EntityDeath.getSoulincrease(),"soul");
        buf.writeInt(x);
        ServerPlayNetworking.send((ServerPlayerEntity) server.getPlayerManager().getPlayerList(), ModMessages.EXAMPLE_ID, buf);
    }
}
