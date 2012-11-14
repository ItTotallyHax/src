package net.axiom.modules;

import net.axiom.AxiomSetup;
import net.axiom.AxiomWrapper;

public class AxiomModuleGUI extends AxiomModule
{
    public AxiomModuleGUI()
    {
        super("GUI", 52479, "GRAVE", true);
    }

    public void onEnable()
    {
        this.hidden = true;
        AxiomWrapper.mcObj.displayGuiScreen(AxiomSetup.gui);
        this.onDisable();
    }
}
