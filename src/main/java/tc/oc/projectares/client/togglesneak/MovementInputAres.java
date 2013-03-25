package tc.oc.projectares.client.togglesneak;

import net.minecraft.src.GameSettings;
import net.minecraft.src.MovementInputFromOptions;

public class MovementInputAres extends MovementInputFromOptions {
    private GameSettings gameSettings;
    private boolean sneakWasPressed;

    public MovementInputAres(GameSettings par1GameSettings) {
        super(par1GameSettings);
        this.gameSettings = par1GameSettings;
        this.sneakWasPressed = false;
    }

    public void updatePlayerMoveState() {
        this.moveStrafe = 0.0F;
        this.moveForward = 0.0F;

        if(this.gameSettings.keyBindForward.pressed) {
            ++this.moveForward;
        }

        if(this.gameSettings.keyBindBack.pressed) {
            --this.moveForward;
        }

        if(this.gameSettings.keyBindLeft.pressed) {
            ++this.moveStrafe;
        }

        if(this.gameSettings.keyBindRight.pressed) {
            --this.moveStrafe;
        }

        this.jump = this.gameSettings.keyBindJump.pressed;

        if(ToggleSneakAres.toggleSneak) {
            if((this.gameSettings.keyBindSneak.isPressed()) && (!this.sneakWasPressed)) {
                this.sneak = (!this.sneak);
            }
        } else {
            this.sneak = this.gameSettings.keyBindSneak.pressed;
        }

        this.sneakWasPressed = this.gameSettings.keyBindSneak.isPressed();

        if(this.sneak) {
            this.moveStrafe = (float)((double) this.moveStrafe * 0.3D);
            this.moveForward = (float)((double) this.moveForward * 0.3D);
        }
    }
}
