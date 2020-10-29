package com.charles445.damagetilt;

import javax.annotation.Nullable;

import net.minecraftforge.common.config.Configuration;

public class ModConfig
{
	@Nullable
	public static Configuration config;
	
	public static boolean damageTiltEnabled = true;
	
	public static void sync()
	{
		if(config!=null)
		{
			try
			{
				config.load();
				
				damageTiltEnabled = config.get(
						Configuration.CATEGORY_GENERAL,
						"Damage Tilt Enabled",
						"true",
						"Whether the damage tilt effect is enabled")
						.getBoolean();
				
				if(config.hasChanged())
					config.save();
			}
			catch(Exception e)
			{
				System.out.println("DamageTilt config failed unexpectedly");
				e.printStackTrace();
			}
		}
	}
}
