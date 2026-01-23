package dev.hytalemodding;

import com.hypixel.hytale.component.*;
import com.hypixel.hytale.component.query.Query;
import com.hypixel.hytale.server.core.Message;
import com.hypixel.hytale.server.core.entity.entities.Player;
import com.hypixel.hytale.server.core.modules.entity.component.TransformComponent;
import com.hypixel.hytale.server.core.modules.entity.damage.Damage;
import com.hypixel.hytale.server.core.modules.entity.damage.DamageEventSystem;
import com.hypixel.hytale.server.core.modules.entity.teleport.Teleport;
import com.hypixel.hytale.server.core.universe.world.storage.EntityStore;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class DamageSystem extends DamageEventSystem {

    @Override
    public void handle(int i, @Nonnull ArchetypeChunk<EntityStore> archetypeChunk, @Nonnull Store<EntityStore> store, @Nonnull CommandBuffer<EntityStore> commandBuffer, @Nonnull Damage damage) {
        // Get entity id
        Ref<EntityStore> ref = archetypeChunk.getReferenceTo(i);

        // Get components
        Player playerComponent = store.getComponent(ref, Player.getComponentType());
        TransformComponent transformComponent = store.getComponent(ref, TransformComponent.getComponentType());

        assert playerComponent != null;
        assert transformComponent != null;

        // Add teleport component
        Teleport teleport = new Teleport(playerComponent.getWorld(), transformComponent.getPosition().add(10, 0, 0), transformComponent.getRotation());
        commandBuffer.addComponent(ref, Teleport.getComponentType(), teleport);

        // Send message
        playerComponent.sendMessage(Message.raw("Poof!").color(Color.PINK));
    }

    @Nullable
    @Override
    public Query<EntityStore> getQuery() {
        return Query.and(Player.getComponentType(), TransformComponent.getComponentType());
    }

    public void test() {
        List<String> list = new ArrayList<>();
        list.add("apple");
        list.add("banana");
        list.add("fruit");

        List<String> startsWithB = new ArrayList<>();

        for (String s : list) {
            if (s.startsWith("b")) {
                startsWithB.add(s);
            }
        }

        for (String s : startsWithB) {
            list.remove(s);
        }
    }
}
