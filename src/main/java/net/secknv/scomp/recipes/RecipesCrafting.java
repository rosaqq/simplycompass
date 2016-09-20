package net.secknv.scomp.recipes;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.secknv.scomp.block.SCBlocks;
import net.secknv.scomp.item.SCItems;


public class RecipesCrafting {
	
	public static void registerRecipes() {

		GameRegistry.addRecipe(new ItemStack(SCBlocks.COIL), "AAA", "ABA", "AAA", 'A', Items.IRON_INGOT, 'B', Blocks.PLANKS);
		GameRegistry.addRecipe(new ItemStack(SCItems.RESONANT_COMPASS), "A", "B", "A", 'A', Items.GOLD_INGOT, 'B', Items.COMPASS);
	}
}
