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
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ChatComponentText;
import net.minecraft.world.WorldSettings;
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
            icommandsender.addChatMessage(new ChatComponentText(argstring[1] + " was succesful added to the whitelist"));
            FMLCommonHandler.instance().bus().register(new ConfigurationHandler());
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
