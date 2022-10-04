package net.kav.soul.util;

import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.kav.soul.client.gui.ExampleGui;
import net.kav.soul.client.gui.ExampleScreen;
import net.minecraft.client.MinecraftClient;
import net.minecraft.nbt.NbtCompound;

public class TradingCondition {

    private static boolean trading_condition;
    private static boolean closing_condition;
    public static Boolean trading(IEntityDataSaver player,boolean statement)
    {
        NbtCompound nbt = player.getPersistentData();
        trading_condition=nbt.getBoolean("trading_condition");
        closing_condition=nbt.getBoolean("closing_condition");

        if(statement==false)
        {
            trading_condition=false;


        }
        else
        {
            trading_condition=true;
            closing_condition=false;
        }


        nbt.putBoolean("closing_condition",closing_condition);
        nbt.putBoolean("trading_condition", trading_condition);
        return trading_condition;

    }

   public static Boolean gettradingCondition()
   {
       return trading_condition;
   }

   public static Boolean getescape(IEntityDataSaver player)
   {
       NbtCompound nbt = player.getPersistentData();
       nbt.putBoolean("closing_condition",closing_condition);
       return closing_condition;
   }
    public static void setescape(IEntityDataSaver player, boolean statement)
    {
        NbtCompound nbt = player.getPersistentData();
        closing_condition=nbt.getBoolean("closing_condition");

        if(statement==true)
        {
            closing_condition=true;
            nbt.putBoolean("closing_condition",closing_condition);
        }
        else
        {
            closing_condition=false;
            nbt.putBoolean("closing_condition",closing_condition);
        }



    }
}
