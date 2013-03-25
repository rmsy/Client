package tc.oc.projectares.client.togglesneak;

import net.minecraft.client.Minecraft;
import net.minecraft.src.*;
import org.lwjgl.input.Keyboard;

public class ToggleSneakAres {

    private KeyBinding toggleSneakKey;
    protected static boolean toggleSneak = false;

    public ToggleSneakAres() {
        // Register Toggle Sneak Key
        this.toggleSneakKey = new KeyBinding("key.togglesneak", Keyboard.KEY_Z);
        ModLoader.registerKey(mod_ProjectAres.get(), this.toggleSneakKey, false);
        ModLoader.addLocalization("key.togglesneak", "Toggle Sneak");
    }

    public void onTickInGame(float tick, Minecraft mc) {
        // Modifies movementinput to allow toggle sneak
        EntityClientPlayerMP player = mc.thePlayer;

        if(!(player.movementInput instanceof MovementInputAres)) {
            player.movementInput = new MovementInputAres(mc.gameSettings);
        }
    }

    public void keyboardEvent(KeyBinding key) {
        // Disables toggle sneak so typing Z does not toggle sneak in game when chatting
        if(ModLoader.getMinecraftInstance().currentScreen instanceof GuiChat) {
            return;
        }

        // Toggle sneak when press the toggle sneak key
        if(key.keyCode == this.toggleSneakKey.keyCode) {
            this.toggleSneak = !this.toggleSneak;
        }
    }
}
