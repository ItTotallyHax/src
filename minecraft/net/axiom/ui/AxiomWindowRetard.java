package net.axiom.ui;

import java.util.Iterator;
import net.axiom.AxiomHelper;
import net.axiom.modules.AxiomModuleRetard;

public class AxiomWindowRetard extends AxiomWindow
{
    private AxiomModuleRetard Retard;

    public AxiomWindowRetard()
    {
        super(0, 54, "Retard Mode");
        this.buttons.add(new AxiomOption(0, false, "Derp", this));
        this.buttons.add(new AxiomOption(1, false, "Headless", this));
        this.buttons.add(new AxiomOption(2, false, "Backward", this));
    }

    public void buttonClicks(int var1)
    {
        super.buttonClicks(var1);
        this.Retard = (AxiomModuleRetard)AxiomHelper.getModByName("Retard");
        Iterator var2;
        AxiomOption var3;

        switch (var1)
        {
            case 0:
                this.Retard.clearChoices();

                for (var2 = this.buttons.iterator(); var2.hasNext(); var3.state = false)
                {
                    var3 = (AxiomOption)var2.next();
                }

                this.Retard.derp = !this.Retard.derp;
                this.getButtonByID(var1).state = this.Retard.derp;
                break;

            case 1:
                this.Retard.clearChoices();

                for (var2 = this.buttons.iterator(); var2.hasNext(); var3.state = false)
                {
                    var3 = (AxiomOption)var2.next();
                }

                this.Retard.headless = !this.Retard.headless;
                this.getButtonByID(var1).state = this.Retard.headless;
                break;

            case 2:
                this.Retard.clearChoices();

                for (var2 = this.buttons.iterator(); var2.hasNext(); var3.state = false)
                {
                    var3 = (AxiomOption)var2.next();
                }

                this.Retard.backward = !this.Retard.backward;
                this.getButtonByID(var1).state = this.Retard.backward;
        }
    }
}
