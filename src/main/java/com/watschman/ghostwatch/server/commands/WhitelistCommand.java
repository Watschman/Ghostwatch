package com.watschman.ghostwatch.server.commands;

import com.watschman.ghostwatch.entity.Player.UUID;
import com.watschman.ghostwatch.handler.ConfigurationHandler;
import com.watschman.ghostwatch.reference.configReference;
import cpw.mods.fml.common.FMLCommonHandler;
import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.PlayerNotFoundException;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ChatComponentText;
import net.minecraft.world.WorldSettings;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.config.Property;

import java.util.ArrayList;
import java.util.List;

public class WhitelistCommand implements ICommand{
    private List aliases;
            public WhitelistCommand(){
                this.aliases = new ArrayList();
                this.aliases.add("ghostwatch");
                this.aliases.add("gh");
            }

    @Override
    public String getCommandName() {
        return "ghostwatch";
    }

    @Override
    public String getCommandUsage(ICommandSender icommandsender) {
        return "ghostwatch.usage";
    }

    @Override
    public List getCommandAliases() {
        return this.aliases;
    }

    @Override
    public void processCommand(ICommandSender icommandsender, String[] argstring)
    {
        MinecraftServer minecraftServer = MinecraftServer.getServer();
        EntityPlayerMP entityPlayerMP = MinecraftServer.getServer().getConfigurationManager().func_152612_a(argstring[1]);
        if (argstring.length == 0){
                icommandsender.addChatMessage(new ChatComponentText("Usage: /ghostwatch/command"));
                return;
        }
        if (argstring[0].equals("add")) {
            configReference.whitelist.add(argstring[1]);
            ConfigurationHandler.loadConfiguration();
            icommandsender.addChatMessage(new ChatComponentText(argstring[1] + " was succesfully added to the whitelist"));
            if (configReference.configValue == true) {
                if (minecraftServer.getConfigurationManager().playerEntityList.contains(entityPlayerMP)) {
                    if (configReference.whitelist.contains(entityPlayerMP.getDisplayName())) {
                        int i = 0;
                        WorldSettings.getGameTypeById(i);
                        switch (i) {
                            case 0:
                                entityPlayerMP.setGameType(WorldSettings.GameType.SURVIVAL);
                                entityPlayerMP.capabilities.allowEdit = true;
                                entityPlayerMP.capabilities.isCreativeMode = false;
                                entityPlayerMP.capabilities.allowFlying = false;
                                entityPlayerMP.capabilities.disableDamage = false;
                                break;
                            case 1:
                                entityPlayerMP.setGameType(WorldSettings.GameType.CREATIVE);
                                entityPlayerMP.capabilities.allowEdit = true;
                                entityPlayerMP.capabilities.isCreativeMode = true;
                                entityPlayerMP.capabilities.allowFlying = true;
                                entityPlayerMP.capabilities.disableDamage = true;
                                break;
                            case 2:
                                entityPlayerMP.setGameType(WorldSettings.GameType.ADVENTURE);
                                entityPlayerMP.capabilities.allowEdit = false;
                                entityPlayerMP.capabilities.isCreativeMode = false;
                                entityPlayerMP.capabilities.allowFlying = false;
                                entityPlayerMP.capabilities.disableDamage = false;
                                break;
                        }
                        entityPlayerMP.removePotionEffect(Potion.invisibility.id);
                    }
                }
            }
        }
        else{
            //NOOP
        }
        if (argstring[0].equals("remove")) {
            if (configReference.whitelist.contains(argstring[1])) {
                configReference.whitelist.remove(argstring[1]);
                icommandsender.addChatMessage(new ChatComponentText(argstring[1] + " was succesfully removed to the whitelist"));
                ConfigurationHandler.loadConfiguration();
            } else {
                icommandsender.addChatMessage(new ChatComponentText(argstring[1] + " could not be deleted"));
            }
            if (configReference.configValue == true) {
                if (minecraftServer.getConfigurationManager().playerEntityList.contains(entityPlayerMP)) {
                    if (!(configReference.whitelist.contains(entityPlayerMP.getDisplayName()))) {
                        entityPlayerMP.addPotionEffect(new PotionEffect(Potion.invisibility.id, 1728000, 1));
                        int i = 0;
                        WorldSettings.getGameTypeById(i);
                        if (i != 3) {
                            entityPlayerMP.setGameType(WorldSettings.GameType.ADVENTURE);
                        }
                        if (entityPlayerMP.capabilities.allowEdit != false || entityPlayerMP.capabilities.isCreativeMode != false || entityPlayerMP.capabilities.allowFlying != true || entityPlayerMP.capabilities.disableDamage != true) {
                            entityPlayerMP.capabilities.allowEdit = false;
                            entityPlayerMP.capabilities.isCreativeMode = false;
                            entityPlayerMP.capabilities.allowFlying = true;
                            entityPlayerMP.capabilities.disableDamage = true;
                        }
                    }
                }
            }
            else{
                //NOOP
            }
        }
    }

    @Override
    public boolean canCommandSenderUseCommand(ICommandSender icommandsender) {
        return icommandsender.canCommandSenderUseCommand(2, "ghostwatch");
    }

    @Override
    public List addTabCompletionOptions(ICommandSender icommandsender, String[] argstring) {
        return null;
    }

    @Override
    public boolean isUsernameIndex(String[] argstring, int i) {
        return false;
    }

    @Override
    public int compareTo(Object o) {
        return 0;
    }
}
