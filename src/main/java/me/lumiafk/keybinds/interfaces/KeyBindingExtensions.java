package me.lumiafk.keybinds.interfaces;

import net.fabricmc.fabric.api.event.Event;
import net.minecraft.client.option.KeyBinding;

public interface KeyBindingExtensions {
	static KeyBindingExtensions getExtensions(KeyBinding keyBinding) {
		return (KeyBindingExtensions) keyBinding;
	}

	Event<OnInput> keybind_getOnPress();

	Event<OnInput> keybind_getOnRelease();
}
