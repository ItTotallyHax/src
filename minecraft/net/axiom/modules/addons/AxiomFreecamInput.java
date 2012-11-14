package net.axiom.modules.addons;

import net.minecraft.src.MovementInput;
import org.lwjgl.input.Keyboard;

public class AxiomFreecamInput extends MovementInput
{
    public void updatePlayerMoveState()
    {
        this.moveStrafe = 0.0F;
        this.moveForward = 0.0F;

        if (Keyboard.isKeyDown(29))
        {
            ++this.moveForward;
        }

        if (Keyboard.isKeyDown(157))
        {
            --this.moveForward;
        }
    }
}
