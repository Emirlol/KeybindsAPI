package org.rimeorreason.keymappings.interfaces;

import net.fabricmc.fabric.api.event.Event;
import net.minecraft.client.KeyMapping;
import org.jetbrains.annotations.NotNull;

public interface KeyMappingExtensions {
    @NotNull
    static KeyMappingExtensions getExtensions(KeyMapping keyMapping) {
        return (KeyMappingExtensions) keyMapping;
    }

    @NotNull
    Event<OnInput> keymapping_getOnPress();

    @NotNull
    Event<OnInput> keymapping_getOnRelease();
}
