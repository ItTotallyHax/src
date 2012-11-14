package net.axiom.modules;

import net.axiom.AxiomSetup;

public class AxiomModuleAntiAFK extends AxiomModule
{
    public long currentms = 0L;
    public long threshold = 1000L;
    public long lastJump = 0L;

    public AxiomModuleAntiAFK()
    {
        super("AntiAFK", 16777215, "none", false);
    }

    public void prepareMod()
    {
        this.currentms = System.nanoTime() / 1000000L;
    }

    public void onPlayerUpdate()
    {
        if (this.currentms - this.lastJump >= this.threshold)
        {
            AxiomSetup.handleCommand(".jump");
            this.lastJump = System.nanoTime() / 1000000L;
        }
    }
}
