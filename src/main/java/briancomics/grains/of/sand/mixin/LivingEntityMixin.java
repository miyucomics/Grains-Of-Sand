package briancomics.grains.of.sand.mixin;

import briancomics.grains.of.sand.cca.MyComponents;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.LivingEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(LivingEntity.class)
public class LivingEntityMixin {
	@Redirect(method = "travel", at = @At(value = "INVOKE", target = "Lnet/minecraft/enchantment/EnchantmentHelper;getDepthStrider(Lnet/minecraft/entity/LivingEntity;)I"))
	private int doNotGetPushedAroundByWater (LivingEntity entity) {
		ClientWorld world = MinecraftClient.getInstance().world;
		if (world == null)
			return EnchantmentHelper.getDepthStrider(entity);
		if (MyComponents.TIME_COMPONENT.get(world).shouldSkipTick())
			return 3;
		return EnchantmentHelper.getDepthStrider(entity);
	}
}
