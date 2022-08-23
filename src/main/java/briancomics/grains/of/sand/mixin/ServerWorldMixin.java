package briancomics.grains.of.sand.mixin;

import briancomics.grains.of.sand.cca.MyComponents;
import briancomics.grains.of.sand.cca.TimeComponentInterface;
import net.minecraft.block.Block;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.entity.Entity;
import net.minecraft.fluid.Fluid;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.chunk.WorldChunk;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ServerWorld.class)
public class ServerWorldMixin {
	@Inject(method = "tickBlock", at = @At("HEAD"), cancellable = true)
	private void doNotTickBlock (BlockPos pos, Block block, CallbackInfo ci) {
		ClientWorld world = MinecraftClient.getInstance().world;
		if (world == null)
			return;
		if (MyComponents.TIME_COMPONENT.get(world).shouldSkipTick())
			ci.cancel();
	}

	@Inject(method = "tickChunk", at = @At("HEAD"), cancellable = true)
	private void tickChunk (WorldChunk chunk, int randomTickSpeed, CallbackInfo ci) {
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

	@Inject(method = "tickFluid", at = @At("HEAD"), cancellable = true)
	private void doNotTickFluid (BlockPos pos, @Nullable Fluid fluid, CallbackInfo ci) {
		ClientWorld world = MinecraftClient.getInstance().world;
		if (world == null)
			return;
		if (MyComponents.TIME_COMPONENT.get(world).shouldSkipTick())
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

	@Inject(method = "tickSpawners", at = @At("HEAD"), cancellable = true)
	private void doNotTickSpawners (boolean spawnMonsters, boolean spawnAnimals, CallbackInfo ci) {
		ClientWorld world = MinecraftClient.getInstance().world;
		if (world == null)
			return;
		if (MyComponents.TIME_COMPONENT.get(world).shouldSkipTick())
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

	@Inject(method = "tickWeather", at = @At("HEAD"), cancellable = true)
	private void doNotTickWeather (CallbackInfo ci) {
		ClientWorld world = MinecraftClient.getInstance().world;
		if (world == null)
			return;
		if (MyComponents.TIME_COMPONENT.get(world).shouldSkipTick())
			ci.cancel();
	}
}
