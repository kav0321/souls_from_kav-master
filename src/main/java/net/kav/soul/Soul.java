package net.kav.soul;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.entity.event.v1.ServerEntityCombatEvents;
import net.fabricmc.fabric.api.entity.event.v1.ServerPlayerEvents;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;
import net.fabricmc.fabric.api.networking.v1.ServerPlayConnectionEvents;
import net.kav.soul.event.*;
import net.kav.soul.item.Moditems;
//import net.kav.soul.networking.ModMessages;

import net.kav.soul.networking.ModMessages;
import net.kav.soul.util.ModRegistries;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import software.bernie.geckolib3.GeckoLib;

public class Soul implements ModInitializer {
	public static final String MOD_ID ="soul";
	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		Moditems.registerModItems();

	   ModMessages.registerC2SPackets();


		ServerEntityCombatEvents.AFTER_KILLED_OTHER_ENTITY.register(new EntityDeath());
		ServerPlayerEvents.AFTER_RESPAWN.register(new PlayerDeath());
		//ServerPlayConnectionEvents.JOIN.register(new ServerStart());
		ModRegistries.registerModStuffs();
		ServerLifecycleEvents.START_DATA_PACK_RELOAD.register(new ServerStart());

		//GeckoLib.initialize();

	}
}
