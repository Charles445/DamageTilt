package com.charles445.damagetilt;

import org.apache.commons.lang3.tuple.Pair;

import com.electronwill.nightconfig.core.file.CommentedFileConfig;
import com.electronwill.nightconfig.core.io.WritingMode;

import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.fml.loading.FMLPaths;

public class ModConfigManager
{
	public static final ForgeConfigSpec COMMON_SPEC;
	public static final CommonConfig COMMON_CONFIG;
	
	static
	{	
		Pair<CommonConfig, ForgeConfigSpec> pair = new ForgeConfigSpec.Builder().configure(CommonConfig::new);
		COMMON_CONFIG = pair.getLeft();
		COMMON_SPEC = pair.getRight();
	}
	
	protected static class CommonConfig
	{
		protected final ForgeConfigSpec.ConfigValue<Boolean> damageTiltEnabled;
		
		protected CommonConfig(final ForgeConfigSpec.Builder builder)
		{
			//damageTiltEnabled
			builder.push("general");
			
			damageTiltEnabled = builder
					.comment("Whether the damage tilt effect is enabled")
					.define("damageTiltEnabled", true);
			
			builder.pop();
		}
		
	}
	
	public static void loadAll()
	{
		CommentedFileConfig commonData = CommentedFileConfig.builder(FMLPaths.CONFIGDIR.get()
				.resolve(DamageTilt.MODID+"-common.toml"))
				.sync()
				.autosave()
				.writingMode(WritingMode.REPLACE)
				.build();
		commonData.load();
		COMMON_SPEC.setConfig(commonData);
				
	}
	
	public static void updateCommon()
	{
		//COMMON_SPEC
		TiltConfig.damageTiltEnabled = COMMON_CONFIG.damageTiltEnabled.get();
	}
}
