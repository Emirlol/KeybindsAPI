package org.rimeorreason.keymappings.mixins;

import com.llamalad7.mixinextras.sugar.Local;
import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.minecraft.client.KeyMapping;
import org.jetbrains.annotations.NotNull;
import org.rimeorreason.keymappings.interfaces.KeyMappingExtensions;
import org.rimeorreason.keymappings.interfaces.OnInput;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(KeyMapping.class)
public abstract class KeyMappingMixin implements KeyMappingExtensions {
    @Unique
    private Event<OnInput> ON_PRESS = null;

    @Unique
    private Event<OnInput> ON_RELEASE = null;

    @Shadow
    private boolean isDown;

    @Inject(method = "setDown", at = @At("HEAD"))
    private void setDown(CallbackInfo ci, @Local(argsOnly = true, name = "down") boolean down) {
        if (down && !isDown && ON_PRESS != null) {
            ON_PRESS.invoker().onInput();
        } else if (!down && isDown && ON_RELEASE != null) {
            ON_RELEASE.invoker().onInput();
        }
    }

    // The events are lazy-initialized via the 2 methods below, so there are no useless events for keymapings that have no event listeners.

    @Override
    @NotNull
    public Event<OnInput> keymapping_getOnPress() {
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
    public Event<OnInput> keymapping_getOnRelease() {
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
