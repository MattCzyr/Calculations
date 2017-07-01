package com.chaosthedude.calculations;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.chaosthedude.calculations.key.KeybindHandler;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = Calculations.MODID, name = Calculations.NAME, version = Calculations.VERSION, acceptedMinecraftVersions = "[1.12]")

public class Calculations {

	public static final String MODID = "calculations";
	public static final String NAME = "Calculations";
	public static final String VERSION = "1.1.0";

	public static final Logger logger = LogManager.getLogger(MODID);

	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		MinecraftForge.EVENT_BUS.register(new KeybindHandler());
	}

}