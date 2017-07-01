package com.chaosthedude.calculations.key;

import org.lwjgl.input.Keyboard;

import com.chaosthedude.calculations.gui.GuiCalculator;

import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.KeyBinding;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.ClientTickEvent;

public class KeybindHandler {

	private static KeyBinding openCalculator = new KeyBinding("key.openCalculator", Keyboard.KEY_V, "key.category.calculations");

	private static final Minecraft mc = Minecraft.getMinecraft();

	public KeybindHandler() {
		ClientRegistry.registerKeyBinding(openCalculator);
	}

	@SubscribeEvent
	public void onClientTick(ClientTickEvent event) {
		if (openCalculator.isPressed()) {
			mc.displayGuiScreen(new GuiCalculator(mc.currentScreen));
		}
	}

}
