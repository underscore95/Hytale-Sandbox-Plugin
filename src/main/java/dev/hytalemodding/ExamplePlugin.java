package dev.hytalemodding;

import au.ellie.hyui.builders.*;
import au.ellie.hyui.elements.LayoutModeSupported;
import com.hypixel.hytale.component.Ref;
import com.hypixel.hytale.server.core.Message;
import com.hypixel.hytale.server.core.event.events.player.PlayerReadyEvent;
import com.hypixel.hytale.server.core.plugin.JavaPlugin;
import com.hypixel.hytale.server.core.plugin.JavaPluginInit;
import com.hypixel.hytale.server.core.universe.PlayerRef;
import com.hypixel.hytale.server.core.universe.world.storage.EntityStore;
import dev.hytalemodding.commands.ShowPageCommand;

import javax.annotation.Nonnull;

public class ExamplePlugin extends JavaPlugin {

    public ExamplePlugin(@Nonnull JavaPluginInit init) {
        super(init);
    }

    public static int seconds = 0;

    @Override
    protected void setup() {
        this.getCommandRegistry().registerCommand(new ShowPageCommand("showpage", "Example command"));

        this.getEventRegistry().registerGlobal(PlayerReadyEvent.class, event -> {
            Ref<EntityStore> ref = event.getPlayerRef();
            PlayerRef playerRef = ref.getStore().getComponent(ref, PlayerRef.getComponentType());

            HudBuilder.hudForPlayer(playerRef)
                    .addElement(
                            GroupBuilder.group()
                                    .withBackground(new HyUIPatchStyle().setColor("#FF0000(0.5)"))
                                    .withLayoutMode(LayoutModeSupported.LayoutMode.MiddleCenter)
                                    .withAnchor(new HyUIAnchor().setWidth(200).setHeight(200))
                                    .addChild(
                                            LabelBuilder.label()
                                                    .withText("")
                                                    .withId("label")
                                    )
                    )
                    .withRefreshRate(1000)
                    .onRefresh(hud -> {
                        hud.getById("label", LabelBuilder.class).ifPresent(label -> {
                            seconds++;
                            label.withText("Seconds: " + seconds);
                        });
                    })
                    .show(ref.getStore());

        });
    }
}