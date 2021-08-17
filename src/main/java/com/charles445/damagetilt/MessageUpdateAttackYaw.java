package com.charles445.damagetilt;

import java.util.function.Supplier;

import net.minecraft.client.Minecraft;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.LivingEntity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fmllegacy.network.NetworkDirection;
import net.minecraftforge.fmllegacy.network.NetworkEvent;

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
		this.attackedAtYaw = entity.hurtDir;
	}
	
	public static MessageUpdateAttackYaw encode(MessageUpdateAttackYaw message, FriendlyByteBuf packet)
	{
		packet.writeFloat(message.attackedAtYaw);
		return message;
	}
	
	public static MessageUpdateAttackYaw decode(FriendlyByteBuf packet)
	{
		return new MessageUpdateAttackYaw(packet.readFloat());
	}
	
	public static void handle(MessageUpdateAttackYaw message, Supplier<NetworkEvent.Context> ctx)
	{
		ctx.get().setPacketHandled(true);
		if(ctx.get().getDirection() != NetworkDirection.PLAY_TO_CLIENT)
			return;
		
		ctx.get().enqueueWork(() -> 
		{
			fromMessage(message);
		});
	}
	
	@OnlyIn(Dist.CLIENT)
	public static void fromMessage(MessageUpdateAttackYaw message)
	{
		if (!TiltConfig.damageTiltEnabled)
			return;
		Minecraft.getInstance().player.hurtDir = message.attackedAtYaw;
	}
}
