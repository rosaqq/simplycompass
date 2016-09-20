package net.secknv.scomp.proxy;

import net.secknv.scomp.block.SCBlocks;
import net.secknv.scomp.item.SCItems;

public class ClientProxy extends CommonProxy {
	
	@Override
	public void registerRenders() {

		SCBlocks.registerRenders();
        SCItems.registerRenders();
	}

}
