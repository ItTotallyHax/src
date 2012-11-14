package net.axiom.modules;

import net.axiom.thread.AxiomThreadAlt;

public class AxiomModuleAlts extends AxiomModule
{
    private final String apiKey = "443cb001c138b2561a0d90720d6ce111";

    public AxiomModuleAlts()
    {
        super("Alts", 16777215, true);
    }

    public void onEnable()
    {
        this.onDisable();
    }

    public boolean onCommand(String var1, String[] var2, String var3)
    {
        if (var1.equalsIgnoreCase("alt"))
        {
            Thread var10000 = new Thread;
            AxiomThreadAlt var10002 = new AxiomThreadAlt("443cb001c138b2561a0d90720d6ce111");
            this.getClass();
            var10000.<init>(var10002, "Axiom Alt Thread");
            Thread var4 = var10000;
            var4.start();
            return true;
        }
        else
        {
            return false;
        }
    }
}
