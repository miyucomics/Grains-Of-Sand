package briancomics.grains.of.sand.cca;

import briancomics.grains.of.sand.enums.TimeStates;
import dev.onyxstudios.cca.api.v3.component.ComponentV3;
import dev.onyxstudios.cca.api.v3.component.sync.AutoSyncedComponent;
import dev.onyxstudios.cca.api.v3.component.tick.ServerTickingComponent;
import net.minecraft.entity.Entity;

import java.util.UUID;

public interface TimeComponentInterface extends ComponentV3, AutoSyncedComponent, ServerTickingComponent {
	TimeStates getTimeState ();
	void setTimeState (TimeStates state);
	void setStoppedTickDelta (float tickDelta);
	void setSlowRate (float speed);

	float getSlowRate ();
	float adjustTickDelta (float tickDelta);
	boolean shouldSkipTick ();
	boolean canEntityBeManipulated (Entity entity);
}
