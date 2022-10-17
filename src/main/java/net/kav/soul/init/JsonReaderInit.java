package net.kav.soul.init;

import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;
import net.fabricmc.fabric.api.resource.ResourceManagerHelper;
import net.kav.soul.Soul;
import net.kav.soul.data.ItemLoader;
import net.kav.soul.networking.packet.datapack;
import net.minecraft.resource.ResourceType;

public class JsonReaderInit {

    public static void init() {
        ResourceManagerHelper.get(ResourceType.SERVER_DATA).registerReloadListener(new ItemLoader());
        ServerLifecycleEvents.END_DATA_PACK_RELOAD.register((server, serverResourceManager, success) -> {
            if (success) {
                for (int i = 0; i < server.getPlayerManager().getPlayerList().size(); i++)
                    datapack.writeS2CListPacket(server.getPlayerManager().getPlayerList().get(i));
                Soul.LOGGER.info("Finished reload on {}", Thread.currentThread());
            } else
                Soul.LOGGER.error("Failed to reload on {}", Thread.currentThread());
        });
    }

}
