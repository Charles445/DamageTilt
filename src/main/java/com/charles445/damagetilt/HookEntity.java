package com.charles445.damagetilt;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;

public class HookEntity
{
	public static void setVelocity(Entity entity, double x, double y, double z)
	{
		if(!ModConfig.damageTiltEnabled)
			return;
		
		//This is run before motion is locally set
		
		if(entity != null)
		{
			EntityPlayer player = Minecraft.getMinecraft().thePlayer;
			if(player != null && entity.equals(player))
			{
				//Set the value
				float result = (float)(Math.atan2(player.motionZ - z, player.motionX - x) * (180D / Math.PI) - (double)player.rotationYaw);
				
				if(Float.isFinite(result))
					player.attackedAtYaw = result;
			}
		}
	}
}
