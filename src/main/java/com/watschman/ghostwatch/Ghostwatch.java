package com.watschman.ghostwatch;

import net.minecraft.init.Blocks;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.event.FMLInitializationEvent;

@Mod(modid = Ghostwatch.MODID, name = "Ghostwatch", version = Ghostwatch.VERSION)
public class Ghostwatch
{
    public static final String MODID = "Ghostwatch";
    public static final String VERSION = "1.7.10-1.0";
    @Mod.Instance("Ghostwatch")
    public static Ghostwatch instance;

    @EventHandler
    public void preinit(FMLInitializationEvent event) //hier Items Entities etc.
    {
    }
    @EventHandler
    public void init(FMLInitializationEvent event)
    {
    }
    @EventHandler
    public void postinit(FMLInitializationEvent event) //wrapping
    {
    }
}
