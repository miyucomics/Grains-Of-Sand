package briancomics.grains.of.sand.cca;

import dev.onyxstudios.cca.api.v3.component.ComponentKey;
import dev.onyxstudios.cca.api.v3.component.ComponentRegistryV3;
import dev.onyxstudios.cca.api.v3.world.WorldComponentFactoryRegistry;
import dev.onyxstudios.cca.api.v3.world.WorldComponentInitializer;
import net.minecraft.util.Identifier;

public final class MyComponents implements WorldComponentInitializer {
	public static final ComponentKey<TimeComponentInterface> TIME_COMPONENT = ComponentRegistryV3.INSTANCE.getOrCreate(new Identifier("sand", "time_component"), TimeComponentInterface.class);

	@Override
	public void registerWorldComponentFactories (WorldComponentFactoryRegistry registry) {
		registry.register(TIME_COMPONENT, TimeComponent::new);
	}
}
