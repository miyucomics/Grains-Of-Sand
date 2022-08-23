package briancomics.grains.of.sand.init.item;

import briancomics.grains.of.sand.cca.MyComponents;
import briancomics.grains.of.sand.cca.TimeComponentInterface;
import briancomics.grains.of.sand.enums.TimeStates;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

public class TimeSorobanItem extends Item {
	public TimeSorobanItem(Settings settings) {
		super(settings);
	}

	@Override
	public TypedActionResult<ItemStack> use (World world, PlayerEntity user, Hand hand) {
		if (!world.isClient()) {
			TimeComponentInterface timeComponent = MyComponents.TIME_COMPONENT.get(world);
			timeComponent.setTimeState(timeComponent.getTimeState() == TimeStates.MANIPULATED ? TimeStates.NORMAL : TimeStates.MANIPULATED);
			MyComponents.TIME_COMPONENT.sync(world);
		}
		return TypedActionResult.success(user.getStackInHand(hand));
	}
}
