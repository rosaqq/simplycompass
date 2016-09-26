/*
 * MIT License
 *
 * Copyright (c) 2016 secknv
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */


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
		GameRegistry.addShapelessRecipe(new ItemStack(SCItems.RESONANT_COMPASS), SCItems.RESONANT_COMPASS);
	}
}
