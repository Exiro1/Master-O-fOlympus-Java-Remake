package com.exiro.systemCore;

import com.exiro.buildingList.Building;
import com.exiro.constructionList.Construction;
import com.exiro.constructionList.Road;
import com.exiro.constructionList.Tree;
import com.exiro.moveRelated.FreeState;
import com.exiro.object.*;
import com.exiro.render.GameFrame;
import com.exiro.render.MouseManager;
import com.exiro.sprite.Sprite;
import com.exiro.terrainList.Rock;
import com.exiro.terrainList.Terrain;

public class GameThread implements Runnable {


    static public float deltaTime;
    final boolean continu = true;
    float fps = 144;
    final long deltaTimeResearched = (long) ((1f / 144f) * 1000f);
    double timeSinceLastUpdateBuilding = 0;
    double timeSinceLastUpdateConstruct = 0;
    double timeSinceLastUpdateResources = 0;
    private final Player p;
    private int currentCity;
    private final GameFrame frame;
    private final GameManager gm;

    public GameThread(GameManager gm) {
        this.p = gm.player;
        this.frame = gm.frame;
        this.gm = gm;
    }

    @Override
    public void run() {
        try {
            gameThread();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void gameThread() throws InterruptedException {
        System.out.println("d : " + deltaTimeResearched);
        while (continu) {
            long startTime = System.currentTimeMillis();
            timeSinceLastUpdateBuilding = timeSinceLastUpdateBuilding + deltaTime;
            timeSinceLastUpdateConstruct = timeSinceLastUpdateConstruct + deltaTime;
            timeSinceLastUpdateResources = timeSinceLastUpdateResources + deltaTime;

            gm.timeManager.updateTime(deltaTime);

            if (timeSinceLastUpdateBuilding > 1) {

                for (City c : p.getPlayerCities()) {
                    manageBuilding(c, timeSinceLastUpdateBuilding);
                }
                timeSinceLastUpdateBuilding = 0;
            }
            if (timeSinceLastUpdateConstruct > 3) {

                for (City c : p.getPlayerCities()) {
                    manageConstruction(c, timeSinceLastUpdateConstruct);
                }
                timeSinceLastUpdateConstruct = 0;
            }
            if (timeSinceLastUpdateResources > 5) {

                for (City c : p.getPlayerCities()) {
                    manageResources(c);
                }
                timeSinceLastUpdateResources = 0;
            }
            for (City c : p.getPlayerCities()) {
                manageSprite(c, deltaTime);
                manageTerrain(c, deltaTime);
            }

            try {
                manageBuild();
            } catch (NullPointerException e) {
                e.printStackTrace();
            }

            gm.GameView.repaint();
            gm.frame.getGi().repaint();
            gm.frame.getIt().repaint();
            float toWait = System.currentTimeMillis() - startTime;
            Thread.sleep(Math.max(deltaTimeResearched - (int) toWait, 0));

            float a = System.currentTimeMillis() - startTime;
            deltaTime = Math.min(a / 1000.0f, deltaTimeResearched / 1000.0f);

        }
    }


    public void manageResources(City c) {
        for (Case r : c.getMap().getSilvers()) {
            ((Rock) r.getTerrain()).setAccessible(false);
            for (Case n : r.getNeighbour()) {
                if (n.getTerrain().isBlocking())
                    continue;
                if (c.getPathManager().getPathTo(c.getMap().getStartCase(), n, FreeState.WALKABLE.getI()) != null) {
                    ((Rock) r.getTerrain()).setAccessible(true);
                    break;
                }
            }
        }
        for (Case r : c.getMap().getCoppers()) {
            ((Rock) r.getTerrain()).setAccessible(false);
            for (Case n : r.getNeighbour()) {
                if (n.getTerrain().isBlocking())
                    continue;
                if (c.getPathManager().getPathTo(c.getMap().getStartCase(), n, FreeState.WALKABLE.getI()) != null) {
                    ((Rock) r.getTerrain()).setAccessible(true);
                    break;
                }
            }
        }
        for (Case r : c.getMap().getTrees()) {
            ((Tree) r.getObject()).setAccessible(false);
            for (Case n : r.getNeighbour()) {
                if (n.getTerrain().isBlocking())
                    continue;
                if (c.getPathManager().getPathTo(c.getMap().getStartCase(), n, FreeState.WALKABLE.getI()) != null) {
                    ((Tree) r.getObject()).setAccessible(true);
                    break;
                }
            }
        }
    }


    public void manageBuild() {

        if (MouseManager.pressing) {

        } else {

        }

    }

    public void manageTerrain(City c, double deltaTime) {
        synchronized (c.getTerrain()) {
            for (Terrain b : c.getTerrain()) {
                b.process(deltaTime);
            }
        }
    }

    public void manageSprite(City c, double deltaTime) {
        synchronized (c.getBuildings()) {

            for (Building b : c.getBuildings()) {
                b.processSprite(deltaTime);
            }
        }
        synchronized (c.getAnimals()) {
            for (Sprite s : c.getAnimals()) {
                s.process(deltaTime);
            }
        }
    }

    public void manageConstruction(City c, double delaTime) {
        synchronized (c.getConstructions()) {
            for (Construction construction : c.getConstructions()) {
                construction.process(delaTime);
            }
        }
    }

    public void manageBuilding(City c, double delaTime) {

        synchronized (c.getBuildings()) {

            for (Building b : c.getBuildings()) {
                b.process(delaTime);
                if (b.isActive()) {
                    b.populate(delaTime);
                } else {

                }
            }
            c.deleteQueue();
        }
        synchronized (c.getPathManager().getRoads()) {
            for (Road r : c.getPathManager().getRoads()) {
                r.setActive(p.getPlayerCities().get(currentCity).getPathManager().isReachable(p.getPlayerCities().get(currentCity).getMap().getCase(r.getxPos(), r.getYpos()), p.getPlayerCities().get(currentCity).getMap().getStartCase(), FreeState.ALL_ROAD.i));
            }
        }

        synchronized (c.getObj()) {
            c.getObj().parallelStream().forEach(this::activate);
        }
        c.getBuildingManager().updateWorker();
    }

    public void activate(ObjectClass o) {
        if (o.getBuildingType() == ObjectType.ROAD || o.getAccess() == null)
            return;
        o.setActive(o.getAccess().size() != 0);
    }


}
