package com.watschman.ghostwatch.proxy;


import com.watschman.ghostwatch.client.settings.KeyBindings;
import cpw.mods.fml.client.registry.ClientRegistry;

public class ClientProxy extends CommonProxy{
    @Override
    public void registerKeyBindings(){
        ClientRegistry.registerKeyBinding(KeyBindings.menu);
    }
}
