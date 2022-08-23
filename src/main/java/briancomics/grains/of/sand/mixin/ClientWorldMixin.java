package briancomics.grains.of.sand.mixin;

import briancomics.grains.of.sand.cca.MyComponents;
import briancomics.grains.of.sand.cca.TimeComponentInterface;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.entity.Entity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ClientWorld.class)
public class ClientWorldMixin {
	@Inject(method = "randomBlockDisplayTick", at = @At("HEAD"), cancellable = true)
	private void doNotSpawnBlockParticle (CallbackInfo ci) {
		ClientWorld world = MinecraftClient.getInstance().world;
		if (world == null)
			return;
		if (MyComponents.TIME_COMPONENT.get(world).shouldSkipTick())
			ci.cancel();
	}

	@Inject(method = "tickEntity", at = @At("HEAD"), cancellable = true)
	private void doNotTickEntity (Entity entity, CallbackInfo ci) {
		ClientWorld world = MinecraftClient.getInstance().world;
		if (world == null)
			return;
		TimeComponentInterface timeComponent = MyComponents.TIME_COMPONENT.get(world);
		if (timeComponent.shouldSkipTick() && timeComponent.canEntityBeManipulated(entity))
			ci.cancel();
	}

	@Inject(method = "tickPassenger", at = @At("HEAD"), cancellable = true)
	private void doNotTickPassenger (Entity vehicle, Entity passenger, CallbackInfo ci) {
		ClientWorld world = MinecraftClient.getInstance().world;
		if (world == null)
			return;
		TimeComponentInterface timeComponent = MyComponents.TIME_COMPONENT.get(world);
		if (timeComponent.shouldSkipTick() && timeComponent.canEntityBeManipulated(passenger))
			ci.cancel();
	}

	@Inject(method = "tickTime", at = @At("HEAD"), cancellable = true)
	private void doNotTickDayNight (CallbackInfo ci) {
		ClientWorld world = MinecraftClient.getInstance().world;
		if (world == null)
			return;
		if (MyComponents.TIME_COMPONENT.get(world).shouldSkipTick())
			ci.cancel();
	}
}
