package com.watschman.ghostwatch;

import com.watschman.ghostwatch.event.*;
import com.watschman.ghostwatch.handler.ConfigurationHandler;
import com.watschman.ghostwatch.proxy.IProxy;
import com.watschman.ghostwatch.reference.Reference;
import com.watschman.ghostwatch.reference.configReference;
import com.watschman.ghostwatch.server.commands.WhitelistCommand;
import com.watschman.ghostwatch.utility.LogHelper;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import net.minecraftforge.common.MinecraftForge;

@Mod(modid = Reference.MOD_ID, name = Reference.MOD_NAME, version = Reference.VERSION, acceptableRemoteVersions = "*")

public class Ghostwatch
{

    @Mod.Instance(Reference.MOD_ID)
    public static Ghostwatch instance;

    @SidedProxy(clientSide = Reference.CLIENT_PROXY_CLASS, serverSide = Reference.SERVER_PROXY_CLASS)
    public static IProxy proxy;

    //PREINIT
    @Mod.EventHandler
    public void preinit(FMLPreInitializationEvent event) //hier Items Entities etc.
    {
        ConfigurationHandler.init(event.getSuggestedConfigurationFile());
        FMLCommonHandler.instance().bus().register(ConfigurationHandler.INSTANCE);
        LogHelper.info("Pre Init done");
    }

    //INIT
    @Mod.EventHandler
    public void init(FMLInitializationEvent event)
    {
        if (configReference.configValue) {
            FMLCommonHandler.instance().bus().register(new playerevent());
            MinecraftForge.EVENT_BUS.register(new attackentityevent());
            MinecraftForge.EVENT_BUS.register(new blockbreakevent());
            MinecraftForge.EVENT_BUS.register(new blockplaceevent());
            MinecraftForge.EVENT_BUS.register(new containerinteractevent());
        }
        LogHelper.info("Init done");
    }

    //Serverevent
    @Mod.EventHandler
    public void serverLoad(FMLServerStartingEvent event)
    {
        event.registerServerCommand(new WhitelistCommand());
    }

    //POSTINIT
    @Mod.EventHandler
    public void postinit(FMLPostInitializationEvent event) //wrapping
    {
        LogHelper.info("Post Init done");
    }
}
