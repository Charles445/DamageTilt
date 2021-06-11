package com.charles445.damagetilt.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.charles445.damagetilt.KnockbackHandler;

import net.minecraft.entity.LivingEntity;

@Mixin(LivingEntity.class)
public class KnockbackMixin
{
	@Inject(at = @At("HEAD"), method = "takeKnockback(DDD)V")
	private void onKnockback(double f, double d, double e, CallbackInfo info)
	{
		KnockbackHandler.onKnockback(this, f, d, e);
	}
}
