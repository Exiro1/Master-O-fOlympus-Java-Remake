package com.exiro.render.layout;

import com.exiro.fileManager.FontLoader;
import com.exiro.fileManager.ImageLoader;
import com.exiro.object.ObjectType;
import com.exiro.render.Button;
import com.exiro.render.ButtonType;
import com.exiro.render.ComplexButton;
import com.exiro.render.EntityRender;
import com.exiro.render.interfaceList.Interface;
import com.exiro.render.interfaceList.TextInterface;
import com.exiro.systemCore.GameManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class InterfaceRender extends JPanel {
    private final GameManager gm;

    BufferedImage bg, sideLine, test;

    int oWIDTH = 186;
    int oHEIGHT = 768;
    int WIDTH = 320;

    double fw = 0;
    double fh = 0;

    ArrayList<Button> buttons;
    ArrayList<Button> buildButtons;
    ArrayList<Button> quickButtons;

    ArrayList<Button> onscreenButton;

    public InterfaceRender(GameManager gm) {
        this.gm = gm;
        buttons = new ArrayList<>();
        buildButtons = new ArrayList<>();
        quickButtons = new ArrayList<>();
        onscreenButton = new ArrayList<>();

    }

    public void initGraphics() {
        bg = ImageLoader.getImage("Zeus_Interface", 7, 1).getImg();
        sideLine = ImageLoader.getImage("Zeus_Interface", 7, 3).getImg();
        test = ImageLoader.getImage("Zeus_Interface", 7, 139).getImg();
        fw = (double) WIDTH / (double) oWIDTH;
        fh = (double) getHeight() / (double) oHEIGHT;

        int yoff = 22;
        buttons.add(new Button((int) (6 * fw), (int) (yoff * fh), (int) (44 * fw), (int) (41 * fh), 7, 92, ButtonType.HOUSE));
        yoff += 41;
        buttons.add(new Button((int) (6 * fw), (int) (yoff * fh), (int) (44 * fw), (int) (41 * fh), 7, 96, ButtonType.AGRICULTURE));
        yoff += 41;
        buttons.add(new Button((int) (6 * fw), (int) (yoff * fh), (int) (44 * fw), (int) (41 * fh), 7, 100, ButtonType.INDUSTRY));
        yoff += 41;
        buttons.add(new Button((int) (6 * fw), (int) (yoff * fh), (int) (44 * fw), (int) (41 * fh), 7, 104, ButtonType.MARKET));
        yoff += 41;
        buttons.add(new Button((int) (6 * fw), (int) (yoff * fh), (int) (44 * fw), (int) (41 * fh), 7, 108, ButtonType.HEALTH));
        yoff += 41;
        buttons.add(new Button((int) (6 * fw), (int) (yoff * fh), (int) (44 * fw), (int) (41 * fh), 7, 112, ButtonType.PALACE));
        yoff += 41;
        buttons.add(new Button((int) (6 * fw), (int) (yoff * fh), (int) (44 * fw), (int) (41 * fh), 7, 116, ButtonType.CULTURE));
        yoff += 41;
        buttons.add(new Button((int) (6 * fw), (int) (yoff * fh), (int) (44 * fw), (int) (41 * fh), 7, 120, ButtonType.TEMPLE));
        yoff += 41;
        buttons.add(new Button((int) (6 * fw), (int) (yoff * fh), (int) (44 * fw), (int) (41 * fh), 7, 124, ButtonType.ARMY));
        yoff += 41;
        buttons.add(new Button((int) (6 * fw), (int) (yoff * fh), (int) (44 * fw), (int) (41 * fh), 7, 128, ButtonType.CACHET));
        yoff += 41;
        buttons.add(new Button((int) (6 * fw), (int) (yoff * fh), (int) (44 * fw), (int) (41 * fh), 7, 88, ButtonType.MAPVIEW));

        quickButtons.add(new Button((int) (49 * fw), (int) (435 * fh), (int) (31 * fw), (int) (38 * fh), 7, 44, ButtonType.QUICK_ROUTE));
        quickButtons.add(new Button((int) (81 * fw), (int) (435 * fh), (int) (31 * fw), (int) (38 * fh), 7, 48, ButtonType.QUICK_STOP));
        quickButtons.add(new Button((int) (112 * fw), (int) (435 * fh), (int) (31 * fw), (int) (38 * fh), 7, 52, ButtonType.QUICK_ERASE));
        quickButtons.add(new Button((int) (144 * fw), (int) (435 * fh), (int) (31 * fw), (int) (38 * fh), 7, 56, ButtonType.QUICK_CANCEL));

        quickButtons.add(new Button((int) (139 * fw), (int) (481 * fh), (int) (33 * fw), (int) (31 * fh), 7, 60, ButtonType.QUICK_HISTORY));

        quickButtons.add(new Button((int) (11 * fw), (int) (566 * fh), (int) (32 * fw), (int) (33 * fh), 7, 36, ButtonType.QUICK_MISSION));

        quickButtons.add(new Button((int) (43 * fw), (int) (566 * fh), (int) (66 * fw), (int) (33 * fh), 7, 32, ButtonType.QUICK_VIEW));

        quickButtons.add(new Button((int) (109 * fw), (int) (566 * fh), (int) (66 * fw), (int) (33 * fh), 7, 40, ButtonType.QUICK_MAP));

    }


    public boolean isClicked(int xc, int yc) {
        if (xc > getBounds().x && xc < getBounds().x + getBounds().width && yc > getBounds().y && yc < getBounds().y + getBounds().height) {
            return true;
        }
        return false;
    }

    public ArrayList<Button> getOnScreenButton() {
        return onscreenButton;
    }

    public void paintComponent(Graphics g) {
        //  super.paintComponent(g);


        g.drawImage(bg, 0, 0, WIDTH - 16, getHeight(), null);
        g.drawImage(sideLine, WIDTH - 16, 0, 16, getHeight(), null);
        g.drawImage(test, (int) (50 * fw), (int) (300 * fh), (int) (128 * fw), (int) (18 * fh), null);

        for (Button b : buttons) {
            b.Render(g, 0, 0);
        }
        for (Button b : buildButtons) {
            b.Render(g, 0, 0);
        }
        for (Button b : quickButtons) {
            b.Render(g, 0, 0);
        }

    }

    public void clickManager(MouseEvent e) {

        for (Button b : buttons) {
            if (b.clicked(e.getX() - this.getBounds().x, e.getY() - this.getBounds().y)) {
                buttonClicked(b);
            }
        }
        for (Button b : buildButtons) {
            if (b.clicked(e.getX() - this.getBounds().x, e.getY() - this.getBounds().y)) {
                buttonClicked(b);
            }
        }
        for (Button b : quickButtons) {
            if (b.clicked(e.getX() - this.getBounds().x, e.getY() - this.getBounds().y)) {
                buttonClicked(b);
            }
        }
    }

    public void buttonClicked(Button b) {
        for (Button ob : buttons) {
            ob.setClicked(false);
        }
        for (Button ob : buildButtons) {
            ob.setClicked(false);
        }
        for (Button ob : quickButtons) {
            ob.setClicked(false);
        }
        gm.getFrame().getWindow().gameInterface = null;

        b.setClicked(true);
        switch (b.getType()) {
            case HOUSE:
                buildButtons.clear();
                buildButtons.add(new Button((int) (50 * fw), (int) (325 * fh), (int) (58 * fw), (int) (42 * fh), 8, 3, ButtonType.HOUSE_LITTLE));
                buildButtons.add(new Button((int) (120 * fw), (int) (325 * fh), (int) (58 * fw), (int) (42 * fh), 8, 6, ButtonType.HOUSE_BIG));
                break;
            case AGRICULTURE:
                buildButtons.clear();
                buildButtons.add(new Button((int) (50 * fw), (int) (325 * fh), (int) (58 * fw), (int) (42 * fh), 8, 9, ButtonType.AGRICULTURE_FARM));
                buildButtons.add(new Button((int) (120 * fw), (int) (325 * fh), (int) (58 * fw), (int) (42 * fh), 8, 12, ButtonType.AGRICULTURE_VITICULTURE));
                buildButtons.add(new Button((int) (50 * fw), (int) (375 * fh), (int) (58 * fw), (int) (42 * fh), 8, 15, ButtonType.AGRICULTURE_BREEDING));
                buildButtons.add(new Button((int) (120 * fw), (int) (375 * fh), (int) (58 * fw), (int) (42 * fh), 8, 18, ButtonType.AGRICULTURE_FISH));
                break;
            case INDUSTRY:
                buildButtons.clear();
                buildButtons.add(new Button((int) (50 * fw), (int) (325 * fh), (int) (58 * fw), (int) (42 * fh), 8, 21, ButtonType.INDUSTRY_1));
                buildButtons.add(new Button((int) (120 * fw), (int) (325 * fh), (int) (58 * fw), (int) (42 * fh), 8, 24, ButtonType.INDUSTRY_2));

                break;
            case MARKET:
                buildButtons.clear();
                buildButtons.add(new Button((int) (50 * fw), (int) (325 * fh), (int) (58 * fw), (int) (42 * fh), 8, 30, ButtonType.MARKET_GRANARY));
                buildButtons.add(new Button((int) (120 * fw), (int) (325 * fh), (int) (58 * fw), (int) (42 * fh), 8, 33, ButtonType.MARKET_STOCK));
                buildButtons.add(new Button((int) (50 * fw), (int) (375 * fh), (int) (58 * fw), (int) (42 * fh), 8, 36, ButtonType.MARKET_AGORA));
                buildButtons.add(new Button((int) (120 * fw), (int) (375 * fh), (int) (58 * fw), (int) (42 * fh), 8, 39, ButtonType.MARKET_TRANS));
                break;
            case HEALTH:
                buildButtons.clear();
                buildButtons.add(new Button((int) (50 * fw), (int) (325 * fh), (int) (58 * fw), (int) (42 * fh), 8, 45, ButtonType.HEALTH_WATER));
                buildButtons.add(new Button((int) (120 * fw), (int) (325 * fh), (int) (58 * fw), (int) (42 * fh), 8, 51, ButtonType.HEALTH_HOSPITAL));
                buildButtons.add(new Button((int) (50 * fw), (int) (375 * fh), (int) (58 * fw), (int) (42 * fh), 8, 42, ButtonType.HEALTH_SAFETY));
                buildButtons.add(new Button((int) (120 * fw), (int) (375 * fh), (int) (58 * fw), (int) (42 * fh), 8, 48, ButtonType.HEALTH_GUARD));
                break;
            case PALACE:
                buildButtons.clear();
                buildButtons.add(new Button((int) (50 * fw), (int) (325 * fh), (int) (58 * fw), (int) (42 * fh), 8, 54, ButtonType.PALACE_PALACE));
                buildButtons.add(new Button((int) (120 * fw), (int) (325 * fh), (int) (58 * fw), (int) (42 * fh), 8, 57, ButtonType.PALACE_TAX));
                buildButtons.add(new Button((int) (50 * fw), (int) (375 * fh), (int) (58 * fw), (int) (42 * fh), 8, 60, ButtonType.PALACE_BRIDGE));
                break;
            case CULTURE:
                buildButtons.clear();
                buildButtons.add(new Button((int) (50 * fw), (int) (325 * fh), (int) (58 * fw), (int) (42 * fh), 8, 66, ButtonType.CULTURE_PHILOSOPHIA));
                buildButtons.add(new Button((int) (120 * fw), (int) (325 * fh), (int) (58 * fw), (int) (42 * fh), 8, 69, ButtonType.CULTURE_GYMNASIUM));
                buildButtons.add(new Button((int) (50 * fw), (int) (375 * fh), (int) (58 * fw), (int) (42 * fh), 8, 72, ButtonType.CULTURE_DRAMA));
                buildButtons.add(new Button((int) (120 * fw), (int) (375 * fh), (int) (58 * fw), (int) (42 * fh), 8, 75, ButtonType.CULTURE_STADIUM));
                break;
            case TEMPLE:
                buildButtons.clear();
                buildButtons.add(new Button((int) (50 * fw), (int) (325 * fh), (int) (58 * fw), (int) (42 * fh), 8, 78, ButtonType.TEMPLE_TEMPLE));
                buildButtons.add(new Button((int) (120 * fw), (int) (325 * fh), (int) (58 * fw), (int) (42 * fh), 8, 81, ButtonType.TEMPLE_HEROE));
                buildButtons.add(new Button((int) (50 * fw), (int) (375 * fh), (int) (58 * fw), (int) (42 * fh), 8, 27, ButtonType.TEMPLE_CONSTRUCT));
                break;
            case ARMY:
                buildButtons.clear();
                buildButtons.add(new Button((int) (50 * fw), (int) (325 * fh), (int) (58 * fw), (int) (42 * fh), 8, 84, ButtonType.ARMY_FORT));
                buildButtons.add(new Button((int) (120 * fw), (int) (325 * fh), (int) (58 * fw), (int) (42 * fh), 8, 87, ButtonType.ARMY_BUILDING));
                break;
            case CACHET:
                buildButtons.clear();
                buildButtons.add(new Button((int) (50 * fw), (int) (325 * fh), (int) (58 * fw), (int) (42 * fh), 8, 90, ButtonType.CACHET_SIMPLE));
                buildButtons.add(new Button((int) (120 * fw), (int) (325 * fh), (int) (58 * fw), (int) (42 * fh), 8, 93, ButtonType.CACHET_ADVANCED));
                buildButtons.add(new Button((int) (50 * fw), (int) (375 * fh), (int) (58 * fw), (int) (42 * fh), 8, 96, ButtonType.CAHCHET_MONUMENT));
                break;
            case MAPVIEW:
                buildButtons.clear();
                break;
            case HOUSE_LITTLE:
                EntityRender.setEntityRender(ObjectType.HOUSE);
                gm.getGameView().showEntity = true;
                break;
            case HOUSE_BIG:
                break;
            case AGRICULTURE_FARM: {
                ArrayList<Button> buttons = new ArrayList<>();
                buttons.add(new ComplexButton(0, 0, 460, 25, 1, 4, 5, 6, ButtonType.FARM_WHEAT, new TextInterface("Ferme à blé", FontLoader.getFont("Zeus.ttf").deriveFont(16f), 50, 18)));
                buttons.add(new ComplexButton(0, 30, 460, 25, 1, 4, 5, 6, ButtonType.FARM_CARROT, new TextInterface("Ferme à carotte", FontLoader.getFont("Zeus.ttf").deriveFont(16f), 50, 48)));
                buttons.add(new ComplexButton(0, 60, 460, 25, 1, 4, 5, 6, ButtonType.FARM_ONION, new TextInterface("Ferme à ognion", FontLoader.getFont("Zeus.ttf").deriveFont(16f), 50, 78)));
                gm.getFrame().getWindow().gameInterface = new Interface(1100, 500, 460, 200, buttons);
                break;
            }
            case AGRICULTURE_FISH: {
                ArrayList<Button> buttons = new ArrayList<>();
                buttons.add(new ComplexButton(0, 0, 460, 25, 1, 4, 5, 6, ButtonType.FISHING_FISHERY, new TextInterface("Pecherie", FontLoader.getFont("Zeus.ttf").deriveFont(16f), 50, 18)));
                buttons.add(new ComplexButton(0, 30, 460, 25, 1, 4, 5, 6, ButtonType.FISHING_HUNTING, new TextInterface("Maison de chasse", FontLoader.getFont("Zeus.ttf").deriveFont(16f), 50, 48)));
                gm.getFrame().getWindow().gameInterface = new Interface(1100, 500, 460, 200, buttons);
                break;
            }
            case AGRICULTURE_VITICULTURE: {
                ArrayList<Button> buttons = new ArrayList<>();
                buttons.add(new ComplexButton(0, 0, 460, 25, 1, 4, 5, 6, ButtonType.VITICULTURE_SMALLHOLDING, new TextInterface("Metairie", FontLoader.getFont("Zeus.ttf").deriveFont(16f), 50, 18)));
                buttons.add(new ComplexButton(0, 30, 460, 25, 1, 4, 5, 6, ButtonType.VITICULTURE_OLIVETREE, new TextInterface("Olivier", FontLoader.getFont("Zeus.ttf").deriveFont(16f), 50, 48)));
                buttons.add(new ComplexButton(0, 60, 460, 25, 1, 4, 5, 6, ButtonType.VITICULTURE_GRAPE, new TextInterface("Vigne", FontLoader.getFont("Zeus.ttf").deriveFont(16f), 50, 78)));
                gm.getFrame().getWindow().gameInterface = new Interface(1100, 500, 460, 200, buttons);
                break;
            }
            case AGRICULTURE_BREEDING: {
                ArrayList<Button> buttons = new ArrayList<>();
                buttons.add(new ComplexButton(0, 0, 460, 25, 1, 4, 5, 6, ButtonType.BREEDING_SHEEPHOLD, new TextInterface("Atelier de laine", FontLoader.getFont("Zeus.ttf").deriveFont(16f), 50, 18)));
                buttons.add(new ComplexButton(0, 30, 460, 25, 1, 4, 5, 6, ButtonType.BREEDING_SHEEP, new TextInterface("Mouton", FontLoader.getFont("Zeus.ttf").deriveFont(16f), 50, 48)));
                buttons.add(new ComplexButton(0, 60, 460, 25, 1, 4, 5, 6, ButtonType.BREEDING_DAIRY, new TextInterface("Fromagerie", FontLoader.getFont("Zeus.ttf").deriveFont(16f), 50, 78)));
                buttons.add(new ComplexButton(0, 90, 460, 25, 1, 4, 5, 6, ButtonType.BREEDING_GOAT, new TextInterface("Chevre", FontLoader.getFont("Zeus.ttf").deriveFont(16f), 50, 108)));
                gm.getFrame().getWindow().gameInterface = new Interface(1100, 500, 460, 200, buttons);
                break;
            }
            case INDUSTRY_1: {
                ArrayList<Button> buttons = new ArrayList<>();
                buttons.add(new ComplexButton(0, 0, 460, 25, 1, 4, 5, 6, ButtonType.INDUSTRY_FOUNDRY, new TextInterface("Fonderie", FontLoader.getFont("Zeus.ttf").deriveFont(16f), 50, 18)));
                buttons.add(new ComplexButton(0, 30, 460, 25, 1, 4, 5, 6, ButtonType.INDUSTRY_MARBLE, new TextInterface("Carrière de marbre", FontLoader.getFont("Zeus.ttf").deriveFont(16f), 50, 48)));
                buttons.add(new ComplexButton(0, 60, 460, 25, 1, 4, 5, 6, ButtonType.INDUSTRY_SAWMILL, new TextInterface("Scierie", FontLoader.getFont("Zeus.ttf").deriveFont(16f), 50, 78)));
                buttons.add(new ComplexButton(0, 90, 460, 25, 1, 4, 5, 6, ButtonType.INDUSTRY_MINT, new TextInterface("Atelier de monnayage", FontLoader.getFont("Zeus.ttf").deriveFont(16f), 50, 108)));
                gm.getFrame().getWindow().gameInterface = new Interface(1100, 500, 460, 200, buttons);
                break;
            }
            case INDUSTRY_2: {
                ArrayList<Button> buttons = new ArrayList<>();
                buttons.add(new ComplexButton(0, 0, 460, 25, 1, 4, 5, 6, ButtonType.INDUSTRY_OLIVE, new TextInterface("Pressoir à olive", FontLoader.getFont("Zeus.ttf").deriveFont(16f), 50, 18)));
                buttons.add(new ComplexButton(0, 30, 460, 25, 1, 4, 5, 6, ButtonType.INDUSTRY_WINERY, new TextInterface("Pressoir à vin", FontLoader.getFont("Zeus.ttf").deriveFont(16f), 50, 48)));
                buttons.add(new ComplexButton(0, 60, 460, 25, 1, 4, 5, 6, ButtonType.INDUSTRY_SCULPTURE, new TextInterface("Atelier de sculpture", FontLoader.getFont("Zeus.ttf").deriveFont(16f), 50, 78)));
                gm.getFrame().getWindow().gameInterface = new Interface(1100, 500, 460, 200, buttons);
                break;
            }
            case MARKET_GRANARY:
                EntityRender.setEntityRender(ObjectType.GRANARY);
                gm.getGameView().showEntity = true;
                break;
            case MARKET_STOCK:
                EntityRender.setEntityRender(ObjectType.STOCK);
                gm.getGameView().showEntity = true;
                break;
            case MARKET_AGORA: {
                ArrayList<Button> buttons = new ArrayList<>();
                buttons.add(new ComplexButton(0, 0, 460, 25, 1, 4, 5, 6, ButtonType.AGORA_AGORA, new TextInterface("Agora", FontLoader.getFont("Zeus.ttf").deriveFont(16f), 50, 18)));
                buttons.add(new ComplexButton(0, 30, 460, 25, 1, 4, 5, 6, ButtonType.AGORA_FOOD, new TextInterface("Epicerie", FontLoader.getFont("Zeus.ttf").deriveFont(16f), 50, 48)));
                buttons.add(new ComplexButton(0, 60, 460, 25, 1, 4, 5, 6, ButtonType.AGORA_WOOL, new TextInterface("Vendeur de laine", FontLoader.getFont("Zeus.ttf").deriveFont(16f), 50, 78)));
                buttons.add(new ComplexButton(0, 90, 460, 25, 1, 4, 5, 6, ButtonType.AGORA_OIL, new TextInterface("Vendeur d'huile d'olive", FontLoader.getFont("Zeus.ttf").deriveFont(16f), 50, 108)));
                gm.getFrame().getWindow().gameInterface = new Interface(1100, 500, 460, 200, buttons);
                break;
            }
            case MARKET_TRANS:
                break;
            case HEALTH_WATER:
                EntityRender.setEntityRender(ObjectType.WATERWELL);
                gm.getGameView().showEntity = true;
                break;
            case HEALTH_HOSPITAL:
                break;
            case HEALTH_SAFETY:
                EntityRender.setEntityRender(ObjectType.SAFETY);
                gm.getGameView().showEntity = true;
                break;
            case HEALTH_GUARD:
                break;
            case PALACE_PALACE:
                break;
            case PALACE_TAX:
                break;
            case PALACE_BRIDGE:
                break;
            case CULTURE_PHILOSOPHIA:
                break;
            case CULTURE_GYMNASIUM:
                break;
            case CULTURE_DRAMA:
                break;
            case CULTURE_STADIUM:
                break;
            case TEMPLE_TEMPLE:
                break;
            case TEMPLE_HEROE:
                break;
            case TEMPLE_CONSTRUCT:
                EntityRender.setEntityRender(ObjectType.GUILD);
                gm.getGameView().showEntity = true;
                break;
            case ARMY_FORT:
                break;
            case ARMY_BUILDING:
                break;
            case CACHET_SIMPLE:
                break;
            case CACHET_ADVANCED:
                break;
            case CAHCHET_MONUMENT:
                break;
            case QUICK_ROUTE:
                EntityRender.setEntityRender(ObjectType.ROAD);
                gm.getGameView().showEntity = true;
                break;
            case QUICK_STOP:
                EntityRender.setEntityRender(ObjectType.BLOCKABLE_ROAD);
                gm.getGameView().showEntity = true;
                break;
            case QUICK_ERASE:
                break;
            case QUICK_CANCEL:
                break;
            case QUICK_HISTORY:
                break;
            case QUICK_MISSION:
                break;
            case QUICK_VIEW:
                break;
            case QUICK_MAP:
                break;
        }
    }


}
