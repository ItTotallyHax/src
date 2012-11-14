package net.axiom.ui;

import java.util.ArrayList;
import java.util.Iterator;
import net.minecraft.src.GuiScreen;

public final class AxiomGUI extends GuiScreen
{
    public ArrayList windows = new ArrayList();
    public AxiomWindow top;

    public AxiomGUI()
    {
        this.loadWindows();
    }

    public void loadWindows()
    {
        this.windows.add(new AxiomWindowWallhack());
        this.windows.add(new AxiomWindowKillAura());
        this.windows.add(new AxiomWindowTracers());
        this.windows.add(new AxiomWindowRetard());
        this.top = (AxiomWindow)this.windows.get(0);
    }

    public void resetWindowPositions()
    {
        AxiomWindow var2;

        for (Iterator var1 = this.windows.iterator(); var1.hasNext(); var2.dragX = 0)
        {
            var2 = (AxiomWindow)var1.next();
            var2.maxed = false;
        }

        int var4 = 12;

        for (Iterator var5 = this.windows.iterator(); var5.hasNext(); var4 += 14)
        {
            AxiomWindow var3 = (AxiomWindow)var5.next();
            var3.dragY = var4;
        }
    }

    /**
     * Called when the mouse is moved or a mouse button is released.  Signature: (mouseX, mouseY, which) which==-1 is
     * mouseMove, which==0 or which==1 is mouseUp
     */
    protected void mouseMovedOrUp(int var1, int var2, int var3)
    {
        AxiomWindow var5;

        if (var3 == 0)
        {
            for (Iterator var4 = this.windows.iterator(); var4.hasNext(); var5.drag = false)
            {
                var5 = (AxiomWindow)var4.next();
            }
        }
    }

    /**
     * Called when the mouse is clicked.
     */
    protected void mouseClicked(int var1, int var2, int var3)
    {
        Iterator var4 = this.windows.iterator();

        while (var4.hasNext())
        {
            AxiomWindow var5 = (AxiomWindow)var4.next();
            var5.clicked(var1, var2);
        }

        super.mouseClicked(var1, var2, var3);
    }

    /**
     * Fired when a key is typed. This is the equivalent of KeyListener.keyTyped(KeyEvent e).
     */
    protected void keyTyped(char var1, int var2)
    {
        if (var2 == 1)
        {
            this.mc.displayGuiScreen((GuiScreen)null);
        }
    }

    /**
     * Draws the screen and all the components in it.
     */
    public void drawScreen(int var1, int var2, float var3)
    {
        this.drawDefaultBackground();
        Iterator var4 = this.windows.iterator();
        AxiomWindow var5;

        while (var4.hasNext())
        {
            var5 = (AxiomWindow)var4.next();

            if (var5 != this.top)
            {
                this.top = var5.mouseDragged(var1, var2) ? var5 : this.top;
                var5.drawWindow();
            }
        }

        var4 = this.windows.iterator();

        while (var4.hasNext())
        {
            var5 = (AxiomWindow)var4.next();

            if (var5 == this.top)
            {
                var5.mouseDragged(var1, var2);
                var5.drawWindow();
            }
        }
    }
}
