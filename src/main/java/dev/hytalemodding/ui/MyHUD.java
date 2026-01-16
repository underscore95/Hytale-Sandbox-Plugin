package dev.hytalemodding.ui;

import com.hypixel.hytale.server.core.Message;
import com.hypixel.hytale.server.core.entity.entities.player.hud.CustomUIHud;
import com.hypixel.hytale.server.core.ui.builder.UICommandBuilder;
import com.hypixel.hytale.server.core.universe.PlayerRef;

import javax.annotation.Nonnull;

public class MyHUD extends CustomUIHud {

    public static final String ID = "ExamplePlugin:hud1";

    public MyHUD(@Nonnull PlayerRef playerRef) {
        super(playerRef);
    }

    @Override
    protected void build(@Nonnull UICommandBuilder uiCommandBuilder) {
        uiCommandBuilder.append("MyHUD.ui");
    }

    public void setLabel(String label) {
        UICommandBuilder uiCommandBuilder = new UICommandBuilder();
        uiCommandBuilder.set("#MyLabel.TextSpans", Message.raw(label));
        update(false, uiCommandBuilder);
    }
}
