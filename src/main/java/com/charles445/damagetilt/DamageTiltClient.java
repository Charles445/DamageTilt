package com.charles445.damagetilt;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.network.ClientSidePacketRegistry;
import net.fabricmc.fabric.api.network.PacketContext;
import net.minecraft.util.PacketByteBuf;

public class DamageTiltClient implements ClientModInitializer
{
	@Override
	public void onInitializeClient()
	{
		ClientSidePacketRegistry.INSTANCE.register(DamageTilt.PLAY_YAW_PACKET_ID, DamageTiltClient::handleKnockbackPacket);
	}
	
	public static void handleKnockbackPacket(PacketContext context, PacketByteBuf buf)
	{
		float newYaw = buf.readFloat();
		
		context.getTaskQueue().execute(()-> {
			YawUtil.setAttackedAtYaw(context.getPlayer(), newYaw);
		});
	}
}
