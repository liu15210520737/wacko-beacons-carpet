package net.scoobis.carpet.mixin;

import net.minecraft.core.Holder;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.level.block.entity.BeaconBlockEntity;
import net.scoobis.carpet.WackoBeaconSettings;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(BeaconBlockEntity.class)
public class BeaconBlockEntityMixin {
	/**
	 * 26.2 新增的服务端校验：次要效果需 4 层、效果层级不得超过塔层数、
	 * 主效果不能是第 4 层效果。规则开启时跳过该校验，恢复 1.21.11 及以前
	 * 仅由 filterEffect 过滤「是否信标效果」的旧行为。
	 */
	@Inject(method = "validateEffects", at = @At("HEAD"), cancellable = true)
	private static void wacko$restoreLegacyBeaconEffects(Holder<MobEffect> primary, Holder<MobEffect> secondary, int levels, CallbackInfoReturnable<Boolean> cir) {
		if (WackoBeaconSettings.wackoBeacons) {
			cir.setReturnValue(true);
		}
	}
}
