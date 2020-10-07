package com.charles445.damagetilt;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;

@Mod(value = DamageTilt.MODID)
public class DamageTilt
{
	public static final String MODID = "damagetilt";
	public static final String NAME = "Damage Tilt";
	public static final String VERSION = "0.1.0";
	
	public DamageTilt()
	{
		//Config
		ModLoadingContext mlc = ModLoadingContext.get();
		mlc.registerConfig(ModConfig.Type.COMMON, ModConfigManager.COMMON_SPEC);
		ModConfigManager.loadAll();
		ModConfigManager.updateCommon();
		
		//Packets
		PacketHandler.init();
		
		//Handlers
		MinecraftForge.EVENT_BUS.register(new EventHandler());
	}
	
}
