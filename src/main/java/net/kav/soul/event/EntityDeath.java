package net.kav.soul.event;

import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.entity.event.v1.ServerEntityCombatEvents;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;

import net.kav.soul.networking.ModMessages;

import net.kav.soul.util.GlobalSoul;
import net.kav.soul.util.IEntityDataSaver;
import net.kav.soul.util.SoulData;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.RangedAttackMob;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.passive.IronGolemEntity;
import net.minecraft.entity.passive.VillagerEntity;
import net.minecraft.entity.passive.WolfEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.command.CommandOutput;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.command.TestCommand;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.Text;

import java.util.IdentityHashMap;

import static net.kav.soul.networking.ModMessages.PLAYER_SOUL;

public class EntityDeath implements ServerEntityCombatEvents.AfterKilledOtherEntity{
    public static int a;
    public static void settHealth(LivingEntity entity,boolean bonus1,boolean bonus2)
    {float x=entity.getMaxHealth();
        if(entity instanceof HostileEntity)
        {
         if(bonus1=true)
         {
             a = (int) ((int) (x*2)+5*(x*2)/100);
         } else if (bonus2=true) {
             a = (int) ((int) (x*2)+15*(x*2)/100);
         }
         else
         {
             a = (int) (x*2);
         }
            MinecraftClient.getInstance().player.sendMessage(Text.of("h1"),true);
        }
        else if (entity instanceof RangedAttackMob &&entity.getMaxHealth()>=30)
        {
            if(bonus1=true)
            {
                a = (int) ((int) (x*3)+5*(x*3)/100);
            } else if (bonus2=true) {
                a = (int) ((int) (x*3)+15*(x*3)/100);
            }
            else
            {
                a = (int) (x*3);
            }

            MinecraftClient.getInstance().player.sendMessage(Text.of("h2"),true);
        }
        else if(entity.getMaxHealth()>50)
        {
            if(bonus1=true)
            {
                a = (int) ((int) (x*x)+5*(x*x)/100);
            } else if (bonus2=true) {
                a = (int) ((int) (x*x)+15*(x*x)/100);
            }
            else
            {
                a = (int) (x*x);
            }
            a = (int) (x*x);
            MinecraftClient.getInstance().player.sendMessage(Text.of("h3"),true);
        }
        else if(entity instanceof IronGolemEntity)
        {
            a=0;

        } else if (entity instanceof VillagerEntity) {
            a=0;

        } else if (entity instanceof WolfEntity) {
            a=-1000;
        } else {
            a = ((int) entity.getMaxHealth());
        }

       // MinecraftClient.getInstance().player.sendMessage(Text.of("h4"),true);
    }

    public static int getSoulincrease()
    {
        return a;
    }

    @Override
    public void afterKilledOtherEntity(ServerWorld world, Entity entity, LivingEntity killedEntity) {
        settHealth(killedEntity,false,false);
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
               world.getServer().getCommandManager().execute(new ServerCommandSource(CommandOutput.DUMMY,entity.getPos(),entity.getRotationClient(),world,4,"", Text.of("k"), world.getServer(),entity),"clear");
               // SoulData.synSoul(((IEntityDataSaver) entity).getPersistentData().getInt("soul"), (ServerPlayerEntity) entity);
            }


            //SoulData.synSoul(((IEntityDataSaver) entity).getPersistentData().getInt("soul"), (ServerPlayerEntity) entity);
        }

    }
}
