package briancomics.grains.of.sand.mixin;

import briancomics.grains.of.sand.cca.MyComponents;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.entity.Entity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Entity.class)
public class EntityMixin {
	@Inject(method = "onBubbleColumnCollision", at = @At("HEAD"), cancellable = true)
	private void doNotGetPushedAroundByBubbles (CallbackInfo ci) {
		ClientWorld world = MinecraftClient.getInstance().world;
		if (world == null)
			return;
		if (MyComponents.TIME_COMPONENT.get(world).shouldSkipTick())
			ci.cancel();
	}
}
