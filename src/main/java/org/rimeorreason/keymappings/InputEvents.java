package org.rimeorreason.keymappings;

import org.rimeorreason.keymappings.interfaces.KeyMappingExtensions;
import org.rimeorreason.keymappings.interfaces.OnInput;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.event.Event;
import net.minecraft.client.KeyMapping;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

@Environment(EnvType.CLIENT)
public final class InputEvents {
    private InputEvents() {
    }

    // These 2 methods are essentially helper methods to get the events contained within the keymapping objects, which are mixin'd in.

    /**
     * @param keyMapping The keymapping to listen for.
     * @return The event for that keymapping, which can be used to register callbacks.
     * @apiNote This event is only fired when there isn't a screen open.
     * For screen events, use fabric's {@link net.fabricmc.fabric.api.client.screen.v1.ScreenKeyboardEvents ScreenKeyboardEvents}
     * and {@link net.fabricmc.fabric.api.client.screen.v1.ScreenMouseEvents ScreenMouseEvents}.
     */
    @NotNull
    public static Event<OnInput> onPress(@NotNull KeyMapping keyMapping) {
        Objects.requireNonNull(keyMapping, "KeyMapping cannot be null");

        return KeyMappingExtensions.getExtensions(keyMapping).keymapping_getOnPress();
    }

    /**
     * @param keyMapping The keymapping to listen for.
     * @return The event for that keymapping, which can be used to register callbacks.
     * @apiNote This event is only fired when there isn't a screen open.
     * For screen events, use fabric's {@link net.fabricmc.fabric.api.client.screen.v1.ScreenKeyboardEvents ScreenKeyboardEvents}
     * and {@link net.fabricmc.fabric.api.client.screen.v1.ScreenMouseEvents ScreenMouseEvents}.
     */
    @NotNull
    public static Event<OnInput> onRelease(@NotNull KeyMapping keyMapping) {
        Objects.requireNonNull(keyMapping, "KeyMapping cannot be null");

        return KeyMappingExtensions.getExtensions(keyMapping).keymapping_getOnRelease();
    }
}
