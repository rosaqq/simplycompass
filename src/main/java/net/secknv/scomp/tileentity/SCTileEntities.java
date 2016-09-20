package net.secknv.scomp.tileentity;

import net.minecraftforge.fml.common.registry.GameRegistry;

public class SCTileEntities {

	public static void register() {

        GameRegistry.registerTileEntity(TileEntityCoil.class, "scomp.coil");
    }
}
