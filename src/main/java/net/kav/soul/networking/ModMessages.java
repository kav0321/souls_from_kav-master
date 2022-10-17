package net.kav.soul.networking;

import io.netty.buffer.Unpooled;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.kav.soul.Soul;
import net.kav.soul.data.ItemData;
import net.kav.soul.data.ItemLoader;
import net.kav.soul.event.EntityDeath;
import net.kav.soul.networking.packet.PlayerStatsServer;
import net.kav.soul.networking.packet.TradingServer;
import net.kav.soul.networking.packet.commandspackets;
import net.kav.soul.util.*;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

import java.util.ArrayList;

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
    public static final Identifier LIST_PACKET= new Identifier(Soul.MOD_ID,"list_packet");
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


        ClientPlayNetworking.registerGlobalReceiver(LIST_PACKET, (client, handler, buf, sender) -> {
            if (client.player != null) {
                executeListPacket(buf, client.player);
            } else {
                PacketByteBuf newBuffer = new PacketByteBuf(Unpooled.buffer());
                while (buf.isReadable()) {
                    newBuffer.writeString(buf.readString());
                }
                client.execute(() -> {
                    executeListPacket(newBuffer, client.player);
                });
            }
        });





    }



    private static void executeListPacket(PacketByteBuf buf, ClientPlayerEntity player) {
        ItemLoader.clearEveryList();
        ArrayList<String> list = new ArrayList<>();
        while (buf.isReadable()) {
            list.add(buf.readString());
        }
        for (int i = 0; i < list.size(); i++) {
            String listName = list.get(i).toString();
            if (ItemData.getListNames().contains(listName)) {
                int count = 2;
                int negativeCount = -2;
                if (listName.equals("minecraft:armor") || listName.equals("minecraft:tool") || listName.equals("minecraft:hoe") || listName.equals("minecraft:sword")
                        || listName.equals("minecraft:axe") || listName.equals("minecraft:custom_block") || listName.equals("minecraft:custom_item") || listName.equals("minecraft:custom_entity"))
                    negativeCount--;
                if (listName.equals("minecraft:enchanting_table")) {
                    count = 5;
                }
                for (int u = negativeCount; u < count; u++) {
                    addToList(listName, list.get(i + u));
                }
            } else if (listName.equals("mining:level")) {
                List<Integer> blockList = new ArrayList<>();
                LevelLists.miningLevelList.add(Integer.parseInt(list.get(i + 1)));
                for (int u = i + 2; u < list.size(); u++) {
                    if (list.get(u).equals("mining:level") || list.get(u).equals("brewing:level"))
                        break;
                    blockList.add(Integer.parseInt(list.get(u)));
                }
                LevelLists.miningBlockList.add(blockList);
            } else if (listName.equals("brewing:level")) {
                List<Integer> brewingItemList = new ArrayList<>();
                LevelLists.brewingLevelList.add(Integer.parseInt(list.get(i + 1)));
                for (int u = i + 2; u < list.size(); u++) {
                    if (list.get(u).equals("brewing:level") || list.get(u).equals("smithing:level"))
                        break;
                    brewingItemList.add(Integer.parseInt(list.get(u)));
                }
                LevelLists.brewingItemList.add(brewingItemList);
            } else if (listName.equals("smithing:level")) {
                List<Integer> smithingItemList = new ArrayList<>();
                LevelLists.smithingLevelList.add(Integer.parseInt(list.get(i + 1)));
                for (int u = i + 2; u < list.size(); u++) {
                    if (list.get(u).equals("smithing:level") || list.get(u).equals("crafting:level"))
                        break;
                    smithingItemList.add(Integer.parseInt(list.get(u)));
                }
                LevelLists.smithingItemList.add(smithingItemList);
            } else if (listName.equals("crafting:level")) {
                List<Integer> craftingItemList = new ArrayList<>();
                LevelLists.craftingLevelList.add(Integer.parseInt(list.get(i + 1)));
                LevelLists.craftingSkillList.add(String.valueOf(list.get(i + 2)));
                for (int u = i + 3; u < list.size(); u++) {
                    if (list.get(u).equals("crafting:level"))
                        break;
                    craftingItemList.add(Integer.parseInt(list.get(u)));
                }
                LevelLists.craftingItemList.add(craftingItemList);
            }
        }
        LevelLists.listOfAllLists.clear();
        LevelLoader.addAllInOneList();
        PlayerStatsManager playerStatsManager = ((PlayerStatsManagerAccess) player).getPlayerStatsManager(player);
        player.getAttributeInstance(EntityAttributes.GENERIC_MAX_HEALTH).setBaseValue(ConfigInit.CONFIG.healthBase + (double) playerStatsManager.getLevel("health") * ConfigInit.CONFIG.healthBonus);
        player.getAttributeInstance(EntityAttributes.GENERIC_MOVEMENT_SPEED)
                .setBaseValue(ConfigInit.CONFIG.movementBase + (double) playerStatsManager.getLevel("agility") * ConfigInit.CONFIG.movementBonus);
        player.getAttributeInstance(EntityAttributes.GENERIC_ATTACK_DAMAGE)
                .setBaseValue(ConfigInit.CONFIG.attackBase + (double) playerStatsManager.getLevel("strength") * ConfigInit.CONFIG.attackBonus);
        player.getAttributeInstance(EntityAttributes.GENERIC_ARMOR).setBaseValue(ConfigInit.CONFIG.defenseBase + (double) playerStatsManager.getLevel("defense") * ConfigInit.CONFIG.defenseBonus);
        player.getAttributeInstance(EntityAttributes.GENERIC_LUCK).setBaseValue(ConfigInit.CONFIG.luckBase + (double) playerStatsManager.getLevel("luck") * ConfigInit.CONFIG.luckBonus);
        PlayerStatsServerPacket.syncLockedBlockList(playerStatsManager);
        PlayerStatsServerPacket.syncLockedBrewingItemList(playerStatsManager);
        PlayerStatsServerPacket.syncLockedSmithingItemList(playerStatsManager);
        PlayerStatsServerPacket.syncLockedCraftingItemList(playerStatsManager);
    }
}
