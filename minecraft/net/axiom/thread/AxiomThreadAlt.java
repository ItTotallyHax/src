package net.axiom.thread;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import net.axiom.AxiomHelper;
import net.axiom.AxiomWrapper;

public class AxiomThreadAlt implements Runnable
{
    private String apiKey;

    public AxiomThreadAlt(String var1)
    {
        this.apiKey = var1;
        AxiomHelper.consoleMsg("Starting alt account login thread.");
    }

    public void run()
    {
        String[] var1 = this.fetchAlt();

        if (var1 != null)
        {
            AxiomWrapper.mcObj.session.username = var1[0];
            AxiomWrapper.mcObj.session.sessionId = var1[1];
            AxiomHelper.chatMsg("New account: " + var1[0]);
        }
    }

    public String[] fetchAlt()
    {
        String[] var1 = null;

        try
        {
            URL var2 = new URL("http://alts.me/api.php?apikey=" + this.apiKey);
            URLConnection var3 = var2.openConnection();
            BufferedReader var4 = new BufferedReader(new InputStreamReader(var3.getInputStream()));
            String var5 = var4.readLine();
            var4.close();
            var1 = var5.split(":");

            if (var1.length != 2)
            {
                var1 = null;
            }
        }
        catch (Exception var6)
        {
            var6.printStackTrace();
        }

        return var1;
    }
}
