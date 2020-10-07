package com.charles445.damagetilt;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod
(
	modid = DamageTilt.MODID,
	name = DamageTilt.NAME,
	version = DamageTilt.VERSION,
	acceptedMinecraftVersions = "[1.12]"
)
public class DamageTilt
{
	public static final String MODID = "damagetilt";
	public static final String NAME = "Damage Tilt";
	public static final String VERSION = "0.1.0";
	
	@Mod.EventHandler
	public void preInit(FMLPreInitializationEvent event)
	{
		PacketHandler.init();
		MinecraftForge.EVENT_BUS.register(new EventHandler());
	}
}
