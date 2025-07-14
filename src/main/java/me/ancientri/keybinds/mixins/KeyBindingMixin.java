package me.ancientri.keybinds.mixins;

import com.llamalad7.mixinextras.sugar.Local;
import me.ancientri.keybinds.interfaces.KeyBindingExtensions;
import me.ancientri.keybinds.interfaces.OnInput;
import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.minecraft.client.option.KeyBinding;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(KeyBinding.class)
public abstract class KeyBindingMixin implements KeyBindingExtensions {
	@Unique
	private Event<OnInput> ON_PRESS = null;

	@Unique
	private Event<OnInput> ON_RELEASE = null;

	@Shadow
	private boolean pressed;

	@Inject(method = "setPressed", at = @At("HEAD"))
	private void setPressed(CallbackInfo ci, @Local(argsOnly = true) boolean pressedArg) {
		if (pressedArg && !pressed && ON_PRESS != null) {
			ON_PRESS.invoker().onInput();
		} else if (!pressedArg && pressed && ON_RELEASE != null) {
			ON_RELEASE.invoker().onInput();
		}
	}

	// The events are lazy-initialized via the 2 methods below, so there are no useless events for keybindings that have no event listeners.

	@Override
	@NotNull
	public Event<OnInput> keybind_getOnPress() {
		if (ON_PRESS == null) {
			ON_PRESS = EventFactory.createArrayBacked(OnInput.class, callbacks -> () -> {
				for (OnInput callback : callbacks) {
					callback.onInput();
				}
			});
		}
		return ON_PRESS;
	}

	@Override
	@NotNull
	public Event<OnInput> keybind_getOnRelease() {
		if (ON_RELEASE == null) {
			ON_RELEASE = EventFactory.createArrayBacked(OnInput.class, callbacks -> () -> {
				for (OnInput callback : callbacks) {
					callback.onInput();
				}
			});
		}
		return ON_RELEASE;
	}
}
