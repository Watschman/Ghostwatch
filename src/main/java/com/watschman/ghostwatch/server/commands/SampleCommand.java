package com.watschman.ghostwatch.server.commands;

import com.watschman.ghostwatch.handler.ConfigurationHandler;
import com.watschman.ghostwatch.reference.configReference;
import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;
import net.minecraft.util.ChatComponentText;

import java.util.ArrayList;
import java.util.List;

public class SampleCommand implements ICommand{
    private List aliases;
            public SampleCommand(){
                this.aliases = new ArrayList();
                this.aliases.add("sample");
                this.aliases.add("sam");
            }

    @Override
    public String getCommandName() {
        return "sample";
    }

    @Override
    public String getCommandUsage(ICommandSender icommandsender) {
        return "sample <text>";
    }

    @Override
    public List getCommandAliases() {
        return this.aliases;
    }

    @Override
    public void processCommand(ICommandSender icommandsender, String[] argstring)
    {
        if (argstring.length == 0){
            if (configReference.configValue == false){
                icommandsender.addChatMessage(new ChatComponentText("configValue is currently false"));
                return;
            }
            else{
                icommandsender.addChatMessage(new ChatComponentText("configValue is currently true"));
                return;
            }
        }
        icommandsender.addChatMessage(new ChatComponentText(argstring[0]));
    }

    @Override
    public boolean canCommandSenderUseCommand(ICommandSender icommandsender) {
        return true;
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
