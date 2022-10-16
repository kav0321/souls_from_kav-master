package net.kav.soul.event;

import net.fabricmc.fabric.api.entity.event.v1.ServerPlayerEvents;
import net.minecraft.server.network.ServerPlayerEntity;

public class PlayerDeath implements  ServerPlayerEvents.AfterRespawn{
    @Override
    public void afterRespawn(ServerPlayerEntity oldPlayer, ServerPlayerEntity newPlayer, boolean alive) {
        //int x;
        //
        //        x=SoulData.addNbtPoints(((IEntityDataSaver) newPlayer),getSoulincrease(),"soul");
        //        // ((PlayerEntity) entity).sendMessage(Text.of("h1"),true);
        //
        //
        //        PacketByteBuf buf= PacketByteBufs.create();
        //        //add sound
        //
        //        buf.writeInt(x);
        //        y=SoulData.addNbtPoints(((IEntityDataSaver) entity),0,"soul");
        //        GlobalSoul.setGlobalSoul(y);
        //        ServerPlayNetworking.send((ServerPlayerEntity) entity, ModMessages.EXAMPLE_ID, buf);


       // ClientPlayNetworking.send(ModMessages.EXAMPLE_ID, PacketByteBufs.create());
        //ClientPlayNetworking.send(ModMessages.GAINING_SOUL_ID, PacketByteBufs.create());
    }
}
