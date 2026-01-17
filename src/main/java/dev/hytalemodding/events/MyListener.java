package dev.hytalemodding.events;

import com.hypixel.hytale.server.core.Message;
import com.hypixel.hytale.server.core.entity.entities.Player;
import com.hypixel.hytale.server.core.event.events.player.PlayerReadyEvent;

public class MyListener {

    public static void openGui(PlayerReadyEvent event) {
        Player player = event.getPlayer();

        player.sendMessage(Message.raw("Welcome to the server! :)"));
    }
}
