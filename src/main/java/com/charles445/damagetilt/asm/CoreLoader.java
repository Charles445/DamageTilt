package com.charles445.damagetilt.asm;

import java.util.Map;

import com.charles445.damagetilt.asm.helper.ObfHelper;

import net.minecraftforge.fml.relauncher.IFMLLoadingPlugin;

@IFMLLoadingPlugin.Name("DamageTilt ASM")
@IFMLLoadingPlugin.SortingIndex(1002)
@IFMLLoadingPlugin.TransformerExclusions({ "com.charles445.damagetilt.asm", "com.charles445.damagetilt.asm." })

public class CoreLoader implements IFMLLoadingPlugin
{
	//
	// IFMLLoadingPlugin
	// 

	@Override
	public String[] getASMTransformerClass()
	{
		return new String[] { "com.charles445.damagetilt.asm.DamageTiltASM" };
	}

	@Override
	public void injectData(Map<String, Object> data)
	{
		ObfHelper.setObfuscated((Boolean) data.get("runtimeDeobfuscationEnabled"));
		ObfHelper.setRunsAfterDeobfRemapper(true);
	}

	@Override
	public String getModContainerClass()
	{
		return null;
	}

	@Override
	public String getSetupClass()
	{
		return null;
	}

	@Override
	public String getAccessTransformerClass()
	{
		return null;
	}
}