package net.axiom.modules.addons;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import net.axiom.AxiomHelper;
import net.axiom.AxiomSetup;

public class AxiomMacro
{
    public String macroName;
    private ArrayList instructions = new ArrayList();

    public AxiomMacro(File var1)
    {
        try
        {
            this.parseMacro(var1);
        }
        catch (IOException var3)
        {
            AxiomHelper.chatMsg("Error parsing macro from " + var1.getName());
        }
    }

    private void parseMacro(File var1) throws IOException
    {
        this.macroName = var1.getName().toLowerCase().replaceAll(".macro", "");
        AxiomHelper.consoleMsg(this.macroName);
        BufferedReader var2 = new BufferedReader(new FileReader(var1));

        while (true)
        {
            String var3 = var2.readLine();

            if (var3 == null)
            {
                AxiomHelper.consoleMsg(this.instructions.size() + " structs");
                return;
            }

            this.instructions.add(var3);
        }
    }

    public void executeMacro()
    {
        Iterator var1 = this.instructions.iterator();

        while (var1.hasNext())
        {
            String var2 = (String)var1.next();
            AxiomSetup.handleCommand(var2);
        }
    }
}
