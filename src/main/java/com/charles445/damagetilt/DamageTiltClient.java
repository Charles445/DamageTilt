package com.charles445.damagetilt;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayConnectionEvents;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking.PlayChannelHandler;
import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayNetworkHandler;
import net.minecraft.network.PacketByteBuf;

public class DamageTiltClient implements ClientModInitializer
{
	@Override
	public void onInitializeClient()
	{
		ClientPlayConnectionEvents.INIT.register(new ClientPlayConnectionEvents.Init()
		{
			@Override
			public void onPlayInit(ClientPlayNetworkHandler handler, MinecraftClient client)
			{
				ClientPlayNetworking.registerReceiver(DamageTilt.PLAY_YAW_PACKET_ID, new ClientTiltReceiver());
			}
		});
		
		ClientPlayConnectionEvents.DISCONNECT.register(new ClientPlayConnectionEvents.Disconnect()
		{
			@Override
			public void onPlayDisconnect(ClientPlayNetworkHandler handler, MinecraftClient client)
			{
				ClientPlayNetworking.unregisterReceiver(DamageTilt.PLAY_YAW_PACKET_ID);
			}
		});
	}
	
	@Environment(EnvType.CLIENT)
	public static class ClientTiltReceiver implements PlayChannelHandler
	{
		@Override
		public void receive(MinecraftClient client, ClientPlayNetworkHandler handler, PacketByteBuf buf, PacketSender responseSender)
		{
			float newYaw = buf.readFloat();
			client.execute(() -> {
				if(client.player != null)
					YawUtil.setAttackedAtYaw(client.player, newYaw);
			});
		}
	}
}
