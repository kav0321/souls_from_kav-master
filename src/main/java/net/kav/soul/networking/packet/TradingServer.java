package net.kav.soul.networking.packet;

import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.kav.soul.util.IEntityDataSaver;
import net.kav.soul.util.TradingCondition;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayNetworkHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.Identifier;

public class TradingServer {
    Boolean trading;


    public static void send(ServerPlayerEntity player, Identifier channelName, PacketByteBuf buf)
    {
        TradingCondition.trading(((IEntityDataSaver) player),true);
    }
    public static void sendescapeinforeceive(MinecraftServer server, ServerPlayerEntity player, ServerPlayNetworkHandler handler, PacketByteBuf buf, PacketSender responseSender)
    {
        TradingCondition.setescape(((IEntityDataSaver) player),true);

    }



}
