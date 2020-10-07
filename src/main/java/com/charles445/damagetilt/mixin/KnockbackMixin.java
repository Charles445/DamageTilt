package com.charles445.damagetilt.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.charles445.damagetilt.KnockbackHandler;

import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;

@Mixin(LivingEntity.class)
public class KnockbackMixin
{
	@Inject(at = @At("HEAD"), method = "takeKnockback(Lnet/minecraft/entity/Entity;FDD)V")
	private void onKnockback(Entity attacker, float f, double d, double e, CallbackInfo info)
	{
		KnockbackHandler.onKnockback(this, attacker, f, d, e);
	}
}
