package com.watschman.ghostwatch.client.handler;

import com.watschman.ghostwatch.client.settings.KeyBindings;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.InputEvent;

public class mykeyhandler{
    @SubscribeEvent
    public void onKeyPressed(InputEvent.KeyInputEvent event){
        if (KeyBindings.menu.isPressed()){
        }
    }
}
