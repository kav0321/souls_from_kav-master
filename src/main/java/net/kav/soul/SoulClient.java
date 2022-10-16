package net.kav.soul;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.fabricmc.fabric.api.entity.event.v1.ServerEntityCombatEvents;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.kav.soul.client.gui.SoulKavOverlay;
import net.kav.soul.entity.ModEntities;

import net.kav.soul.entity.client.RaccoonRenderer;
import net.kav.soul.entity.custom.RaccoonEntity;
import net.kav.soul.event.ClientStamina;
import net.kav.soul.event.EntityDeath;
import net.kav.soul.event.KeyInputHandler;
import net.kav.soul.event.ServerStart;
//import net.kav.soul.networking.ModMessages;
import net.kav.soul.networking.ModMessages;

import net.kav.soul.util.DisplayGUI;
import net.kav.soul.util.SoulData;

public class SoulClient implements ClientModInitializer {


    @Override
    public void onInitializeClient() {

        KeyInputHandler.register();
        ModMessages.registerS2CPackets();
        HudRenderCallback.EVENT.register(new SoulKavOverlay());
        DisplayGUI.GUi();
        ClientTickEvents.END_WORLD_TICK.register(new ClientStamina());

        EntityRendererRegistry.register(ModEntities.RACCOON, RaccoonRenderer::new);
        //EntityRendererRegistry.register(ModEntities.RACCOON, RaccoonRenderer::new);

    }
}
