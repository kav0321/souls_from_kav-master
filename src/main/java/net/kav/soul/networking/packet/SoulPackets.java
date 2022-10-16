package net.kav.soul.networking.packet;

import io.netty.buffer.Unpooled;
import net.kav.soul.data.ItemData;
import net.kav.soul.util.IEntityDataSaver;
import net.kav.soul.util.SoulData;
import net.minecraft.client.MinecraftClient;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.server.network.ServerPlayerEntity;

import java.util.List;

public class SoulPackets {


    public static void  packe(){SoulData.addFloatpoint(((IEntityDataSaver) MinecraftClient.getInstance().player),1,"Stamina");}


    //public static void writeS2CQuestListPacket(ServerPlayerEntity serverPlayerEntity) {
    //        PacketByteBuf buf = new PacketByteBuf(Unpooled.buffer());
    //        for (int i = 0; i < ItemData.getListNames().size(); i++) {
    //            String listName = ItemData.getListNames().get(i);
    //            if ( listName.equals("itemname")) {
    //                List<List<Object>> list = ItemData.getList(listName);
    //                buf.writeString(listName);
    //                for (int k = 0; k < list.size(); k++) {
    //                    for (int u = 0; u < list.get(k).size(); u++) {
    //                        buf.writeString(list.get(k).get(u).toString());
    //                    }
    //                    buf.writeString("stop");
    //                }
    //            } else {
    //                List list = QuestData.getList(listName);
    //                buf.writeString(listName);
    //                for (int u = 0; u < list.size(); u++) {
    //                    buf.writeString(list.get(u).toString());
    //                }
    //            }
    //        }
    //        CustomPayloadS2CPacket packet = new CustomPayloadS2CPacket(QUEST_LIST_DATA, buf);
    //        serverPlayerEntity.networkHandler.sendPacket(packet);
    //}
}
