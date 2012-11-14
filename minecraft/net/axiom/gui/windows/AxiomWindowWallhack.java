package net.axiom.gui.windows;

import java.util.List;
import net.axiom.AxiomHelper;
import net.axiom.AxiomWrapper;
import net.axiom.gui.components.AvidGuiButton;
import net.axiom.gui.components.AvidGuiWindow;
import net.axiom.modules.AxiomModuleWallhack;

public class AxiomWindowWallhack extends AvidGuiWindow
{
    private AxiomModuleWallhack Wallhack;

    public AxiomWindowWallhack(int var1, int var2)
    {
        super(var1, var2, "Wallhack");
    }

    protected void actionPerformed(AvidGuiButton var1)
    {
        if (this.Wallhack == null)
        {
            this.Wallhack = (AxiomModuleWallhack)AxiomHelper.getModByName("Wallhack");
        }

        int var2 = var1.id;

        switch (var2)
        {
            case 0:
                this.Wallhack.manmade = !this.Wallhack.manmade;
                break;

            case 1:
                this.Wallhack.coal = !this.Wallhack.coal;
                break;

            case 2:
                this.Wallhack.iron = !this.Wallhack.iron;
                break;

            case 3:
                this.Wallhack.gold = !this.Wallhack.gold;
                break;

            case 4:
                this.Wallhack.diamond = !this.Wallhack.diamond;
                break;

            case 5:
                this.Wallhack.redstone = !this.Wallhack.redstone;
                break;

            case 6:
                this.Wallhack.emerald = !this.Wallhack.emerald;
                break;

            case 7:
                this.Wallhack.circuits = !this.Wallhack.circuits;
                break;

            case 8:
                this.Wallhack.dungeons = !this.Wallhack.dungeons;
                break;

            case 9:
                this.Wallhack.strongholds = !this.Wallhack.strongholds;
                break;

            case 10:
                this.Wallhack.hazards = !this.Wallhack.hazards;
        }

        if (var2 > -1 && var2 < 12 && this.Wallhack.enabled)
        {
            AxiomWrapper.mcObj.renderGlobal.loadRenderers();
        }

        this.start();
    }

    public void start()
    {
        this.controlList.clear();
        this.controlList2.clear();

        if (this.Wallhack == null)
        {
            this.Wallhack = (AxiomModuleWallhack)AxiomHelper.getModByName("Wallhack");
        }

        byte var1 = 0;
        List var10000 = this.controlList;
        AvidGuiButton var10001 = new AvidGuiButton;
        int var2 = var1 + 6;
        var10001.<init>(0, 4, var2, this.Wallhack.manmade);
        var10000.add(var10001);
        var10000 = this.controlList;
        var10001 = new AvidGuiButton;
        var2 += 14;
        var10001.<init>(1, 4, var2, this.Wallhack.coal);
        var10000.add(var10001);
        var10000 = this.controlList;
        var10001 = new AvidGuiButton;
        var2 += 14;
        var10001.<init>(2, 4, var2, this.Wallhack.iron);
        var10000.add(var10001);
        var10000 = this.controlList;
        var10001 = new AvidGuiButton;
        var2 += 14;
        var10001.<init>(3, 4, var2, this.Wallhack.gold);
        var10000.add(var10001);
        var10000 = this.controlList;
        var10001 = new AvidGuiButton;
        var2 += 14;
        var10001.<init>(4, 4, var2, this.Wallhack.diamond);
        var10000.add(var10001);
        var10000 = this.controlList;
        var10001 = new AvidGuiButton;
        var2 += 14;
        var10001.<init>(5, 4, var2, this.Wallhack.redstone);
        var10000.add(var10001);
        var10000 = this.controlList;
        var10001 = new AvidGuiButton;
        var2 += 14;
        var10001.<init>(6, 4, var2, this.Wallhack.emerald);
        var10000.add(var10001);
        var10000 = this.controlList;
        var10001 = new AvidGuiButton;
        var2 += 14;
        var10001.<init>(7, 4, var2, this.Wallhack.circuits);
        var10000.add(var10001);
        var10000 = this.controlList;
        var10001 = new AvidGuiButton;
        var2 += 14;
        var10001.<init>(8, 4, var2, this.Wallhack.dungeons);
        var10000.add(var10001);
        var10000 = this.controlList;
        var10001 = new AvidGuiButton;
        var2 += 14;
        var10001.<init>(9, 4, var2, this.Wallhack.strongholds);
        var10000.add(var10001);
        var10000 = this.controlList;
        var10001 = new AvidGuiButton;
        var2 += 14;
        var10001.<init>(10, 4, var2, this.Wallhack.hazards);
        var10000.add(var10001);
    }

    public void drawPanel()
    {
        super.drawPanel();
        byte var1 = 0;
        int var2 = var1 + 6;
        AxiomWrapper.mcObj.fontRenderer.drawString("Manmade", 52, var2, this.getColor(this.Wallhack.manmade));
        var2 += 14;
        AxiomWrapper.mcObj.fontRenderer.drawString("Coal", 52, var2, this.getColor(this.Wallhack.coal));
        var2 += 14;
        AxiomWrapper.mcObj.fontRenderer.drawString("Iron", 52, var2, this.getColor(this.Wallhack.iron));
        var2 += 14;
        AxiomWrapper.mcObj.fontRenderer.drawString("Gold", 52, var2, this.getColor(this.Wallhack.gold));
        var2 += 14;
        AxiomWrapper.mcObj.fontRenderer.drawString("Diamond", 52, var2, this.getColor(this.Wallhack.diamond));
        var2 += 14;
        AxiomWrapper.mcObj.fontRenderer.drawString("Redstone", 52, var2, this.getColor(this.Wallhack.redstone));
        var2 += 14;
        AxiomWrapper.mcObj.fontRenderer.drawString("Emerald", 52, var2, this.getColor(this.Wallhack.emerald));
        var2 += 14;
        AxiomWrapper.mcObj.fontRenderer.drawString("Circuits", 52, var2, this.getColor(this.Wallhack.circuits));
        var2 += 14;
        AxiomWrapper.mcObj.fontRenderer.drawString("Dungeons", 52, var2, this.getColor(this.Wallhack.dungeons));
        var2 += 14;
        AxiomWrapper.mcObj.fontRenderer.drawString("Hold", 52, var2, this.getColor(this.Wallhack.strongholds));
        var2 += 14;
        AxiomWrapper.mcObj.fontRenderer.drawString("Hazards", 52, var2, this.getColor(this.Wallhack.hazards));
    }

    public int getColor(boolean var1)
    {
        int var2 = var1 ? -16724737 : -10592674;
        return var2;
    }

    public int getWidth()
    {
        return 104;
    }

    public int getHeight()
    {
        return 170;
    }
}
