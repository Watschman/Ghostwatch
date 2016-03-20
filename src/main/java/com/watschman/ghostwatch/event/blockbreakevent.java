package com.watschman.ghostwatch.event;

import com.watschman.ghostwatch.reference.configReference;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.event.world.BlockEvent;

public class blockbreakevent {

    @SubscribeEvent
    public void pre(BlockEvent.BreakEvent event) {
        if (configReference.configValue == true) {
            if (!(configReference.whitelist.contains(event.getPlayer().getDisplayName()))) {
                event.setCanceled(true);
            }
        }
        else {
            //NOOP
        }
    }
}
