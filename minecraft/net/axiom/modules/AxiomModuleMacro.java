package net.axiom.modules;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import net.axiom.AxiomHelper;
import net.axiom.modules.addons.AxiomMacro;
import net.minecraft.client.Minecraft;

public class AxiomModuleMacro extends AxiomModule
{
    private ArrayList macros = new ArrayList();

    public AxiomModuleMacro()
    {
        super("Macro", 16777215, true);
        this.enabled = true;
        this.loadMacros();
    }

    private void loadMacros()
    {
        File var1 = new File(Minecraft.getAppDir("minecraft") + "/axiom");
        File var2 = new File(var1 + "/macros");

        if (!var1.exists())
        {
            var1.mkdir();
        }

        if (!var2.exists())
        {
            var2.mkdir();
        }

        File[] var3 = var2.listFiles();
        int var4 = var3.length;

        for (int var5 = 0; var5 < var4; ++var5)
        {
            File var6 = var3[var5];

            if (var6.getAbsolutePath().toLowerCase().endsWith(".macro"))
            {
                this.macros.add(new AxiomMacro(var6));
            }
        }

        AxiomHelper.consoleMsg(this.macros.size() + " macro(s) loaded.");
    }

    public boolean onCommand(String var1, String[] var2, String var3)
    {
        Iterator var4 = this.macros.iterator();
        AxiomMacro var5;

        do
        {
            if (!var4.hasNext())
            {
                if (var1.equalsIgnoreCase("mload"))
                {
                    this.macros.clear();
                    this.loadMacros();
                    AxiomHelper.chatMsg("Macros reloaded.");
                    return true;
                }

                return false;
            }

            var5 = (AxiomMacro)var4.next();
        }
        while (!var1.equalsIgnoreCase(var5.macroName));

        var5.executeMacro();
        return true;
    }
}
