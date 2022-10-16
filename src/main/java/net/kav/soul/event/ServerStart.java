package net.kav.soul.event;

import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.kav.soul.networking.ModMessages;
import net.kav.soul.util.IEntityDataSaver;
import net.kav.soul.util.SoulData;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.resource.LifecycledResourceManager;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayerEntity;

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
