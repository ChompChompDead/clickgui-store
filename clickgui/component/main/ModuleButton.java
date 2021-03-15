package com.teddyhack.client.ui.clickgui.component.main;

import com.teddyhack.client.Teddyhack;
import com.teddyhack.api.event.events.EventNotifier;
import com.teddyhack.client.module.Module;
import com.teddyhack.client.ui.clickgui.component.Component;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;

import java.awt.Color;
import java.util.ArrayList;

public class ModuleButton extends Component {

    // WTF ANOTHER VARIABLE???
    public int x, y, width, height;
    public boolean settingOpen = false;
    public ArrayList<ModuleButton> moduleButtons;

    Module module;
    public Frame parent;
    Minecraft mc = Minecraft.getMinecraft();

    // (contrsuctor)
    public ModuleButton(Module module, int x, int y, Frame parent) {
        this.module = module;
        this.x = x;
        this.y = y;
        this.parent = parent;
        this.width = parent.width;
        this.height = 14;
        moduleButtons = new ArrayList<>();
    }

    //draw and click im cool your not stupid thot
    public void draw(int MouseX, int MouseY) {
        if (parent.getCategory().open) {
            Gui.drawRect(x, y + 1, x + width, y + 15, new Color(0, 0, 0, 100).getRGB());
        }
        if (module.toggled) {
            mc.fontRenderer.drawString(module.getName(), x + 2, y + 3, new Color(120,63,4).getRGB());
        } else {
            mc.fontRenderer.drawString(module.getName(), x + 2, y + 3, new Color(255, 255, 255).getRGB());
        }
    }

    public void onClick(int MouseX, int MouseY, int Button) {
        if (onButton(MouseX, MouseY) && Button == 0) {
            module.toggle();
            Teddyhack.onEvent(new EventNotifier(module.name, module.toggled));
            Teddyhack.log.info(module.name);
        }
        if (onButton(MouseX, MouseY) && Button == 1) {
            settingOpen = !settingOpen;
        }
    }

    public boolean onButton(int x, int y) {
        return x >= this.x && x <= this.x + this.width && y >= this.y && y <= this.y + this.height;
    }
}