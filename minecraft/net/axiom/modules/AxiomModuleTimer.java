package net.axiom.modules;

import net.axiom.AxiomHelper;
import net.axiom.AxiomSetup;
import net.axiom.AxiomWrapper;
import net.axiom.ui.CustomFontRenderer;

public class AxiomModuleTimer extends AxiomModule
{
    private int tcolor = -1;
    public long endTime = 0L;

    public AxiomModuleTimer()
    {
        super("Timer", 16777215, true);
    }

    public void prepareMod()
    {
        this.enabled = true;
    }

    public void onRenderGUI()
    {
        long var1 = this.endTime - System.nanoTime() / 1000000L;
        int var3 = (int)(var1 / 1000L) % 60;
        int var4 = (int)(var1 / 60000L % 60L);
        int var5 = (int)(var1 / 3600000L % 24L);
        String var6 = String.valueOf(var5);
        String var7 = String.valueOf(var4);
        String var8 = String.valueOf(var3);
        var6 = var6.length() < 2 ? "0" + var6 : var6;
        var7 = var7.length() < 2 ? "0" + var7 : var7;
        var8 = var8.length() < 2 ? "0" + var8 : var8;
        String var9 = var6 + ":" + var7 + ":" + var8;

        if (var1 > 0L)
        {
            CustomFontRenderer var10 = AxiomSetup.customFont;
            var10.drawStringS(AxiomWrapper.mcObj.ingameGUI, var9, 2, 12, this.tcolor);
        }
    }

    public boolean onCommand(String var1, String[] var2, String var3)
    {
        if (var1.equalsIgnoreCase("time"))
        {
            if (var2.length < 1)
            {
                return true;
            }
            else
            {
                try
                {
                    long var4 = Long.parseLong(var2[0]);
                    var4 *= 1000L;
                    this.endTime = System.nanoTime() / 1000000L + var4;
                }
                catch (Exception var6)
                {
                    AxiomHelper.chatMsg("Invalid syntax; time [seconds].");
                }

                return true;
            }
        }
        else
        {
            return false;
        }
    }
}
