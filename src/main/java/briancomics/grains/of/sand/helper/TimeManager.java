package briancomics.grains.of.sand.helper;

import net.minecraft.entity.Entity;

import java.util.UUID;

public class TimeManager {
	public static boolean shouldTick = true;
	public static float frozenTickDelta = 0;
	public static UUID initiator = null;

	public static boolean canBeFrozen (Entity entity) {
		return entity.getUuid() != TimeManager.initiator;
	}
}
