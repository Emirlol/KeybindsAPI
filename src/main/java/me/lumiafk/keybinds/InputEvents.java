package me.lumiafk.keybinds;

import me.lumiafk.keybinds.interfaces.KeyBindingExtensions;
import me.lumiafk.keybinds.interfaces.OnInput;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.event.Event;
import net.minecraft.client.option.KeyBinding;

import java.util.Objects;

@Environment(EnvType.CLIENT)
public final class InputEvents {
	private InputEvents() {}

	//These 2 methods are essentially helper methods to get the events contained within the keybinding objects, which are mixin'd in.

	/**
	 * @param   keyBinding The keybinding to listen for.
	 * @return  The event for that keybinding, which can be used to register callbacks.
	 * @apiNote This event is only fired when there isn't a screen open.
	 *          For screen events, use fabric's {@link net.fabricmc.fabric.api.client.screen.v1.ScreenKeyboardEvents ScreenKeyboardEvents}
	 *          and {@link net.fabricmc.fabric.api.client.screen.v1.ScreenMouseEvents ScreenMouseEvents}.
	 */
	public static Event<OnInput> onPress(KeyBinding keyBinding) {
		Objects.requireNonNull(keyBinding, "KeyBinding cannot be null");

		return KeyBindingExtensions.getExtensions(keyBinding).keybind_getOnPress();
	}

	/**
	 * @param   keyBinding The keybinding to listen for.
	 * @return  The event for that keybinding, which can be used to register callbacks.
	 * @apiNote This event is only fired when there isn't a screen open.
	 *          For screen events, use fabric's {@link net.fabricmc.fabric.api.client.screen.v1.ScreenKeyboardEvents ScreenKeyboardEvents}
	 *          and {@link net.fabricmc.fabric.api.client.screen.v1.ScreenMouseEvents ScreenMouseEvents}.
	 */
	public static Event<OnInput> onRelease(KeyBinding keyBinding) {
		Objects.requireNonNull(keyBinding, "KeyBinding cannot be null");

		return KeyBindingExtensions.getExtensions(keyBinding).keybind_getOnRelease();
	}
}
