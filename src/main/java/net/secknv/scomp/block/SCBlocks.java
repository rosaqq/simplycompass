package net.secknv.scomp.block;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.secknv.scomp.reference.Reference;

@GameRegistry.ObjectHolder(Reference.MOD_ID)
public class SCBlocks {
	

	public static Block COIL = new BlockCoil();
	
	public static void register() {

		registerBlock(COIL);
	}
	
	public static void registerBlock(Block block) {

		GameRegistry.register(block);
		ItemBlock item = new ItemBlock(block);
		item.setRegistryName(block.getRegistryName());
		GameRegistry.register(item);
	}
	
	public static void registerRenders() {

		registerRender(COIL);
	}

	public static void registerRender(Block block) {

		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(Item.getItemFromBlock(block), 0,
				new ModelResourceLocation(block.getRegistryName(), "inventory"));
	}
}
