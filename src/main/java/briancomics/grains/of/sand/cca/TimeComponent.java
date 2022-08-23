package briancomics.grains.of.sand.cca;

import briancomics.grains.of.sand.enums.TimeStates;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.NbtCompound;

import java.util.UUID;

class TimeComponent implements TimeComponentInterface {
	private final Object world;
	private TimeStates timeState = TimeStates.NORMAL;
	private UUID timeManipulator = UUID.randomUUID();
	private float stoppedTickDelta = 0;
	private int ticksSinceStart = 0;
	private float slowRate = 5;

	public TimeComponent (Object world) {
		this.world = world;
	}
	@Override public void readFromNbt (NbtCompound tag) {
		this.timeState = TimeStates.values()[tag.getInt("timeState")];
		this.stoppedTickDelta = tag.getFloat("stoppedTickDelta");
		this.ticksSinceStart = tag.getInt("ticksSinceStart");
	}
	@Override public void writeToNbt (NbtCompound tag) {
		tag.putInt("timeState", this.timeState.ordinal());
		tag.putFloat("stoppedTickDelta", this.stoppedTickDelta);
		tag.putInt("ticksSinceStart", this.ticksSinceStart);
	}

	@Override public TimeStates getTimeState () {
		return this.timeState;
	}
	@Override public void setTimeState (TimeStates state) {
		this.timeState = state;
	}
	@Override public void setStoppedTickDelta (float tickDelta) {
		this.stoppedTickDelta = tickDelta;
	}
	@Override public void setSlowRate (float speed) {
		slowRate = speed;
	}
	@Override public float getSlowRate () {
		return slowRate;
	}

	public float adjustTickDelta (float tickDelta) {
		if (this.getTimeState() == TimeStates.NORMAL)
			return tickDelta;
		if (this.slowRate == 0)
			return stoppedTickDelta;
		return ((this.ticksSinceStart % this.slowRate) / this.slowRate) + (tickDelta / this.slowRate);
	}
	public boolean shouldSkipTick () {
		return this.timeState == TimeStates.MANIPULATED && this.ticksSinceStart % this.slowRate != 0;
	}
	public boolean canEntityBeManipulated (Entity entity) {
		return !(entity instanceof PlayerEntity);
	}

	@Override public void serverTick () {
		this.ticksSinceStart++;
		MyComponents.TIME_COMPONENT.sync(world);
	}
}
