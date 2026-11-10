package com.example.immortal;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.entity.event.v1.ServerLivingEntityEvents;
import net.minecraft.server.level.ServerPlayer;

public class ImmortalMod implements ModInitializer {
    // 1.0 health = half a heart
    private static final float MIN_HEALTH = 1.0f;

    @Override
    public void onInitialize() {
        ServerLivingEntityEvents.ALLOW_DAMAGE.register((entity, source, amount) -> {
            if (entity instanceof ServerPlayer player) {
                // Would this hit take us below half a heart?
                if (player.getHealth() - amount < MIN_HEALTH) {
                    // Drop to exactly half a heart and cancel the lethal damage
                    player.setHealth(MIN_HEALTH);
                    return false;
                }
            }
            return true;
        });
    }
}
