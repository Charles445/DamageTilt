package com.charles445.damagetilt;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraftforge.event.entity.living.LivingKnockBackEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class EventHandler
{
	@SubscribeEvent
	public void onKnockback(LivingKnockBackEvent event)
	{
		if(!ModConfig.damageTiltEnabled)
			return;
		
		if(event.getEntityLiving() instanceof EntityPlayer)
		{
			EntityPlayer player = (EntityPlayer) event.getEntityLiving();
			if(player.world.isRemote)
				return;
			
			//Server Side
			PacketHandler.instance.sendTo(new MessageUpdateAttackYaw(player), (EntityPlayerMP) player);
		}
	}
}
