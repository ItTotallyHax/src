package net.axiom;

import org.lwjgl.input.Keyboard;

public class AxiomInput
{
    public boolean[] keyStates = new boolean[256];

    public boolean checkKey(int var1)
    {
        boolean var2 = Keyboard.isKeyDown(var1) != this.keyStates[var1] ? (this.keyStates[var1] = !this.keyStates[var1]) : false;
        boolean var3 = AxiomWrapper.mcObj.currentScreen == null;
        return var2 && var3;
    }
}
