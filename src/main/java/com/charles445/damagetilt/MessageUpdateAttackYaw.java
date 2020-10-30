package com.charles445.damagetilt;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.EntityLivingBase;

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
				Minecraft.getMinecraft().func_152344_a(new RunnableMessage(message));
			}
			
			return null;
		}
		
		@SideOnly(Side.CLIENT)
		public static void fromMessage(MessageUpdateAttackYaw message)
		{
			if (!ModConfig.damageTiltEnabled)
				return;
			Minecraft.getMinecraft().thePlayer.attackedAtYaw = message.attackedAtYaw;
		}
		
		@SideOnly(Side.CLIENT)
		public class RunnableMessage implements Runnable
		{
			MessageUpdateAttackYaw message;
			
			public RunnableMessage(MessageUpdateAttackYaw message)
			{
				this.message = message;
			}
			
			@Override
			public void run()
			{
				Handler.fromMessage(message);
			}
		}
	}
}