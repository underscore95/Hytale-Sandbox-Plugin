package dev.hytalemodding.events;

import com.hypixel.hytale.component.ArchetypeChunk;
import com.hypixel.hytale.component.CommandBuffer;
import com.hypixel.hytale.component.Ref;
import com.hypixel.hytale.component.Store;
import com.hypixel.hytale.component.query.Query;
import com.hypixel.hytale.component.system.EntityEventSystem;
import com.hypixel.hytale.server.core.Message;
import com.hypixel.hytale.server.core.entity.entities.Player;
import com.hypixel.hytale.server.core.event.events.ecs.BreakBlockEvent;
import com.hypixel.hytale.server.core.universe.world.storage.EntityStore;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

// Another example: www.youtube.com/watch?v=s-lvZcBygEk
public class PlayerMineSystem extends EntityEventSystem<EntityStore, BreakBlockEvent> {

    public PlayerMineSystem() {
        super(BreakBlockEvent.class);
    }

    @Override
    public void handle(int i, @Nonnull ArchetypeChunk<EntityStore> archetypeChunk, @Nonnull Store<EntityStore> store, @Nonnull CommandBuffer<EntityStore> commandBuffer, @Nonnull BreakBlockEvent breakBlockEvent) {
        // Get the player
        Ref<EntityStore> ref = archetypeChunk.getReferenceTo(i);
        Player player = store.getComponent(ref, Player.getComponentType());

        // For example
        player.sendMessage(Message.raw("You cannot break blocks!"));
        breakBlockEvent.setCancelled(true);
    }

    @Nullable
    @Override
    public Query<EntityStore> getQuery() {
        // Only run for entities with the Player component (only players!)
        return Query.and(
                Player.getComponentType()
        );
    }
}
