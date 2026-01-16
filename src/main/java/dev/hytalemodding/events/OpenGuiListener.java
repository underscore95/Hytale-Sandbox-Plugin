package dev.hytalemodding.events;

import com.hypixel.hytale.server.core.event.events.player.PlayerReadyEvent;

public class OpenGuiListener {

    public static void openGui(PlayerReadyEvent event) {
//        Player player = event.getPlayer();
//        Ref<EntityStore> ref = event.getPlayerRef();
//        Store<EntityStore> store = ref.getStore();
//        PlayerRef playerRef = store.getComponent(ref, PlayerRef.getComponentType());
//        World world = player.getWorld();
//
//        assert world != null;
//        assert playerRef != null;
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
