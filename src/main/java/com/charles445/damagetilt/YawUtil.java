package com.charles445.damagetilt;

import net.minecraft.entity.LivingEntity;

public class YawUtil
{
	//Utility to quickly change how to access attackedAtYaw
	
	public static float getAttackedAtYaw(LivingEntity entity)
	{
		return entity.field_6271;
	}
	
	public static void setAttackedAtYaw(LivingEntity entity, float attackedAtYaw)
	{
		entity.field_6271 = attackedAtYaw;
	}
}
