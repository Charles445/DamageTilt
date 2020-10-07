package com.charles445.damagetilt;

import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.relauncher.Side;

public class PacketHandler
{
	public static final SimpleNetworkWrapper instance = NetworkRegistry.INSTANCE.newSimpleChannel(DamageTilt.MODID);
	
	public static void init()
	{
		instance.registerMessage(MessageUpdateAttackYaw.Handler.class, MessageUpdateAttackYaw.class, 0, Side.CLIENT);
	}
}
