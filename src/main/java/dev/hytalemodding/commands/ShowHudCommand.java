package dev.hytalemodding.commands;

import com.buuz135.mhud.MultipleHUD;
import com.hypixel.hytale.component.Ref;
import com.hypixel.hytale.component.Store;
import com.hypixel.hytale.server.core.Message;
import com.hypixel.hytale.server.core.command.system.CommandContext;
import com.hypixel.hytale.server.core.command.system.basecommands.AbstractPlayerCommand;
import com.hypixel.hytale.server.core.entity.entities.Player;
import com.hypixel.hytale.server.core.universe.PlayerRef;
import com.hypixel.hytale.server.core.universe.world.World;
import com.hypixel.hytale.server.core.universe.world.storage.EntityStore;
import dev.hytalemodding.ui.MyHUD;
import dev.hytalemodding.ui.MyHUD2;

import javax.annotation.Nonnull;
import java.util.concurrent.CompletableFuture;

public class ShowHudCommand extends AbstractPlayerCommand {

    public ShowHudCommand(@Nonnull String name, @Nonnull String description) {
        super(name, description);
    }

    @Override
    protected void execute(@Nonnull CommandContext commandContext, @Nonnull Store<EntityStore> store, @Nonnull Ref<EntityStore> ref, @Nonnull PlayerRef playerRef, @Nonnull World world) {
        Player player = commandContext.senderAs(Player.class);

        CompletableFuture.runAsync(() -> {
            MultipleHUD.getInstance().setCustomHud(player, playerRef, MyHUD.ID, new MyHUD(playerRef));
            MultipleHUD.getInstance().setCustomHud(player, playerRef, MyHUD2.ID, new MyHUD2(playerRef));
            playerRef.sendMessage(Message.raw("UI HUD Shown"));
        }, world);
    }
}
