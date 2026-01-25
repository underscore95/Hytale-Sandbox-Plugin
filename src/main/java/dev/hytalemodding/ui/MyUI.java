package dev.hytalemodding.ui;

import com.hypixel.hytale.codec.Codec;
import com.hypixel.hytale.codec.KeyedCodec;
import com.hypixel.hytale.codec.builder.BuilderCodec;
import com.hypixel.hytale.component.Ref;
import com.hypixel.hytale.component.Store;
import com.hypixel.hytale.protocol.packets.interface_.CustomPageLifetime;
import com.hypixel.hytale.protocol.packets.interface_.CustomUIEventBindingType;
import com.hypixel.hytale.server.core.Message;
import com.hypixel.hytale.server.core.entity.entities.Player;
import com.hypixel.hytale.server.core.entity.entities.player.pages.InteractiveCustomUIPage;
import com.hypixel.hytale.server.core.ui.builder.EventData;
import com.hypixel.hytale.server.core.ui.builder.UICommandBuilder;
import com.hypixel.hytale.server.core.ui.builder.UIEventBuilder;
import com.hypixel.hytale.server.core.universe.PlayerRef;
import com.hypixel.hytale.server.core.universe.world.storage.EntityStore;

import javax.annotation.Nonnull;
import java.util.Objects;

public class MyUI extends InteractiveCustomUIPage<MyUI.Data> {

    private static final String BUTTON1_ID = "Button1";
    private static final String BUTTON2_ID = "Button2";

    public MyUI(@Nonnull PlayerRef playerRef, @Nonnull CustomPageLifetime lifetime) {
        super(playerRef, lifetime, Data.CODEC);
    }

    @Override
    public void build(@Nonnull Ref<EntityStore> ref, @Nonnull UICommandBuilder uiCommandBuilder, @Nonnull UIEventBuilder uiEventBuilder, @Nonnull Store<EntityStore> store) {
        uiCommandBuilder.append("MyUI.ui");

        uiEventBuilder.addEventBinding(CustomUIEventBindingType.Activating, "#Button1", EventData.of("ClickedButton", BUTTON1_ID), false);
        uiEventBuilder.addEventBinding(CustomUIEventBindingType.Activating, "#Button2", EventData.of("ClickedButton", BUTTON2_ID), false);

    }

    @Override
    public void handleDataEvent(@Nonnull Ref<EntityStore> ref, @Nonnull Store<EntityStore> store, @Nonnull Data data) {
        Player player = Objects.requireNonNull(store.getComponent(ref, Player.getComponentType()));
        if (BUTTON1_ID.equals(data.clickedButton)) {
            player.sendMessage(Message.raw("Clicked button 1"));
        } else if (BUTTON2_ID.equals(data.clickedButton)) {
            player.sendMessage(Message.raw("Clicked button 2"));
        }
    }

    public static class Data {
        public static final BuilderCodec<Data> CODEC = BuilderCodec.builder(Data.class, Data::new)
                .append(new KeyedCodec<>("ClickedButton", Codec.STRING), (data, s) -> data.clickedButton = s, choicePageEventData -> choicePageEventData.clickedButton).add()
                .build();

        private String clickedButton;
    }
}
