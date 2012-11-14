package net.axiom.gui.screens;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import javax.net.ssl.HttpsURLConnection;
import net.axiom.AxiomSetup;
import net.axiom.gui.components.GuiPasswordField;
import net.minecraft.src.GuiButton;
import net.minecraft.src.GuiScreen;
import net.minecraft.src.GuiTextField;
import net.minecraft.src.StringTranslate;
import net.minecraft.src.StringUtils;
import org.lwjgl.input.Keyboard;

public class AxiomGuiAltSwitch extends GuiScreen
{
    int showStatusTimer = 0;
    public String failed;
    public static boolean showStatus;
    private GuiScreen m_gParent;
    private GuiTextField textField;
    private GuiPasswordField passwordField;
    private boolean m_bSet;
    private String error = "Logged in!";

    public AxiomGuiAltSwitch(GuiScreen var1)
    {
        this.m_gParent = var1;
    }

    /**
     * Called from the main game loop to update the screen.
     */
    public void updateScreen()
    {
        this.textField.updateCursorCounter();
        this.passwordField.updateCursorCounter();

        if (this.showStatusTimer > 0 && showStatus)
        {
            --this.showStatusTimer;
        }

        if (this.showStatusTimer <= 0)
        {
            showStatus = false;
        }
    }

    /**
     * Adds the buttons (and other controls) to the screen in question.
     */
    public void initGui()
    {
        StringTranslate var1 = StringTranslate.getInstance();
        Keyboard.enableRepeatEvents(true);
        this.controlList.clear();
        int var2 = this.height / 4 + 80;
        this.textField = new GuiTextField(this.fontRenderer, this.width / 2 - 100, var2 - 60, 200, 20);
        this.textField.setMaxStringLength(50);
        this.passwordField = new GuiPasswordField(this.fontRenderer, this.width / 2 - 100, var2 - 24, 200, 20);
        this.passwordField.setMaxStringLength(50);
        var2 += 34;
        this.controlList.add(new GuiButton(0, this.width / 2 - 100, var2, 98, 20, "Login"));
        this.controlList.add(new GuiButton(2, this.width / 2 + 2, var2, 98, 20, "Alt"));
        this.controlList.add(new GuiButton(1, this.width / 2 - 100, var2 + 24, "Back"));
    }

    /**
     * Called when the screen is unloaded. Used to disable keyboard repeat events
     */
    public void onGuiClosed()
    {
        Keyboard.enableRepeatEvents(false);
    }

    /**
     * Fired when a control is clicked. This is the equivalent of ActionListener.actionPerformed(ActionEvent e).
     */
    protected void actionPerformed(GuiButton var1)
    {
        if (var1.enabled)
        {
            if (var1.id == 0)
            {
                showStatus = true;
                this.showStatusTimer = 120;

                if (this.passwordField.getText().length() > 0)
                {
                    String var2 = this.textField.getText();
                    String var3 = this.passwordField.getText();
                    AxiomSetup.handleCommand(".login " + var2 + " " + var3);
                }
                else
                {
                    this.mc.session.username = this.textField.getText();
                }
            }

            if (var1.id == 1)
            {
                this.mc.displayGuiScreen(this.m_gParent);
                showStatus = false;
            }

            if (var1.id == 2)
            {
                AxiomSetup.handleCommand(".alt");
            }
        }
    }

    /**
     * Fired when a key is typed. This is the equivalent of KeyListener.keyTyped(KeyEvent e).
     */
    protected void keyTyped(char var1, int var2)
    {
        this.textField.textboxKeyTyped(var1, var2);
        this.passwordField.textboxKeyTyped(var1, var2);

        if (var1 == 9)
        {
            if (this.textField.isFocused())
            {
                this.textField.setFocused(false);
                this.passwordField.setFocused(true);
            }
            else
            {
                this.textField.setFocused(true);
                this.passwordField.setFocused(false);
            }
        }

        if (var1 == 13)
        {
            this.actionPerformed((GuiButton)this.controlList.get(0));
        }
    }

    /**
     * Called when the mouse is clicked.
     */
    protected void mouseClicked(int var1, int var2, int var3)
    {
        super.mouseClicked(var1, var2, var3);
        this.textField.mouseClicked(var1, var2, var3);
        this.passwordField.mouseClicked(var1, var2, var3);
    }

    public static String excutePost(String var0, String var1)
    {
        HttpsURLConnection var2 = null;
        DataOutputStream var4;

        try
        {
            URL var3;

            try
            {
                var3 = new URL(var0);
                var2 = (HttpsURLConnection)var3.openConnection();
                var2.setRequestMethod("POST");
                var2.setRequestProperty("Content-Type", "application/form-urlencoded");
                var2.setRequestProperty("Content-Length", Integer.toString(var1.getBytes().length));
                var2.setRequestProperty("Content-Launguage", "en-US");
                var2.setUseCaches(false);
                var2.setDoInput(true);
                var2.setDoOutput(true);
                var2.connect();
                var4 = new DataOutputStream(var2.getOutputStream());
                var4.writeBytes(var1);
                var4.flush();
                var4.close();
                InputStream var5 = var2.getInputStream();
                BufferedReader var6 = new BufferedReader(new InputStreamReader(var5));
                StringBuffer var7 = new StringBuffer();
                String var8;

                while ((var8 = var6.readLine()) != null)
                {
                    var7.append(var8);
                    var7.append('\r');
                }

                var6.close();
                String var9 = var7.toString();
                String var11 = var9;
                return var11;
            }
            catch (Exception var15)
            {
                var15.printStackTrace();
                var3 = null;
                var4 = var3;
            }
        }
        finally
        {
            if (var2 != null)
            {
                var2.disconnect();
            }
        }

        return var4;
    }

    /**
     * Draws the screen and all the components in it.
     */
    public void drawScreen(int var1, int var2, float var3)
    {
        int var4 = this.height / 4 + 80;
        this.drawDefaultBackground();
        this.drawCenteredString(this.fontRenderer, "Premium Account Login", this.width / 2, var4 - 92, 16777215);
        this.drawString(this.fontRenderer, "Username", this.width / 2 - 100, var4 - 72, 10526880);
        this.drawString(this.fontRenderer, "Password", this.width / 2 - 100, var4 - 36, 10526880);

        if (showStatus)
        {
            if (this.error.contains(":"))
            {
                this.drawCenteredString(this.fontRenderer, "Logged in!", this.width / 2, var4 + 4, -16716254);
            }
            else
            {
                this.drawCenteredString(this.fontRenderer, StringUtils.stripControlCodes(this.error), this.width / 2, var4 + 4, -1179614);
            }
        }
        else
        {
            this.fontRenderer.drawString("Logged in as: " + this.mc.session.username, this.width / 2 - 100, var4 + 4, 10526880);
            this.fontRenderer.drawString("Status: " + (this.mc.session.sessionId.length() > 1 ? "\u00a72Premium" : "\u00a7cOffline"), this.width / 2 - 100, var4 + 14, 10526880);
        }

        this.textField.drawTextBox();
        this.passwordField.drawTextBox();
        super.drawScreen(var1, var2, var3);
    }
}
