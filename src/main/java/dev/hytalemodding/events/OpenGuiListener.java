package dev.hytalemodding.events;

import com.hypixel.hytale.component.Ref;
import com.hypixel.hytale.component.Store;
import com.hypixel.hytale.protocol.packets.interface_.CustomPageLifetime;
import com.hypixel.hytale.protocol.packets.interface_.HudComponent;
import com.hypixel.hytale.server.core.Message;
import com.hypixel.hytale.server.core.entity.entities.Player;
import com.hypixel.hytale.server.core.entity.entities.player.pages.CustomUIPage;
import com.hypixel.hytale.server.core.event.events.player.PlayerReadyEvent;
import com.hypixel.hytale.server.core.universe.PlayerRef;
import com.hypixel.hytale.server.core.universe.world.World;
import com.hypixel.hytale.server.core.universe.world.storage.EntityStore;
import dev.hytalemodding.ui.MyUI;

import java.util.concurrent.CompletableFuture;

public class OpenGuiListener {

    public static void openGui(PlayerReadyEvent event) {
        Player player = event.getPlayer();
        Ref<EntityStore> ref = event.getPlayerRef();
        Store<EntityStore> store = ref.getStore();
        PlayerRef playerRef = store.getComponent(ref, PlayerRef.getComponentType());
        World world = player.getWorld();

        assert world != null;
        assert playerRef != null;
//
//        CompletableFuture.runAsync(() -> {
//            CustomUIPage page = player.getPageManager().getCustomPage();
//            if (page == null) {
//                page = new MyUI(playerRef, CustomPageLifetime.CanDismiss);
//                player.getPageManager().openCustomPage(ref, store, page);
//            }
//
//            playerRef.sendMessage(Message.raw("UI Page Shown"));
//        }, world);
    }
}
