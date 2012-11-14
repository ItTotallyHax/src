package net.axiom.gui.screens;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import net.axiom.gui.components.AvidGuiButton;
import net.axiom.gui.components.AvidGuiWindow;
import net.axiom.gui.windows.AxiomWindowAura;
import net.axiom.gui.windows.AxiomWindowRetardMode;
import net.axiom.gui.windows.AxiomWindowTracer;
import net.axiom.gui.windows.AxiomWindowWallhack;
import net.minecraft.src.GuiScreen;

public class AxiomGuiHub extends GuiScreen
{
    public static HashMap windowLocations = new HashMap();
    public static ArrayList windows;
    protected AxiomWindowAura windowAura = new AxiomWindowAura(2, 2);
    protected AxiomWindowRetardMode windowRetard = new AxiomWindowRetardMode(104, 2);
    protected AxiomWindowTracer windowTracer = new AxiomWindowTracer(218, 2);
    protected AxiomWindowWallhack windowWallhack = new AxiomWindowWallhack(2, 12);

    public AxiomGuiHub()
    {
        windows = new ArrayList();
        windows.add(this.windowAura);
        windows.add(this.windowRetard);
        windows.add(this.windowTracer);
        windows.add(this.windowWallhack);
    }

    /**
     * Draws the screen and all the components in it.
     */
    public void drawScreen(int var1, int var2, float var3)
    {
        this.drawDefaultBackground();

        for (int var4 = windows.size(); var4 > 0; --var4)
        {
            ((AvidGuiWindow)windows.get(var4 - 1)).drawScreen(var1, var2, var3);
        }

        super.drawScreen(var1, var2, var3);
    }

    private void actionPerformed(AvidGuiButton var1) {}

    /**
     * Called when the mouse is clicked.
     */
    protected void mouseClicked(int var1, int var2, int var3)
    {
        super.mouseClicked(var1, var2, var3);

        if (var3 == 0)
        {
            Iterator var4 = windows.iterator();

            while (var4.hasNext())
            {
                AvidGuiWindow var5 = (AvidGuiWindow)var4.next();

                if (var5.mouseClick(var1, var2, var3))
                {
                    windows.remove(var5);
                    windows.add(0, var5);
                    break;
                }
            }
        }
    }

    /**
     * Called from the main game loop to update the screen.
     */
    public void updateScreen()
    {
        Iterator var2 = windows.iterator();

        while (var2.hasNext())
        {
            AvidGuiWindow var1 = (AvidGuiWindow)var2.next();
            var1.updateScreen();
        }
    }

    /**
     * Adds the buttons (and other controls) to the screen in question.
     */
    public void initGui() {}

    /**
     * Called when the mouse is moved or a mouse button is released.  Signature: (mouseX, mouseY, which) which==-1 is
     * mouseMove, which==0 or which==1 is mouseUp
     */
    public void mouseMovedOrUp(int var1, int var2, int var3)
    {
        super.mouseMovedOrUp(var1, var2, var3);
        Iterator var5 = windows.iterator();

        while (var5.hasNext())
        {
            AvidGuiWindow var4 = (AvidGuiWindow)var5.next();
            var4.mouseMovedOrUp(var1, var2, var3);
        }
    }

    /**
     * Fired when a key is typed. This is the equivalent of KeyListener.keyTyped(KeyEvent e).
     */
    protected void keyTyped(char var1, int var2)
    {
        Iterator var4 = windows.iterator();

        while (var4.hasNext())
        {
            AvidGuiWindow var3 = (AvidGuiWindow)var4.next();
            var3.keyTyped(var1, var2);
        }

        if (var2 == 1)
        {
            this.mc.displayGuiScreen((GuiScreen)null);
        }
    }
}
