package com.watschman.ghostwatch.event;

import com.watschman.ghostwatch.reference.configReference;
import cpw.mods.fml.common.eventhandler.Event;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.event.entity.player.PlayerOpenContainerEvent;

public class containerinteractevent {
    @SubscribeEvent
    public void pre(PlayerOpenContainerEvent event) {
        if (configReference.configValue == true) {
            if (!(configReference.whitelist.contains(event.entityPlayer.getDisplayName()))) {
                event.setResult(Event.Result.DENY);

//            get UI working


//            if (event.entityPlayer.openGui() = GuiIngameMenu){
//                event.setResult(Event.Result.ALLOW);
//            }
            } else {
                event.setResult(Event.Result.DEFAULT);
            }
        }
        else {
            //NOOP
        }
    }
}
