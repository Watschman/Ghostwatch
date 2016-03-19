package com.watschman.ghostwatch.client.settings;


import com.watschman.ghostwatch.reference.Reference;
import cpw.mods.fml.client.registry.ClientRegistry;
import net.minecraft.client.settings.KeyBinding;
import org.lwjgl.input.Keyboard;

public class KeyBindings {
    public static KeyBinding menu;
    public static void init(){
        menu = new KeyBinding("key.menu", Keyboard.KEY_ESCAPE, "key.categories." + Reference.MOD_NAME);
        ClientRegistry.registerKeyBinding(menu);
    }
}
