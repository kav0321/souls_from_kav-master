package net.kav.soul.networking;

import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.kav.soul.Soul;
import net.kav.soul.event.EntityDeath;
import net.kav.soul.networking.packet.PlayerStatsServer;
import net.kav.soul.networking.packet.TradingServer;
import net.kav.soul.networking.packet.commandspackets;
import net.kav.soul.util.*;
import net.minecraft.client.MinecraftClient;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class ModMessages {
    public static final Identifier PLAYER_SOUL = new Identifier(Soul.MOD_ID, "soul");
    public static final Identifier EXAMPLE_ID = new Identifier(Soul.MOD_ID,"example");
    public static final Identifier Soul_ID = new Identifier(Soul.MOD_ID,"soul");

    public static final Identifier TRADING = new Identifier(Soul.MOD_ID,"trading");
    public static final Identifier ESCAPE= new Identifier(Soul.MOD_ID,"escape");
    public static final Identifier RANDOM= new Identifier(Soul.MOD_ID,"random");
    public static final Identifier COMMAND= new Identifier(Soul.MOD_ID,"command");
    public static final Identifier COMMAND2= new Identifier(Soul.MOD_ID,"commandtwo");

    public static final Identifier HANDSWING= new Identifier(Soul.MOD_ID,"handswing");
    public static final Identifier STAMINA= new Identifier(Soul.MOD_ID,"stamina");
    public static final Identifier STAMINA2= new Identifier(Soul.MOD_ID,"staminaa");
    public static final Identifier STA_AT_ZERO= new Identifier(Soul.MOD_ID,"sta_at_zero");
    public static final Identifier ITEM_TYPE= new Identifier(Soul.MOD_ID,"item_type");
    public static void registerC2SPackets(){

       ServerPlayNetworking.registerGlobalReceiver(ESCAPE,TradingServer::sendescapeinforeceive);
       ServerPlayNetworking.registerGlobalReceiver(COMMAND, commandspackets::commands);
        ServerPlayNetworking.registerGlobalReceiver(COMMAND2, commandspackets::commandsta);
       ServerPlayNetworking.registerGlobalReceiver(HANDSWING, PlayerStatsServer::sendstats);
        ServerPlayNetworking.registerGlobalReceiver(STAMINA, PlayerStatsServer::getsta);



    }

    public static void registerS2CPackets(){
        //ClientPlayNetworking.registerGlobalReceiver(HANDSWING, PlayerStatsServer::sendstats);
        ClientPlayNetworking.registerGlobalReceiver(RANDOM, (client,handler,buf, respondSender)->
        {
            //(((IEntityDataSaver) client.player)).getPersistentData().putInt("random",buf.readInt());
            boolean x= MinecraftClient.getInstance().isInSingleplayer();
            if(x!=true)
            {
                int a =buf.readInt();
                SoulData.setRandom(((IEntityDataSaver) client.player), a);
            }

        });
        ClientPlayNetworking.registerGlobalReceiver(EXAMPLE_ID, (client,handler,buf, respondSender)->
        {
           (((IEntityDataSaver) client.player)).getPersistentData().putInt("soul",buf.readInt());

           boolean x= MinecraftClient.getInstance().isInSingleplayer();
           if(x!=true)
           {
               int a=SoulData.addNbtPoints(((IEntityDataSaver) client.player), EntityDeath.getSoulincrease(),"soul");
                GlobalSoul.setGlobalSoul(a);
                SoulData.addNbtPoints(((IEntityDataSaver) client.player), EntityDeath.getSoulincrease(),"soul");
           }

        });



        ClientPlayNetworking.registerGlobalReceiver(TRADING, (client,handler,buf, respondSender)->
        {
           // (((IEntityDataSaver) client.player)).getPersistentData().putBoolean("trading_condition",buf.readBoolean());

            //boolean x= MinecraftClient.getInstance().isInSingleplayer();
            //            if(x!=true)
            //            {
            //
            //            }
            boolean con= TradingCondition.trading((IEntityDataSaver) client.player,true);


        });


        //ClientPlayNetworking.registerGlobalReceiver(STAMINA, (client,handler,buf, respondSender)->
        //        {
        //            //(((IEntityDataSaver) client.player)).getPersistentData().putFloat("Stamina",buf.readFloat());
        //            boolean x= MinecraftClient.getInstance().isInSingleplayer();
        //            if(x!=true) {
        //                float x1 = buf.readFloat();
        //
        //                float xa =SoulData.addFloatpoint(((IEntityDataSaver) client.player), x1, "Stamina");
        //               // GlobalSoul.setStamina(xa);
        //            }
        //        });


        ClientPlayNetworking.registerGlobalReceiver(STAMINA2, (client,handler,buf, respondSender)->
        {
            //(((IEntityDataSaver) client.player)).getPersistentData().putFloat("Stamina",buf.readFloat());
            float xa;
             boolean x= MinecraftClient.getInstance().isInSingleplayer();
            if(x!=true) {
                client.player.sendMessage(Text.of("oh yeye"),true);
                float x1 = buf.readFloat();

                SoulData.removeFloatpoint(((IEntityDataSaver) client.player), x1, "Stamina");
                MaxStats.increaseMaxstamina(0);

            }

        });

        ClientPlayNetworking.registerGlobalReceiver(STA_AT_ZERO, (client,handler,buf, respondSender)->
        {


            //(((IEntityDataSaver) client.player)).getPersistentData().putFloat("Stamina",buf.readFloat());
            boolean x= MinecraftClient.getInstance().isInSingleplayer();
            if(x!=true) {
                SoulData.addFloatpoint(((IEntityDataSaver) client.player),0,"MaxStamina");
            }
        });


        ClientPlayNetworking.registerGlobalReceiver(ITEM_TYPE, (client,handler,buf, respondSender)->
        {

            //(((IEntityDataSaver) client.player)).getPersistentData().putInt("ItemType",);
            boolean x= MinecraftClient.getInstance().isInSingleplayer();
            if(x!=true) {
                SoulData.addFloatpoint(((IEntityDataSaver) client.player),0,"ItemType");
            }
GlobalSoul.setItemtype(buf.readInt());
        });








    }
}
