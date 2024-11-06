package com.weekendserver;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.networking.v1.LoginPacketSender;
import net.fabricmc.fabric.api.networking.v1.ServerLoginConnectionEvents;

import net.fabricmc.fabric.api.networking.v1.ServerLoginNetworking;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerLoginNetworkHandler;
import net.minecraft.text.Text;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.ZoneId;

public class WeekendServer implements ModInitializer {
	public static final String MOD_ID = "weekendserver";

	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		LOGGER.info("Weekday Access Mod initialized.");
		ServerLoginConnectionEvents.QUERY_START.register(this::onPlayerLogin);
	}

	private void onPlayerLogin(ServerLoginNetworkHandler serverLoginNetworkHandler, MinecraftServer minecraftServer, LoginPacketSender loginPacketSender, ServerLoginNetworking.LoginSynchronizer loginSynchronizer)
	{
		DayOfWeek currentDay = LocalDate.now(ZoneId.systemDefault()).getDayOfWeek();
		LOGGER.info("Day is " + currentDay);

		if(currentDay != DayOfWeek.SUNDAY && currentDay != DayOfWeek.SATURDAY){
			LOGGER.warn("Not weekend you silly >:0");
			serverLoginNetworkHandler.disconnect(Text.literal("Not weekend you stupid shit ass motherfucking shit go kys"));
		}
	}

}