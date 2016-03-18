package com.watschman.ghostwatch.event;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraftforge.client.event.RenderPlayerEvent;

public class PlayerRendering {
    @SubscribeEvent
    @SideOnly(Side.CLIENT)
    public void  pre(RenderPlayerEvent.Pre event)
    {
        event.setCanceled(true);
    }
}
