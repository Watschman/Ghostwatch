package com.watschman.ghostwatch.handler;

import com.watschman.ghostwatch.event.*;
import com.watschman.ghostwatch.reference.Reference;
import com.watschman.ghostwatch.reference.configReference;
import com.watschman.ghostwatch.utility.LogHelper;
import cpw.mods.fml.client.event.ConfigChangedEvent;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.config.Property;

import java.io.File;
import java.util.*;

public class ConfigurationHandler{
    public static final ConfigurationHandler INSTANCE = new ConfigurationHandler();
    public static Configuration configuration;
    public static void init(File configFile){
        if (configuration == null){
            configuration = new Configuration(configFile);
            INSTANCE.loadConfiguration();
        }
    }
    public static void loadConfiguration(){
        Property isenabled = configuration.get(Configuration.CATEGORY_GENERAL, "enabled",  false);
        Property whitelist = configuration.get(Configuration.CATEGORY_GENERAL, "whitelist", new String[]{"Watschman"});
        whitelist.comment = "Add player names to permit building in Survival";
        isenabled.comment = "defines if this Mod is enabled or not";
        isenabled.setRequiresMcRestart(false).setRequiresWorldRestart(false);
        isenabled.setValue(configReference.configValue);
        whitelist.setRequiresMcRestart(false).setRequiresWorldRestart(false);
        if (configReference.whitelist.isEmpty()){
            configReference.whitelist.addAll(Arrays.asList(whitelist.getStringList()));
        }
        String[] jon = new String[configReference.whitelist.size()];
        configReference.whitelist.toArray(jon);
        whitelist.setValues(jon);
        if (configuration.hasChanged()) {
            configuration.save();
        }
        if (configReference.configValue) {
            MinecraftForge.EVENT_BUS.register(new attackentityevent());
            MinecraftForge.EVENT_BUS.register(new blockbreakevent());
            MinecraftForge.EVENT_BUS.register(new blockplaceevent());
            MinecraftForge.EVENT_BUS.register(new containerinteractevent());
        }
    }
    @SubscribeEvent
    public void onConfigurationChangedEvent (ConfigChangedEvent.OnConfigChangedEvent event){
        if (event.modID.equalsIgnoreCase(Reference.MOD_ID)){
            loadConfiguration();
        }
    }
}
