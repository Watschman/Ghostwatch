package com.watschman.ghostwatch.event;

import com.watschman.ghostwatch.reference.configReference;
import com.watschman.ghostwatch.utility.LogHelper;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.WorldSettings;
import net.minecraftforge.common.MinecraftForge;


public class playerevent {

    @SubscribeEvent
    public void onPlayerLogin(PlayerEvent.PlayerLoggedInEvent event) {
        if (configReference.configValue = true) {
            if (!(configReference.whitelist.contains(event.player.getDisplayName()))) {

                LogHelper.info(event.player.getDisplayName() + " ist nicht gewhitelistet");
                event.player.addPotionEffect(new PotionEffect(Potion.invisibility.id, 1728000, 1));
//            Ghostwatch.proxy.playerRender();
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

                MinecraftForge.EVENT_BUS.register(new attackentityevent());
                MinecraftForge.EVENT_BUS.register(new blockbreakevent());
                MinecraftForge.EVENT_BUS.register(new blockplaceevent());
                MinecraftForge.EVENT_BUS.register(new containerinteractevent());
            } else {
                event.player.setGameType(WorldSettings.GameType.SURVIVAL);
                event.player.capabilities.allowEdit = true;
                event.player.capabilities.isCreativeMode = false;
                event.player.capabilities.allowFlying = false;
                event.player.capabilities.disableDamage = false;
                LogHelper.info(event.player.getDisplayName() + " ist gewhitelistet");

            }
            event.player.sendPlayerAbilities();
        }
    }
    @SubscribeEvent
    public void onPlayerLogout(PlayerEvent.PlayerLoggedOutEvent event) {
        if (configReference.configValue = true) {
            if (!(configReference.whitelist.contains(event.player.getDisplayName()))) {
                LogHelper.info(event.player.getDisplayName() + " ist nicht gewhitelistet und loggte aus");
                event.player.removePotionEffect(Potion.invisibility.id);
            }
        }
    }
}
