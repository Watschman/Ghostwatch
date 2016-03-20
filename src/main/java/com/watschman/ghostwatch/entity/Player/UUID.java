package com.watschman.ghostwatch.entity.Player;

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
                return player;
            }
        }
        return null;
    }


}
