package com.watschman.ghostwatch.event;

import com.watschman.ghostwatch.reference.configReference;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.event.world.BlockEvent;

public class blockplaceevent {
    @SubscribeEvent
    public void onBlockPlacerEvent(BlockEvent.PlaceEvent event){
        if (!(configReference.whitelist.contains(event.player.getDisplayName()))) {
            event.setCanceled(true);
        }
    }
}
