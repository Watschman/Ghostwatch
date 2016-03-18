package com.watschman.ghostwatch.event;

import com.watschman.ghostwatch.reference.configReference;
import com.watschman.ghostwatch.utility.LogHelper;
import cpw.mods.fml.common.eventhandler.ASMEventHandler;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent;
import org.lwjgl.opengl.GL11;
import net.minecraft.world.WorldSettings;


public class playerevent {
    @SubscribeEvent
    public void onPlayerLogin(PlayerEvent.PlayerLoggedInEvent event){
        if (!(configReference.whitelist.contains(event.player.getDisplayName()))){
            LogHelper.info(event.player.getDisplayName() + " ist nicht gewhitelistet");
            int i = 0;
            WorldSettings.getGameTypeById(i);
            if (i != 2){
                event.player.setGameType(WorldSettings.GameType.ADVENTURE);
                //make Player invisible and set them fly
            }
        }
        else{
            LogHelper.info(event.player.getDisplayName() + " ist gewhitelistet");
        }
        LogHelper.info(configReference.whitelist);
    }
}
