package net.axiom.ui;

import java.text.DecimalFormat;
import java.util.Iterator;
import net.axiom.AxiomSetup;
import net.axiom.AxiomWrapper;
import net.axiom.modules.AxiomModule;
import net.minecraft.src.EntityClientPlayerMP;
import net.minecraft.src.FontRenderer;
import net.minecraft.src.ScaledResolution;

public class AxiomIGGUI
{
    public static boolean hidden = false;

    public static void renderOther()
    {
        Iterator var0 = AxiomSetup.modules.iterator();

        while (var0.hasNext())
        {
            AxiomModule var1 = (AxiomModule)var0.next();

            if (var1.enabled)
            {
                var1.onRenderGUI();
            }
        }
    }

    public static void renderAxiomTag()
    {
        FontRenderer var0 = AxiomWrapper.mcObj.fontRenderer;
        var0.drawStringWithShadow("Axiom v" + AxiomSetup.version + " (1.3.2)", 2, 2, 16777215);
    }

    public static void renderModTags()
    {
        ScaledResolution var0 = AxiomWrapper.scaledGui;
        FontRenderer var1 = AxiomWrapper.mcObj.fontRenderer;
        int var2 = 2;
        Iterator var3 = AxiomSetup.modules.iterator();

        while (var3.hasNext())
        {
            AxiomModule var4 = (AxiomModule)var3.next();
            var4.prepareMod();

            if (!var4.hidden && var4.enabled)
            {
                int var5 = var0.getScaledWidth() - var1.getStringWidth(var4.modTag) - 2;
                var1.drawStringWithShadow(var4.modTag, var5, var2, var4.modColor);
                var2 += 10;
            }
        }
    }

    public static void renderCoordinates()
    {
        EntityClientPlayerMP var0 = AxiomWrapper.mcObj.thePlayer;
        FontRenderer var1 = AxiomWrapper.mcObj.fontRenderer;
        ScaledResolution var2 = AxiomWrapper.scaledGui;
        DecimalFormat var3 = new DecimalFormat("##.#");
        var3.setDecimalSeparatorAlwaysShown(true);
        var3.setMinimumFractionDigits(1);
        var3.setMaximumFractionDigits(1);
        String var4 = var3.format(var0.posX);
        String var5 = var3.format(var0.posY);
        String var6 = var3.format(var0.posZ);
        String var7 = "X: " + var4;
        String var8 = "Y: " + var5;
        String var9 = "Z: " + var6;
        var1.drawStringWithShadow(var7, var2.getScaledWidth() - var1.getStringWidth(var7) - 2, var2.getScaledHeight() - 30, 16777215);
        var1.drawStringWithShadow(var8, var2.getScaledWidth() - var1.getStringWidth(var8) - 2, var2.getScaledHeight() - 20, 16777215);
        var1.drawStringWithShadow(var9, var2.getScaledWidth() - var1.getStringWidth(var9) - 2, var2.getScaledHeight() - 10, 16777215);
    }
}
