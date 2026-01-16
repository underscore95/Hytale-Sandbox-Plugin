package dev.hytalemodding;

import com.hypixel.hytale.server.core.event.events.player.PlayerReadyEvent;
import com.hypixel.hytale.server.core.plugin.JavaPlugin;
import com.hypixel.hytale.server.core.plugin.JavaPluginInit;
import dev.hytalemodding.commands.*;
import dev.hytalemodding.events.OpenGuiListener;

import javax.annotation.Nonnull;

public class ExamplePlugin extends JavaPlugin {

    public ExamplePlugin(@Nonnull JavaPluginInit init) {
        super(init);
    }

    @Override
    protected void setup() {
        this.getEventRegistry().registerGlobal(PlayerReadyEvent.class, OpenGuiListener::openGui);

        this.getCommandRegistry().registerCommand(new ShowHudCommand("showhud", "Example command"));
        this.getCommandRegistry().registerCommand(new HideHudCommand("hidehud", "Example command"));
        this.getCommandRegistry().registerCommand(new UpdateHudCommand("updatehud", "Example command"));
        this.getCommandRegistry().registerCommand(new ShowPageCommand("showpage", "Example command"));
        this.getCommandRegistry().registerCommand(new HidePageCommand("hidepage", "Example command"));
    }
}