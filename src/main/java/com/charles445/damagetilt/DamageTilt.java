package com.charles445.damagetilt;

import net.fabricmc.api.ModInitializer;
import net.minecraft.util.Identifier;

public class DamageTilt implements ModInitializer 
{
	public static final String MODID = "damagetilt";
	public static final String NAME = "Damage Tilt";
	public static final String VERSION = "0.1.2";
	
	public static final Identifier PLAY_YAW_PACKET_ID = new Identifier(DamageTilt.MODID, "attackedatyaw");
	
	@Override
	public void onInitialize()
	{
		
	}
}
