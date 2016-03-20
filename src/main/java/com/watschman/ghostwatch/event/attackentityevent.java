package com.watschman.ghostwatch.event;

import com.watschman.ghostwatch.reference.configReference;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.event.entity.player.AttackEntityEvent;

public class attackentityevent{

    @SubscribeEvent
    public void pre(AttackEntityEvent event) {
        if (configReference.configValue == true) {
            if (!(configReference.whitelist.contains(event.entityPlayer.getDisplayName()))) {
                event.setCanceled(true);
            }
        }
        else {
            //NOOP
        }
    }
}
