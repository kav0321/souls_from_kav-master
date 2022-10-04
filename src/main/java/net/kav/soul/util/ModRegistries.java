package net.kav.soul.util;

import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.kav.soul.entity.ModEntities;
import net.kav.soul.entity.custom.RaccoonEntity;

public class ModRegistries {

    public static void registerModStuffs(){
        registerAttributes();
    }

    private static void registerAttributes() {
        FabricDefaultAttributeRegistry.register(ModEntities.RACCOON, RaccoonEntity.setAttributes());
    }
}
