package tc.oc.projectares.client;

import net.minecraft.client.Minecraft;
import net.minecraft.src.*;
import tc.oc.projectares.client.controls.ControlsAres;
import tc.oc.projectares.client.togglesneak.ToggleSneakAres;

public final class ProjectAres {
    private static ProjectAres pa;

    public boolean onPA = false;
    public static final String SERVER_DOMAIN = "oc.tc";
    private ToggleSneakAres toggleSneakAres;
    private ControlsAres controlsAres;

    public static ProjectAres get() {
        return pa;
    }

    public ProjectAres() {
        this.pa = this;
        ModLoader.setInGUIHook(mod_ProjectAres.get(), true, false);
        ModLoader.setInGameHook(mod_ProjectAres.get(), true, false);
        this.toggleSneakAres = new ToggleSneakAres();
        this.controlsAres = new ControlsAres();
    }

    public void onCustomPacket(NetServerHandler serverHandler, String channel, int length, byte[] data) {}

    public void onTickInGUI(float tick, Minecraft mc, GuiScreen screen) {
        // Register Controls onTickInGUI
        this.controlsAres.onTickInGUI(tick, mc, screen);
    }

    public void onTickInGame(float tick, Minecraft mc) {
        // Register Toggle Sneak onTickInGame
        this.toggleSneakAres.onTickInGame(tick, mc);
    }

    public void keyboardEvent(KeyBinding key) {
        // Register Toggle Sneak keyboardEvent
        this.toggleSneakAres.keyboardEvent(key);
    }

    public void onConnect(NetClientHandler handler) {
        if(handler.getNetManager().getSocketAddress().toString().contains(this.SERVER_DOMAIN)) {
            this.onPA = true;
        }
    }

    public void onDisconnect(NetClientHandler handler) {
        if(this.onPA) {
            this.onPA = false;
        }
    }
}
