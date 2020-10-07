package com.charles445.damagetilt;

import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.EntityLivingBase;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class MessageUpdateAttackYaw implements IMessage
{
	private float attackedAtYaw;
	
	public MessageUpdateAttackYaw()
	{
		
	}
	
	public MessageUpdateAttackYaw(EntityLivingBase entity)
	{
		this.attackedAtYaw = entity.attackedAtYaw;
	}
	
	@Override
	public void fromBytes(ByteBuf buf)
	{
		this.attackedAtYaw = buf.readFloat();
	}
	
	@Override
	public void toBytes(ByteBuf buf)
	{
		buf.writeFloat(this.attackedAtYaw);
	}
	
	public static class Handler implements IMessageHandler<MessageUpdateAttackYaw, IMessage>
	{
		@Override
		public IMessage onMessage(MessageUpdateAttackYaw message, MessageContext ctx) 
		{
			if(ctx.side == Side.CLIENT)
			{
				Minecraft.getMinecraft().addScheduledTask(() -> 
				{
					fromMessage(message);
				});
			}
			return null;
		}
		
		@SideOnly(Side.CLIENT)
		public static void fromMessage(MessageUpdateAttackYaw message)
		{
			if (!ModConfig.damageTiltEnabled)
				return;
			Minecraft.getMinecraft().player.attackedAtYaw = message.attackedAtYaw;
		}
	}
}