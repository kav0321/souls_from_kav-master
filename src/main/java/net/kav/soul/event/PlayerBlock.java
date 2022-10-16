package net.kav.soul.event;

import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ActionResult;

public interface PlayerBlock {
    Event<PlayerBlock> EVENT = EventFactory.createArrayBacked(PlayerBlock.class,
            (listeners) -> (player) -> {
                for (PlayerBlock listener : listeners) {
                    ActionResult result = listener.block(player);

                    if(result != ActionResult.PASS) {
                        return result;
                    }
                }

                return ActionResult.PASS;
            });

    ActionResult block(PlayerEntity player);



}
