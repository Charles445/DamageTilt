package com.charles445.damagetilt;

import io.netty.buffer.Unpooled;
import net.fabricmc.fabric.api.network.ServerSidePacketRegistry;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.PacketByteBuf;
import net.minecraft.server.network.ServerPlayerEntity;

public class KnockbackHandler
{
	public static void onKnockback(Object entity, Entity attacker, float f, double d, double e)
	{
		if(entity instanceof PlayerEntity)
		{
			PlayerEntity player = (PlayerEntity)entity;
			if(!player.world.isClient && player instanceof ServerPlayerEntity)
			{
				//Server side, send packet
				PacketByteBuf data = new PacketByteBuf(Unpooled.buffer());
				data.writeFloat(YawUtil.getAttackedAtYaw(player));
				
				ServerSidePacketRegistry.INSTANCE.sendToPlayer(player, DamageTilt.PLAY_YAW_PACKET_ID, data);
			}	
		}
	}
}
