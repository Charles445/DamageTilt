package com.charles445.damagetilt;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.config.Configuration;

@Mod
(
	modid = DamageTilt.MODID,
	name = DamageTilt.NAME,
	version = DamageTilt.VERSION,
	acceptedMinecraftVersions = "[1.7, 1.8)"
)
public class DamageTilt
{
	public static final String MODID = "damagetilt";
	public static final String NAME = "Damage Tilt";
	public static final String VERSION = "0.1.0";
	
	@Mod.EventHandler
	public void preInit(FMLPreInitializationEvent event)
	{
		System.out.println("Damage Tilt is loading");
		
		ModConfig.config = new Configuration(event.getSuggestedConfigurationFile());
		ModConfig.sync();
		
		PacketHandler.init();
		MinecraftForge.EVENT_BUS.register(new EventHandler());
	}
}
