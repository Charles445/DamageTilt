package com.charles445.damagetilt;

import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.simple.SimpleChannel;

public class PacketHandler
{
	private static final String PROTOCOL_VERSION = "1";
	public static final SimpleChannel instance = NetworkRegistry.newSimpleChannel(
			new ResourceLocation(DamageTilt.MODID, "main"),
			() -> PROTOCOL_VERSION,
			PROTOCOL_VERSION::equals,
			PROTOCOL_VERSION::equals
	);
	
	public static void init()
	{
		instance.registerMessage(0, MessageUpdateAttackYaw.class, MessageUpdateAttackYaw::encode, MessageUpdateAttackYaw::decode, MessageUpdateAttackYaw::handle);
	}
}
