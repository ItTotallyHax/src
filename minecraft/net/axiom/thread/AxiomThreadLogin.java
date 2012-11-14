package net.axiom.thread;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import net.axiom.AxiomHelper;
import net.axiom.AxiomWrapper;
import net.minecraft.src.Session;

public class AxiomThreadLogin implements Runnable
{
    public String[] args;

    private AxiomThreadLogin(String[] var1)
    {
        this.args = var1;
        AxiomHelper.consoleMsg("Starting login thread.");
    }

    public AxiomThreadLogin(String var1, String var2)
    {
        this(new String[] {var1, var2});
    }

    private boolean login(String var1, String var2)
    {
        try
        {
            String var3 = "user=" + var1 + "&password=" + var2 + "&version=9001";
            URL var4 = new URL("https://login.minecraft.net");
            URLConnection var5 = var4.openConnection();
            var5.setDoOutput(true);
            OutputStreamWriter var6 = new OutputStreamWriter(var5.getOutputStream());
            var6.write(var3);
            var6.flush();
            BufferedReader var7 = new BufferedReader(new InputStreamReader(var5.getInputStream()));
            String var8;

            if ((var8 = var7.readLine()) != null)
            {
                if (!var8.contains(":"))
                {
                    return false;
                }

                String[] var9 = var8.split(":");
                AxiomWrapper.mcObj.session = new Session(var9[2], var9[3]);
                return true;
            }

            var6.close();
            var7.close();
        }
        catch (Exception var10)
        {
            var10.printStackTrace();
        }

        return false;
    }

    public void run()
    {
        boolean var1 = this.login(this.args[0], this.args[1]);
        String var2 = var1 ? "Success! Reconnect to apply changes." : "Failed.";
        AxiomHelper.chatMsg(var2);

        if (var1)
        {
            AxiomHelper.chatMsg("New account: " + this.args[0]);
        }
    }
}
