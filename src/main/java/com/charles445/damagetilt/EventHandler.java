package com.charles445.damagetilt;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;

public class EventHandler
{
	public static void onKnockback(EntityLivingBase entity)
	{
		if(!ModConfig.damageTiltEnabled)
			return;
		
		if(entity instanceof EntityPlayer)
		{
			EntityPlayer player = (EntityPlayer) entity;
			if(player.worldObj.isRemote)
				return;
			
			//Server Side
			PacketHandler.instance.sendTo(new MessageUpdateAttackYaw(player), (EntityPlayerMP) player);
		}
	}
}
