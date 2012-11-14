package net.axiom.modules;

import net.axiom.AxiomWrapper;
import net.minecraft.src.GuiIngame;
import org.lwjgl.opengl.GL11;

public class AxiomModuleNegative extends AxiomModule
{
    public AxiomModuleNegative()
    {
        super("Negative", 16777215, true);
    }

    public void onRenderGUI()
    {
        this.hidden = true;
        AxiomWrapper.mcObj.entityRenderer.disableLightmap(0.0D);
        GL11.glBindTexture(GL11.GL_TEXTURE_2D, AxiomWrapper.mcObj.renderEngine.getTexture("/misc/axiom/overlay.png"));
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glBlendFunc(GL11.GL_ONE_MINUS_DST_COLOR, GL11.GL_ONE_MINUS_SRC_COLOR);
        int var1 = AxiomWrapper.scaledGui.getScaledWidth();
        int var2 = AxiomWrapper.scaledGui.getScaledHeight();
        GuiIngame var3 = AxiomWrapper.mcObj.ingameGUI;
        var3.drawTexturedModalRect(0, 0, 0, 0, var1, var2);
        GL11.glDisable(GL11.GL_BLEND);
        AxiomWrapper.mcObj.entityRenderer.enableLightmap(0.0D);
    }

    public float onGammaSet(float var1)
    {
        return 200.0F;
    }
}
