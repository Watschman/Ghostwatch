package com.watschman.ghostwatch.entity.Player;

import com.watschman.ghostwatch.utility.LogHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;

import java.util.List;

public class UUID {
    public static EntityPlayer getPlayerOnServerFromUUID(UUID parUUID){
        if (parUUID == null){
            return null;
        }
        List<EntityPlayerMP> allPlayers = MinecraftServer.getServer().getConfigurationManager().playerEntityList;
        for (EntityPlayerMP player : allPlayers){
            if (player.getUniqueID().equals(parUUID)){
                LogHelper.info("-----Das ist der Loghelper" + player);
                return player;
            }
        }
        return null;
    }


}
