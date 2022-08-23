package briancomics.grains.of.sand;

import briancomics.grains.of.sand.init.item.TimeSorobanItem;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.Rarity;
import net.minecraft.util.registry.Registry;

public class GrainsOfSandMain implements ModInitializer {
	public static final String MODID = "sand";

	public static final TimeSorobanItem TIME_SOROBAN_ITEM = new TimeSorobanItem(new FabricItemSettings().group(ItemGroup.MISC).maxCount(1).fireproof().rarity(Rarity.RARE));

	@Override
	public void onInitialize () {
		Registry.register(Registry.ITEM, new Identifier(MODID, "time_soroban"), TIME_SOROBAN_ITEM);
	}
}
