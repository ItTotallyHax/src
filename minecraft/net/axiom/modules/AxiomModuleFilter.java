package net.axiom.modules;

import java.util.ArrayList;
import java.util.Iterator;
import net.axiom.AxiomHelper;
import net.minecraft.src.Packet3Chat;

public class AxiomModuleFilter extends AxiomModule
{
    public ArrayList filter = new ArrayList();

    public AxiomModuleFilter()
    {
        super("Filter", 16777215, true);
        this.enabled = true;
    }

    public boolean onChat(Packet3Chat var1)
    {
        Iterator var2 = this.filter.iterator();
        String var3;

        do
        {
            if (!var2.hasNext())
            {
                return true;
            }

            var3 = (String)var2.next();
        }
        while (!var1.message.toLowerCase().contains(var3.toLowerCase()));

        return false;
    }

    public boolean onCommand(String var1, String[] var2, String var3)
    {
        if (!var1.equalsIgnoreCase("filter"))
        {
            if (var1.equalsIgnoreCase("fclear"))
            {
                this.filter.clear();
                AxiomHelper.chatMsg("Filter cleared.");
                return true;
            }
            else
            {
                return false;
            }
        }
        else
        {
            if (!var3.contains("\"") || !var3.endsWith("\""))
            {
                AxiomHelper.chatMsg("Invalid syntax.");
            }

            String var4 = var3.split("\"")[1];
            this.filter.add(var4);
            AxiomHelper.chatMsg("\'" + var4 + "\' added to the filter.");
            return true;
        }
    }
}
