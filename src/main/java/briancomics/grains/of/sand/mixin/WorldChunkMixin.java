package briancomics.grains.of.sand.mixin;

import briancomics.grains.of.sand.cca.MyComponents;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.chunk.WorldChunk;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(WorldChunk.class)
public class WorldChunkMixin {
	@Inject(method = "canTickBlockEntity", at = @At("HEAD"), cancellable = true)
	private void doNotTickBlockEntities (BlockPos pos, CallbackInfoReturnable<Boolean> cir) {
		ClientWorld world = MinecraftClient.getInstance().world;
		if (world == null)
			return;
		if (MyComponents.TIME_COMPONENT.get(world).shouldSkipTick())
			cir.setReturnValue(false);
	}
}
