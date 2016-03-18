package com.watschman.ghostwatch.handler;

import com.watschman.ghostwatch.reference.Reference;
import com.watschman.ghostwatch.reference.configReference;
import cpw.mods.fml.client.event.ConfigChangedEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.config.Property;

import java.io.File;
import java.util.Arrays;

public class ConfigurationHandler{

    public static Configuration configuration;

    public static void init(File configFile){
        if (configuration == null){
            configuration = new Configuration(configFile);
            loadConfiguration();
        }
    }
    @SubscribeEvent
    public void onConfigurationChangedEvent (ConfigChangedEvent.OnConfigChangedEvent event){
        if (event.modID.equalsIgnoreCase(Reference.MOD_ID)){
            //RESYNC
            loadConfiguration();
        }
    }
    private static void loadConfiguration(){
        configReference.configValue = configuration.getBoolean("enabled", Configuration.CATEGORY_GENERAL, true, "defines if this Mod is enabled or not");
        Property whitelist = configuration.get(Configuration.CATEGORY_GENERAL, "whitelist", new String[]{"watschman"});
        whitelist.comment = "Add player names to permit building in Survival";
        configReference.whitelist.addAll(Arrays.asList(whitelist.getStringList()));
        if (configuration.hasChanged()){
            configuration.save();
        }
    }
}
