package com.charles445.damagetilt;

import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod
(
	modid = DamageTilt.MODID,
	name = DamageTilt.NAME,
	version = DamageTilt.VERSION,
	acceptedMinecraftVersions = "[1.8, 1.9)",
	acceptableRemoteVersions = "*"
)
public class DamageTilt
{
	public static final String MODID = "damagetilt";
	public static final String NAME = "Damage Tilt";
	public static final String VERSION = "0.2.0";
	
	@Mod.EventHandler
	public void preInit(FMLPreInitializationEvent event)
	{
		System.out.println("Damage Tilt is loading");
		
		ModConfig.config = new Configuration(event.getSuggestedConfigurationFile());
		ModConfig.sync();
	}
}
