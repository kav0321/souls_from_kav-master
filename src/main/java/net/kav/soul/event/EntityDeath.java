package net.kav.soul.event;

import net.fabricmc.fabric.api.entity.event.v1.ServerEntityCombatEvents;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.kav.soul.networking.ModMessages;
import net.kav.soul.util.GlobalSoul;
import net.kav.soul.util.IEntityDataSaver;
import net.kav.soul.util.SoulData;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.passive.IronGolemEntity;
import net.minecraft.entity.passive.PassiveEntity;
import net.minecraft.entity.passive.VillagerEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;

public class EntityDeath implements ServerEntityCombatEvents.AfterKilledOtherEntity{
    public static int a;
    public static void settHealth(int amount,boolean bonus1,boolean bonus2,int a2)
    {
       if((amount>=15&& amount<40) &&a2==2)
       {
           a=amount*20;
       } else if (((amount>=15&& amount<40) &&a2==1)) {
           a=amount*10;
       }
        else if (amount>=50) {
           a=amount*200;
       } else if (amount>=40&& amount<50) {
           a=amount*31;
       } else if (a2>10) {
           a=0;

       }
       else
           a =amount;


    }


       // MinecraftClient.getInstance().player.sendMessage(Text.of("h4"),true);


    public static int getSoulincrease()
    {
        return a;
    }

    @Override
    public void afterKilledOtherEntity(ServerWorld world, Entity entity, LivingEntity killedEntity) {
        int a2;//hostile =2;
        int d;
        if(killedEntity instanceof HostileEntity || killedEntity.getMaxHealth()>=40)
        {

            a2=2;
            settHealth((int) killedEntity.getMaxHealth(),false,false,a2);
        }
        else if(killedEntity instanceof PassiveEntity|| killedEntity.getMaxHealth()>=40)
        {
            a2=1;

            settHealth((int) killedEntity.getMaxHealth(),false,false,a2);
        } else if (killedEntity.getMaxHealth()>=50) {
            a2=4;
            settHealth((int) killedEntity.getMaxHealth(),false,false,a2);
        }
        else if(killedEntity instanceof IronGolemEntity) {
            a2 = 15;
            settHealth(0, false, false, a2);
        }
        else if(killedEntity instanceof VillagerEntity)
        {
            a2 = 15;
            settHealth(0, false, false, a2);
        }
        else
        {a2=0;
            settHealth(0, false, false, a2);}


        if(!world.isClient())
        {
            if (entity instanceof PlayerEntity) {
                int x;
                int y;


                x=SoulData.addNbtPoints(((IEntityDataSaver) entity),getSoulincrease(),"soul");
               // ((PlayerEntity) entity).sendMessage(Text.of("h1"),true);


                PacketByteBuf buf= PacketByteBufs.create();
                //add sound

                buf.writeInt(x);
                y=SoulData.addNbtPoints(((IEntityDataSaver) entity),0,"soul");
                GlobalSoul.setGlobalSoul(y);
                ServerPlayNetworking.send((ServerPlayerEntity) entity, ModMessages.EXAMPLE_ID, buf);
              //world.getServer().getCommandManager().execute(new ServerCommandSource(CommandOutput.DUMMY,entity.getPos(),entity.getRotationClient(),world,4,"", Text.of("k"), world.getServer(),entity),"clear");
               // SoulData.synSoul(((IEntityDataSaver) entity).getPersistentData().getInt("soul"), (ServerPlayerEntity) entity);
            }


            //SoulData.synSoul(((IEntityDataSaver) entity).getPersistentData().getInt("soul"), (ServerPlayerEntity) entity);
        }

    }
}
