package com.charles445.damagetilt;

import java.util.function.Supplier;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.LivingEntity;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.network.NetworkDirection;
import net.minecraftforge.fml.network.NetworkEvent;

public class MessageUpdateAttackYaw
{
	public float attackedAtYaw;
	
	public MessageUpdateAttackYaw()
	{
		
	}
	
	public MessageUpdateAttackYaw(float value)
	{
		this.attackedAtYaw = value;
	}
	
	public MessageUpdateAttackYaw(LivingEntity entity)
	{
		this.attackedAtYaw = entity.attackedAtYaw;
	}
	
	public static MessageUpdateAttackYaw encode(MessageUpdateAttackYaw message, PacketBuffer packet)
	{
		packet.writeFloat(message.attackedAtYaw);
		return message;
	}
	
	public static MessageUpdateAttackYaw decode(PacketBuffer packet)
	{
		return new MessageUpdateAttackYaw(packet.readFloat());
	}
	
	public static void handle(MessageUpdateAttackYaw message, Supplier<NetworkEvent.Context> ctx)
	{
		ctx.get().setPacketHandled(true);
		if(ctx.get().getDirection() != NetworkDirection.PLAY_TO_CLIENT)
			return;
		
		Minecraft.getInstance().deferTask(() -> 
		{
			fromMessage(message);
		});
	}
	
	@OnlyIn(Dist.CLIENT)
	public static void fromMessage(MessageUpdateAttackYaw message)
	{
		if (!TiltConfig.damageTiltEnabled)
			return;
		Minecraft.getInstance().player.attackedAtYaw = message.attackedAtYaw;
	}
}
