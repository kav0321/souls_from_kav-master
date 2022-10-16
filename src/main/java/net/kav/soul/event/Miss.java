package net.kav.soul.event;

import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.minecraft.util.ActionResult;

public interface Miss {

    Event<Miss> Miss = EventFactory.createArrayBacked(Miss.class,
            (listeners) -> (player) -> {
                for (Miss listener : listeners) {
                    ActionResult result = listener.Miss(player);

                    if(result != ActionResult.PASS) {
                        return result;
                    }
                }

                return ActionResult.PASS;
            });

    ActionResult Miss(Object player);
}
