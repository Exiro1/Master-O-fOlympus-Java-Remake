package com.exiro.render;

import com.exiro.depacking.TileImage;
import com.exiro.fileManager.ImageLoader;

import java.awt.*;

public class Button {


    int x, y, w, h;
    TileImage image;
    ButtonType type;
    int id = 0;

    public Button(int x, int y, int w, int h, int bitid, int id, ButtonType type) {
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
        this.image = ImageLoader.getImage("Zeus_Interface", bitid, id);
        this.type = type;
        this.id = id;
    }


    public void setClicked(boolean clicked) {
        if (clicked) {
            this.image = ImageLoader.getImage("Zeus_Interface", 7, id + 1);
        } else {
            this.image = ImageLoader.getImage("Zeus_Interface", 7, id);
        }
    }


    public boolean clicked(int xc, int yc) {
        if (xc > x && xc < x + w && yc > y && yc < y + h) {
            return true;
        }
        return false;
    }

    public ButtonType getType() {
        return type;
    }

    public void Render(Graphics g) {
        g.drawImage(image.getImg(), (x), (y), (w), (h), null);
    }

}