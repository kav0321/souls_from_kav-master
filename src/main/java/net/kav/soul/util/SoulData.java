package net.kav.soul.util;

import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.kav.soul.data.ItemType;
import net.kav.soul.mixin.ModEntityDataSaverMixin;
//import net.kav.soul.networking.ModMessages;
import net.kav.soul.networking.ModMessages;
import net.minecraft.entity.ExperienceOrbEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayerEntity;

import java.util.Random;

import static java.lang.Math.pow;

public class SoulData {

    private static int soul;
    private static int Intimidation_factor;
    private static int Insanity;
    private static  int Random;

    private static int Health;
    private static int Strength;
    private static int dex;
    private static int Intelligence;
    private static float Stamina;
    private static int ItemType;
    private static float MaxStamina=20;
    public static int addNbtPoints(IEntityDataSaver player, int amount,String string)
    {
        NbtCompound nbt = player.getPersistentData();
        soul=nbt.getInt("soul");
        Intimidation_factor = nbt.getInt("Intimidation_factor");
        Insanity= nbt.getInt("Insanity");
        Stamina=nbt.getFloat("Stamina");
        MaxStamina=nbt.getFloat("Stamina_re");
        ItemType= nbt.getInt("ItemType");

        switch (string)
        {
            case "soul":

                if (soul >= 9999999) {
                    soul = 9999999;
                } else {
                    soul = soul + amount;
                }
                nbt.putInt("soul", soul);
               // synSoul(soul, (ServerPlayerEntity) player);
                return soul;
            case "Intimidation_factor" :
                if (Intimidation_factor >= 100) {
                    Intimidation_factor = 100;
                } else {
                    Intimidation_factor = Intimidation_factor + amount;
                }
                nbt.putInt("Intimidation_factor",Intimidation_factor);
                return Intimidation_factor;
            case "Insanity":
                if (Insanity >= 100) {
                    Insanity = 100;
                } else {
                    Insanity = Insanity + amount;
                }
                nbt.putInt("Insanity",Insanity);
                return Insanity;
            case "ItemType":
                ItemType =amount;
                nbt.putInt("ItemType",ItemType);
                GlobalSoul.setItemtype(amount);
                return ItemType;
                //do nothing for now
            default:
                return 0;
        }
    }

    public static float addFloatpoint(IEntityDataSaver player,float amount, String string)
    {
        NbtCompound nbt = player.getPersistentData();
        Stamina=nbt.getFloat("Stamina");
        MaxStamina=nbt.getFloat("Stamina_re");
        //Stamina=10;

        switch (string) {
            case "Stamina":

                if (Stamina >= MaxStats.getMaxstamina()) {
                    Stamina = MaxStats.getMaxstamina();
                }else
                {
                    Stamina = Stamina + amount;
                }
                nbt.putFloat("Stamina", Stamina);
                GlobalSoul.setStamina(Stamina);
                return Stamina;

            case "MaxStamina":
                if(MaxStamina>99)
                {
                    MaxStamina=99;
                }
                else
                {MaxStamina = MaxStamina + amount;}
                nbt.putFloat("MaxStamina", MaxStamina);

                MaxStats.setMaxstamina(MaxStamina);
                return MaxStamina;
            //do nothing for now
            default:
                return 2;
        }
    }

    public static float removeFloatpoint(IEntityDataSaver player,float amount, String string)
    {
        NbtCompound nbt = player.getPersistentData();
        Stamina=nbt.getFloat("Stamina");
        Stamina=nbt.getFloat("Stamina");
        MaxStamina=nbt.getFloat("Stamina_re");


        switch (string) {
            case "Stamina":

               if (Stamina<=0) {
                    Stamina=0;

                }
               else
               {Stamina = Stamina - amount;}

                GlobalSoul.setStamina(Stamina);
            nbt.putFloat("Stamina", Stamina);
             //  synSoul(Stamina, (ServerPlayerEntity) player);
            return Stamina;
            // synSoul(soul, (ServerPlayerEntity) player);

            //do nothing for now
            default:
                return -1000;
        }
    }

    public static int removeNbtPoints(IEntityDataSaver player, int amount,String string)
    {
        NbtCompound nbt = player.getPersistentData();
        soul=nbt.getInt("soul");
        Intimidation_factor = nbt.getInt("Intimidation_factor");
        Insanity= nbt.getInt("Insanity");

        switch (string)
        {
            case "soul":

                if (soul < 0) {
                    soul = 0;
                } else {
                    soul = soul - amount;
                }
                nbt.putInt("soul", soul);

                return soul;
            case "Intimidation_factor" :
                if (Intimidation_factor <0) {
                    Intimidation_factor = 0;
                } else {
                    Intimidation_factor = Intimidation_factor - amount;
                }
                nbt.putInt("Intimidation_factor",Intimidation_factor);
                return Intimidation_factor;
            case "Insanity":
                if (Insanity <0) {
                    Insanity = 0;
                } else {
                    Insanity = Insanity - amount;
                }
                nbt.putInt("Insanity",Insanity);
                return Insanity;
            //do nothing for now
            default:
                return 0;
        }
    }

    public static int getSoul(IEntityDataSaver player)
    {
        NbtCompound nbt = player.getPersistentData();
        soul=nbt.getInt("soul");
        nbt.putInt("soul", soul);
        return soul;
    }

    public static int random(IEntityDataSaver entity)
    {
        NbtCompound nbt = entity.getPersistentData();
        Random =nbt.getInt("random");

        int rands=0;
        int[] digits= new int[5];
        Random rand = new Random();


        for(int i=0;i<5;i++)
        {
            digits[i]=rand.nextInt(1,9);

            if(i!=0)
            {
                rands= digits[i]*((int) pow(10, i))+rands;


            }
            else
            {
                rands= digits[i]*((int) pow(10, i));



            }

        }


        Random=rands;

        nbt.putInt("random",Random);
        return Random;

    }

    public static int setRandom(IEntityDataSaver entity,int amount)
    {
        NbtCompound nbt = entity.getPersistentData();
        Random =nbt.getInt("random");

        Random=amount;
        nbt.putInt("random",Random);
        return Random;
    }
    public static int getRandom(IEntityDataSaver entity)
    {

        return Random;
    }








}