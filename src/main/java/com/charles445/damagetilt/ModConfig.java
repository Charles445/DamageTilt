package com.charles445.damagetilt;

import net.minecraftforge.common.config.Config;
import net.minecraftforge.common.config.ConfigManager;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@Config(modid = DamageTilt.MODID)
public class ModConfig
{
	@Config.Comment("Whether the damage tilt effect is enabled")
	@Config.Name("Damage Tilt Enabled")
	public static boolean damageTiltEnabled = true;
	
	@Mod.EventBusSubscriber(modid = DamageTilt.MODID)
	private static class EventHandler
	{
		@SubscribeEvent
		@SideOnly(Side.CLIENT)
		public static void onConfigChanged(ConfigChangedEvent.OnConfigChangedEvent event)
		{
			if(event.getModID().equals(DamageTilt.MODID))
			{
				ConfigManager.sync(DamageTilt.MODID, Config.Type.INSTANCE);
			}
		}
	}
}
