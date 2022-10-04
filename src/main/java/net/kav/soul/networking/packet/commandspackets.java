package net.kav.soul.networking.packet;

import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.kav.soul.util.IEntityDataSaver;
import net.kav.soul.util.TradingCondition;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.command.CommandOutput;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.network.ServerPlayNetworkHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.Text;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.World;

public class commandspackets {

    public static void commands(MinecraftServer server, ServerPlayerEntity player, ServerPlayNetworkHandler handler, PacketByteBuf buf, PacketSender responseSender)
    {





        //items receives and removes souls after getting the items
        server.getCommandManager().execute(new ServerCommandSource(CommandOutput.DUMMY,player.getPos(),player.getRotationClient(), player.getWorld(),4,"", Text.of("k"), server,player),"kill");

    }
}
