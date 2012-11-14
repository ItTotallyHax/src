package net.minecraft.src;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import net.minecraft.client.Minecraft;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class ThreadDownloadResources extends Thread
{
    /** The folder to store the resources in. */
    public File resourcesFolder;

    /** A reference to the Minecraft object. */
    private Minecraft mc;

    /** Set to true when Minecraft is closing down. */
    private boolean closing = false;
    private DataOutputStream var7;

    public ThreadDownloadResources(File par1File, Minecraft par2Minecraft)
    {
        this.mc = par2Minecraft;
        this.setName("Resource download thread");
        this.setDaemon(true);
        this.resourcesFolder = new File(par1File, "resources/");

        if (!this.resourcesFolder.exists() && !this.resourcesFolder.mkdirs())
        {
            throw new RuntimeException("The working directory could not be created: " + this.resourcesFolder);
        }
    }

    public void run()
    {
        try
        {
            URL var1 = new URL("http://s3.amazonaws.com/MinecraftResources/");
            DocumentBuilderFactory var2 = DocumentBuilderFactory.newInstance();
            DocumentBuilder var3 = var2.newDocumentBuilder();
            Document var4 = var3.parse(var1.openStream());
            NodeList var5 = var4.getElementsByTagName("Contents");

            for (int var6 = 0; var6 < 2; ++var6)
            {
                for (int var7x = 0; var7x < var5.getLength(); ++var7x)
                {
                    Node var8 = var5.item(var7x);

                    if (var8.getNodeType() == 1)
                    {
                        Element var9 = (Element)var8;
                        String var10 = var9.getElementsByTagName("Key").item(0).getChildNodes().item(0).getNodeValue();
                        long var11 = Long.parseLong(var9.getElementsByTagName("Size").item(0).getChildNodes().item(0).getNodeValue());

                        if (var11 > 0L)
                        {
                            this.downloadAndInstallResource(var1, var10, var11, var6);

                            if (this.closing)
                            {
                                return;
                            }
                        }
                    }
                }
            }
        }
        catch (Exception var13)
        {
            this.loadResource(this.resourcesFolder, "");
            var13.printStackTrace();
        }
    }

    /**
     * Reloads the resource folder and passes the resources to Minecraft to install.
     */
    public void reloadResources()
    {
        this.loadResource(this.resourcesFolder, "");
    }

    /**
     * Loads a resource and passes it to Minecraft to install.
     */
    private void loadResource(File par1File, String par2Str)
    {
        File[] var3 = par1File.listFiles();
        File[] var4 = var3;
        int var5 = var3.length;

        for (int var6 = 0; var6 < var5; ++var6)
        {
            File var7x = var4[var6];

            if (var7x.isDirectory())
            {
                this.loadResource(var7x, par2Str + var7x.getName() + "/");
            }
            else
            {
                try
                {
                    this.mc.installResource(par2Str + var7x.getName(), var7x);
                }
                catch (Exception var9)
                {
                    System.out.println("Failed to add " + par2Str + var7x.getName());
                }
            }
        }
    }

    /**
     * Downloads the resource and saves it to disk then installs it.
     */
    private void downloadAndInstallResource(URL par1URL, String par2Str, long par3, int par5)
    {
        try
        {
            int var6 = par2Str.indexOf("/");
            String var7x = par2Str.substring(0, var6);

            if (!var7x.equals("sound") && !var7x.equals("newsound"))
            {
                if (par5 != 1)
                {
                    return;
                }
            }
            else if (par5 != 0)
            {
                return;
            }

            File var8 = new File(this.resourcesFolder, par2Str);

            if (!var8.exists() || var8.length() != par3)
            {
                var8.getParentFile().mkdirs();
                String var9 = par2Str.replaceAll(" ", "%20");
                this.downloadResource(new URL(par1URL, var9), var8, par3);

                if (this.closing)
                {
                    return;
                }
            }

            this.mc.installResource(par2Str, var8);
        }
        catch (Exception var10)
        {
            var10.printStackTrace();
        }
    }

    /**
     * Downloads the resource and saves it to disk.
     */
    private void downloadResource(URL par1URL, File par2File, long par3) throws IOException
    {
        byte[] var5 = new byte[4096];
        DataInputStream var6 = new DataInputStream(par1URL.openStream());
        this.var7 = new DataOutputStream(new FileOutputStream(par2File));
        boolean var7x = false;
        int var8;

        while ((var8 = var6.read(var5)) >= 0)
        {
            this.var7.write(var5, 0, var8);

            if (this.closing)
            {
                return;
            }
        }

        var6.close();
        this.var7.close();
    }

    /**
     * Called when Minecraft is closing down.
     */
    public void closeMinecraft()
    {
        this.closing = true;
    }
}
