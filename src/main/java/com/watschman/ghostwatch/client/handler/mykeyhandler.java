package com.watschman.ghostwatch.client.handler;

import com.watschman.ghostwatch.client.settings.KeyBindings;
import com.watschman.ghostwatch.event.containerinteractevent;
import cpw.mods.fml.common.eventhandler.Event;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.InputEvent;
import net.minecraftforge.event.entity.player.PlayerOpenContainerEvent;

public class mykeyhandler{
    @SubscribeEvent
    public void onKeyPressed(InputEvent.KeyInputEvent event){
        if (KeyBindings.menu.isPressed()){
        }
    }
}
