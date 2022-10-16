package net.kav.soul.event;

import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.kav.soul.data.ItemData;
import net.kav.soul.data.ItemType;
import net.kav.soul.networking.ModMessages;
import net.kav.soul.util.IEntityDataSaver;
import net.kav.soul.util.MaxStats;
import net.kav.soul.util.SoulData;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.command.CommandOutput;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;

public class serverevent implements ServerTickEvents.EndTick {
    int x1 = 0;
    int y = 0;
    int x12;
    @Override
    public void onEndTick(MinecraftServer server) {

        //final int y =server.getTicks();

        y++;
        x1++;
        if (x1 >= 10) {
            x1 = 0;

        }

        for (ServerPlayerEntity player : server.getPlayerManager().getPlayerList()) {

           // player.sendMessage(Text.of(Integer.toString(x1)), true);

          // if (player.handSwinging == false && x1==9) {
            //                PacketByteBuf buf = PacketByteBufs.create();
            //                //add sound
            //
            //
            //                float x = SoulData.addFloatpoint(((IEntityDataSaver) player), 0.7f, "Stamina");
            //                buf.writeFloat(2.0f);
            //
            //                // player.sendMessage(Text.of(Integer.toString(x1)),true);
            //                //player.sendMessage(Text.of("Succ"),true);
            //
            //                ServerPlayNetworking.send(player, ModMessages.STAMINA, buf);
            //            }
           // player.sendMessage(Text.of(Float.toString(SoulData.addFloatpoint(((IEntityDataSaver) player), 0f, "Stamina"))),true);
                String xereca =ItemData.ItemName.get(0).toString();

            player.sendMessage(Text.of(xereca),true);
            ItemStack itemss = player.getMainHandStack();
            Item item = itemss.getItem();
                ItemType types= new ItemType();
                player.sendMessage(Text.of(item.toString()),true);
               if(types.ItemNames(item))
               {
                   x12=ItemType.item.get(item);
               }
               else
                   x12=0;
           // player.sendMessage(Text.of(Integer.toString(x12)),true);
                PacketByteBuf bufss = PacketByteBufs.create();
                bufss.writeInt(x12);
                SoulData.addNbtPoints(((IEntityDataSaver) player), x12, "ItemType");
               ServerPlayNetworking.send(player, ModMessages.ITEM_TYPE, bufss);


            if(SoulData.addFloatpoint(((IEntityDataSaver) player), 0f, "Stamina")<=0) {
               String xa= player.getName().getString();
                server.getCommandManager().execute(new ServerCommandSource(CommandOutput.DUMMY,player.getPos(),player.getRotationClient(), player.getWorld(),4,"", Text.of("k"), server,player),"effect give "+xa+" minecraft:weakness 4 3 ");
                server.getCommandManager().execute(new ServerCommandSource(CommandOutput.DUMMY,player.getPos(),player.getRotationClient(), player.getWorld(),4,"", Text.of("k"), server,player),"effect give "+xa+" minecraft:mining_fatigue 4 3");
                server.getCommandManager().execute(new ServerCommandSource(CommandOutput.DUMMY,player.getPos(),player.getRotationClient(), player.getWorld(),4,"", Text.of("k"), server,player),"effect give "+xa+" minecraft:slowness 2 10");

            }

            if(y==5)
            {
                MaxStats.increaseMaxstamina(0);
                SoulData.addFloatpoint(((IEntityDataSaver) player),0,"MaxStamina");
                ServerPlayNetworking.send(player, ModMessages.STA_AT_ZERO, PacketByteBufs.create());
            }


        }



    }
}
