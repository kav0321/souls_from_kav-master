package net.kav.soul.event;

import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.kav.soul.client.gui.ExampleGui;
import net.kav.soul.client.gui.Screen;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import org.lwjgl.glfw.GLFW;

public class KeyInputHandler {

    public static final String KEY_CATEGORY_SOUL ="key.category.soul.soul";
    public static final String KEY_ESCAPE ="key.category.soul.escaping";
    public static final String KEY_GAINING_SOUL = "key.soul.gaining_soul";

    public static KeyBinding gainingkey;
   public static KeyBinding gainingkey2;

    public static void registerKeyInputs(){
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
if(gainingkey.wasPressed()) {

    MinecraftClient.getInstance().setScreen(new Screen((new ExampleGui())));
            }


        });
    }



    public static void register()
    {
        gainingkey = KeyBindingHelper.registerKeyBinding(new KeyBinding(
           KEY_GAINING_SOUL,
            InputUtil.Type.KEYSYM,
            GLFW.GLFW_KEY_V,
            KEY_CATEGORY_SOUL
    ));



        registerKeyInputs();

    }






}
