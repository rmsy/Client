package tc.oc.projectares.client.controls;

import net.minecraft.client.Minecraft;
import net.minecraft.src.*;

public class ControlsAres {
    protected static Class forgeCommonHandlerClass;
    protected boolean forgeDetected = false;

    public ControlsAres() {
        // Look for Forge
        try {
            this.forgeCommonHandlerClass = Class.forName("cpw.mods.fml.common.FMLCommonHandler");

            if(this.forgeCommonHandlerClass != null) {
                this.forgeDetected = true;
            }
        } catch(Exception ex) {
        }
    }

    public void onTickInGUI(float tick, Minecraft mc, GuiScreen screen) {
        if(!this.forgeDetected) { // If Forge detected no need for better controls, if not then open better controls when controls is open.
            if(mc.currentScreen instanceof GuiControls && !(mc.currentScreen instanceof GuiAresControls) && mc.isGamePaused) {
                mc.displayGuiScreen(new GuiAresControls(new GuiOptions(new GuiIngameMenu(), mc.gameSettings), mc.gameSettings));
            }

            if(mc.currentScreen instanceof GuiControls && !(mc.currentScreen instanceof GuiAresControls) && !mc.isGamePaused) {
                mc.displayGuiScreen(new GuiAresControls(new GuiOptions(new GuiMainMenu(), mc.gameSettings), mc.gameSettings));
            }
        }
    }
}
