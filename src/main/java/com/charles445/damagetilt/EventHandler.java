package com.charles445.damagetilt;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraftforge.event.entity.living.LivingKnockBackEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.network.NetworkDirection;

public class EventHandler
{
	@SubscribeEvent
	public void onModConfigEvent(ModConfig.ModConfigEvent event)
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
		
		if(event.getEntityLiving() instanceof PlayerEntity)
		{
			PlayerEntity player = (PlayerEntity) event.getEntityLiving();
			if(player.world.isRemote)
				return;
			
			//Server Side
			PacketHandler.instance.sendTo(new MessageUpdateAttackYaw(player), ((ServerPlayerEntity)player).connection.getNetworkManager(), NetworkDirection.PLAY_TO_CLIENT);
		}
	}
}
