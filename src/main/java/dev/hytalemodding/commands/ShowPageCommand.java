package dev.hytalemodding.commands;


import au.ellie.hyui.builders.LabelBuilder;
import au.ellie.hyui.builders.PageBuilder;
import com.hypixel.hytale.component.Ref;
import com.hypixel.hytale.component.Store;
import com.hypixel.hytale.protocol.packets.interface_.CustomUIEventBindingType;
import com.hypixel.hytale.server.core.Message;
import com.hypixel.hytale.server.core.command.system.CommandContext;
import com.hypixel.hytale.server.core.command.system.basecommands.AbstractPlayerCommand;
import com.hypixel.hytale.server.core.entity.entities.Player;
import com.hypixel.hytale.server.core.universe.PlayerRef;
import com.hypixel.hytale.server.core.universe.world.World;
import com.hypixel.hytale.server.core.universe.world.storage.EntityStore;

import javax.annotation.Nonnull;

public class ShowPageCommand extends AbstractPlayerCommand {

    public ShowPageCommand(@Nonnull String name, @Nonnull String description) {
        super(name, description);
    }

    static int clicks = 0;

    @Override
    protected void execute(@Nonnull CommandContext commandContext, @Nonnull Store<EntityStore> store, @Nonnull Ref<EntityStore> ref, @Nonnull PlayerRef playerRef, @Nonnull World world) {
        playerRef.sendMessage(Message.raw("UI Page Shown"));

        String html = """
                <div class="page-overlay">
                    <div class="container" data-hyui-title="My Custom Page using HTML">
                        <div class="container-contents">
                            <p id="label">Clicks: 0</p>
                            <button id="hi-btn">Click Me!</button>
                        </div>
                    </div>
                </div>
                """;

        PageBuilder.pageForPlayer(playerRef)
                .fromHtml(html)
                .addEventListener("hi-btn", CustomUIEventBindingType.Activating, (ignored, ctx) -> {
                    playerRef.sendMessage(Message.raw("Hello from the UI!"));

                    clicks++;
                    ctx.getById("label", LabelBuilder.class).ifPresent(label -> {
                        label.withText("Clicks: " + clicks);
                    });

                    ctx.updatePage(true);
                })
                .open(store);
    }
}