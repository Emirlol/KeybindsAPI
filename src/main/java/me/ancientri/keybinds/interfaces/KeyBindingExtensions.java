package me.ancientri.keybinds.interfaces;

import net.fabricmc.fabric.api.event.Event;
import net.minecraft.client.option.KeyBinding;
import org.jetbrains.annotations.NotNull;

public interface KeyBindingExtensions {
	@NotNull
	static KeyBindingExtensions getExtensions(KeyBinding keyBinding) {
		return (KeyBindingExtensions) keyBinding;
	}

	@NotNull
	Event<OnInput> keybind_getOnPress();

	@NotNull
	Event<OnInput> keybind_getOnRelease();
}
