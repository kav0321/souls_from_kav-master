package net.kav.soul.util;

import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.kav.soul.client.gui.ExampleGui;
import net.kav.soul.client.gui.ExampleScreen;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.NbtCompound;

public class DisplayGUI {


    public static void GUi(){
        ClientTickEvents.END_CLIENT_TICK.register(client -> {


            if(TradingCondition.gettradingCondition()==true)
            {




                MinecraftClient.getInstance().setScreen(new ExampleScreen((new ExampleGui())));



                TradingCondition.trading(((IEntityDataSaver) client.player),false);
            }


        });



    }
}
