package net.kav.soul.event;

import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.kav.soul.entity.custom.RaccoonEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ActionResult;

public interface EntityInteractions{

   Event<EntityInteractions> Event = EventFactory.createArrayBacked(EntityInteractions.class, (listeners)->(player, raccoon)->{
        for(EntityInteractions listener: listeners)
        {
            ActionResult result = listener.interact(player,raccoon);
            if(result != ActionResult.PASS)
            {
                return result;
            }
        }
        return ActionResult.PASS;
    });

    ActionResult interact(PlayerEntity player, RaccoonEntity raccoon);
}
