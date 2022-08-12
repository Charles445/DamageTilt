package com.charles445.damagetilt;

import net.minecraft.world.entity.player.Player;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.event.entity.living.LivingKnockBackEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.config.ModConfigEvent;
import net.minecraftforge.network.NetworkDirection;

public class EventHandler
{
	@SubscribeEvent
	public void onModConfigEvent(ModConfigEvent event)
	{
		ModConfig config = event.getConfig();
		
		if(config.getSpec() == ModConfigManager.COMMON_SPEC)
		{
			ModConfigManager.updateCommon();
		}
	}
	
	@SubscribeEvent
	public void onKnockback(LivingKnockBackEvent event)
	{
		if(!TiltConfig.damageTiltEnabled)
			return;
		
		if(event.getEntity() instanceof Player)
		{
			Player player = (Player) event.getEntity();
			if(player.level.isClientSide)
				return;
			
			//Server Side
			
			if(player instanceof ServerPlayer)
			{
				ServerPlayer serverPlayer = (ServerPlayer)player;
				
				//Check if the connection is null, which may happen with fake players if they get knocked back
				//Forge 1.17 Addresses this which is cool, but I'm not taking any chances
				if(serverPlayer.connection == null)
					return;
				
				//Check if the network manager is null too, while we're at it.
				if(serverPlayer.connection.connection == null) //Yo Dawg
					return;
				
				PacketHandler.instance.sendTo(new MessageUpdateAttackYaw(player), serverPlayer.connection.connection, NetworkDirection.PLAY_TO_CLIENT);
			}
		}
	}
}
