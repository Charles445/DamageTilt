package com.charles445.damagetilt;

import net.minecraft.entity.LivingEntity;

public class YawUtil
{
	//Utility to quickly change how to access attackedAtYaw
	
	public static float getAttackedAtYaw(LivingEntity entity)
	{
		//I don't think "knockbackVelocity" is the right name for this...
		return entity.knockbackVelocity;
	}
	
	public static void setAttackedAtYaw(LivingEntity entity, float attackedAtYaw)
	{
		entity.knockbackVelocity = attackedAtYaw;
	}
}
