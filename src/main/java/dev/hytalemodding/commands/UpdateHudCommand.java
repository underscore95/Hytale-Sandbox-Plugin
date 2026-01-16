package dev.hytalemodding.commands;

import com.hypixel.hytale.component.Ref;
import com.hypixel.hytale.component.Store;
import com.hypixel.hytale.server.core.command.system.CommandContext;
import com.hypixel.hytale.server.core.command.system.basecommands.AbstractPlayerCommand;
import com.hypixel.hytale.server.core.entity.entities.Player;
import com.hypixel.hytale.server.core.universe.PlayerRef;
import com.hypixel.hytale.server.core.universe.world.World;
import com.hypixel.hytale.server.core.universe.world.storage.EntityStore;
import dev.hytalemodding.ui.MyHUD;

import javax.annotation.Nonnull;
import java.util.concurrent.CompletableFuture;

public class UpdateHudCommand extends AbstractPlayerCommand {

    private static int timesUpdated = 0;

    public UpdateHudCommand(@Nonnull String name, @Nonnull String description) {
        super(name, description);
    }

    @Override
    protected void execute(@Nonnull CommandContext commandContext, @Nonnull Store<EntityStore> store, @Nonnull Ref<EntityStore> ref, @Nonnull PlayerRef playerRef, @Nonnull World world) {
        Player player = commandContext.senderAs(Player.class);

        CompletableFuture.runAsync(() -> {
            if (player.getHudManager().getCustomHud() instanceof MyHUD hud) {
                hud.setLabel("times updated: " + timesUpdated);
                timesUpdated++;
            }
        }, world);
    }
}
