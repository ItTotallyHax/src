package net.axiom.ui;

import net.axiom.AxiomHelper;
import net.axiom.AxiomWrapper;
import net.axiom.modules.AxiomModuleWallhack;

public class AxiomWindowWallhack extends AxiomWindow
{
    public AxiomModuleWallhack Wallhack;

    public AxiomWindowWallhack()
    {
        super(0, 12, "Wallhack");
        this.buttons.add(new AxiomOption(0, false, "Manmade", this));
        this.buttons.add(new AxiomOption(1, false, "Coal", this));
        this.buttons.add(new AxiomOption(2, false, "Iron", this));
        this.buttons.add(new AxiomOption(3, false, "Gold", this));
        this.buttons.add(new AxiomOption(4, false, "Diamond", this));
        this.buttons.add(new AxiomOption(5, false, "Redstone", this));
        this.buttons.add(new AxiomOption(6, false, "Emerald", this));
        this.buttons.add(new AxiomOption(7, false, "Circuits", this));
        this.buttons.add(new AxiomOption(8, false, "Dungeon", this));
        this.buttons.add(new AxiomOption(9, false, "Hold", this));
        this.buttons.add(new AxiomOption(10, false, "Hazards", this));
    }

    public void buttonClicks(int var1)
    {
        super.buttonClicks(var1);
        this.Wallhack = (AxiomModuleWallhack)AxiomHelper.getModByName("Wallhack");

        switch (var1)
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

        if (var1 >= 0 && var1 <= 9 && this.Wallhack.enabled)
        {
            AxiomWrapper.mcObj.renderGlobal.loadRenderers();
        }
    }
}
