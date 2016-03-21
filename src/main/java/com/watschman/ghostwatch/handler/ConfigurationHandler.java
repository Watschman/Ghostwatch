package com.watschman.ghostwatch.handler;

import com.watschman.ghostwatch.reference.Reference;
import com.watschman.ghostwatch.reference.configReference;
import com.watschman.ghostwatch.server.commands.WhitelistCommand;
import com.watschman.ghostwatch.utility.LogHelper;
import cpw.mods.fml.client.event.ConfigChangedEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
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
        configReference.configValue = configuration.getBoolean("enabled", Configuration.CATEGORY_GENERAL, false, "defines if this Mod is enabled or not");
        Property whitelist = configuration.get(Configuration.CATEGORY_GENERAL, "whitelist", new String[]{"Watschman"});
        whitelist.comment = "Add player names to permit building in Survival";
        whitelist.setRequiresMcRestart(false).setRequiresWorldRestart(false);
        if (configReference.whitelist.isEmpty()){
            configReference.whitelist.addAll(Arrays.asList(whitelist.getStringList()));
        }
        String[] jon = new String[configReference.whitelist.size()];
        configReference.whitelist.toArray(jon);
        whitelist.setValues(jon);
        LogHelper.info("Jon:" + "----" + Arrays.toString(jon) + "----");
        LogHelper.info("whitelist config:" + "----" + Arrays.toString(whitelist.getStringList()) + "----");
        LogHelper.info("whitelist set:" + "----" + (configReference.whitelist) + "----");
        if (configuration.hasChanged()) {
            configuration.save();
        }
    }
    @SubscribeEvent
    public void onConfigurationChangedEvent (ConfigChangedEvent.OnConfigChangedEvent event){
        if (event.modID.equalsIgnoreCase(Reference.MOD_ID)){
            loadConfiguration();
        }
    }
}
