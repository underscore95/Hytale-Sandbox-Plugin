package dev.hytalemodding;

import com.hypixel.hytale.server.core.Message;
import com.hypixel.hytale.server.core.event.events.player.PlayerReadyEvent;
import com.hypixel.hytale.server.core.plugin.JavaPlugin;
import com.hypixel.hytale.server.core.plugin.JavaPluginInit;
import dev.hytalemodding.events.MyListener;
import dev.hytalemodding.events.PlayerMineSystem;

import javax.annotation.Nonnull;

public class ExamplePlugin extends JavaPlugin {

    public ExamplePlugin(@Nonnull JavaPluginInit init) {
        super(init);
    }

    @Override
    protected void setup() {

        // Normal events
        getEventRegistry().registerGlobal(PlayerReadyEvent.class, MyListener::openGui);
        getEventRegistry().registerGlobal(PlayerReadyEvent.class, event -> {
            event.getPlayer().sendMessage(Message.raw("Hi there!"));
        });

        // ECS Events
        getEntityStoreRegistry().registerSystem(new PlayerMineSystem());
    }
}