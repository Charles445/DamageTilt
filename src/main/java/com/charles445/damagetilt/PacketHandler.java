package com.charles445.damagetilt;

import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import cpw.mods.fml.relauncher.Side;

public class PacketHandler
{
	public static final SimpleNetworkWrapper instance = NetworkRegistry.INSTANCE.newSimpleChannel(DamageTilt.MODID);
	
	public static void init()
	{
		instance.registerMessage(MessageUpdateAttackYaw.Handler.class, MessageUpdateAttackYaw.class, 0, Side.CLIENT);
	}
}
