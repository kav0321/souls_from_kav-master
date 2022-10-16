package net.kav.soul.networking.packet;

import io.netty.buffer.Unpooled;
import net.kav.soul.data.ItemData;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.server.network.ServerPlayerEntity;

import java.util.ArrayList;

public class datapack {

    public static void writeS2CListPacket(ServerPlayerEntity serverPlayerEntity){
        PacketByteBuf buf = new PacketByteBuf(Unpooled.buffer());
        for(int i=0; i< ItemData.getListNames().size();i++)
        {
            String Dataname = ItemData.getListNames().get(i);
            ArrayList<Object> data = ItemData.getList(Dataname);
            for(int u=0;u <data.size();u++)
            {
                buf.writeString(data.get(u).toString());
            }
        }

        for(int j=0;j<ItemData.ItemName.size();j++)
        {
            buf.writeString("Itemname");
            buf.writeString(ItemData.ItemName.get(j).toString());
        }
        for(int j=0;j<ItemData.soulnumber.size();j++)
        {
            buf.writeString("Itemsoul");
            buf.writeString(ItemData.soulnumber.get(j).toString());
        }



    }



}
