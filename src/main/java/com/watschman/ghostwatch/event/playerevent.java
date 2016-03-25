package com.watschman.ghostwatch.event;

import com.watschman.ghostwatch.reference.configReference;
import com.watschman.ghostwatch.utility.LogHelper;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.WorldSettings;


public class playerevent {

    @SubscribeEvent
    public void onPlayerLogin(PlayerEvent.PlayerLoggedInEvent event) {
        if (configReference.configValue == true) {
            if (!(configReference.whitelist.contains(event.player.getDisplayName()))) {

                LogHelper.info(event.player.getDisplayName() + " is not whitelistet and logged in");
                event.player.addPotionEffect(new PotionEffect(Potion.invisibility.id, 1728000, 1));
                int i = 0;
                WorldSettings.getGameTypeById(i);
                if (i != 3) {
                    event.player.setGameType(WorldSettings.GameType.ADVENTURE);
                }
                if (event.player.capabilities.allowEdit != false || event.player.capabilities.isCreativeMode != false || event.player.capabilities.allowFlying != true || event.player.capabilities.disableDamage != true) {
                    event.player.capabilities.allowEdit = false;
                    event.player.capabilities.isCreativeMode = false;
                    event.player.capabilities.allowFlying = true;
                    event.player.capabilities.disableDamage = true;
                }
            }
            else {
                event.player.setGameType(WorldSettings.GameType.SURVIVAL);
                event.player.capabilities.allowEdit = true;
                event.player.capabilities.isCreativeMode = false;
                event.player.capabilities.allowFlying = false;
                event.player.capabilities.disableDamage = false;
                event.player.removePotionEffect(Potion.invisibility.id);
                LogHelper.info(event.player.getDisplayName() + " is whitelistet and logged in");

            }
        }
        else {
            int k = 0;
            WorldSettings.getGameTypeById(k);
            switch (k){
                case 0:
                    event.player.setGameType(WorldSettings.GameType.SURVIVAL);
                    event.player.capabilities.allowEdit = true;
                    event.player.capabilities.isCreativeMode = false;
                    event.player.capabilities.allowFlying = false;
                    event.player.capabilities.disableDamage = false;
                    break;
                case 1:
                    event.player.setGameType(WorldSettings.GameType.CREATIVE);
                    event.player.capabilities.allowEdit = true;
                    event.player.capabilities.isCreativeMode = true;
                    event.player.capabilities.allowFlying = true;
                    event.player.capabilities.disableDamage = true;
                    break;
                case 2:
                    event.player.setGameType(WorldSettings.GameType.ADVENTURE);
                    event.player.capabilities.allowEdit = false;
                    event.player.capabilities.isCreativeMode = false;
                    event.player.capabilities.allowFlying = false;
                    event.player.capabilities.disableDamage = false;
                    break;
            }
        }
        event.player.sendPlayerAbilities();
    }
    @SubscribeEvent
    public void onPlayerLogout(PlayerEvent.PlayerLoggedOutEvent event) {
        if (configReference.configValue == true) {
            if (!(configReference.whitelist.contains(event.player.getDisplayName()))) {
                LogHelper.info(event.player.getDisplayName() + " ist not whitelistet and logged aus");
            }
        }
        else {
        }
        event.player.removePotionEffect(Potion.invisibility.id);
    }
}
