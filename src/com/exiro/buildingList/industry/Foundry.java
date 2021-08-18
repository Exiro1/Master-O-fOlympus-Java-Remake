package com.exiro.buildingList.industry;

import com.exiro.buildingList.BuildingCategory;
import com.exiro.buildingList.IndustryHarverster;
import com.exiro.moveRelated.FreeState;
import com.exiro.object.Case;
import com.exiro.object.ObjectType;
import com.exiro.object.Resource;
import com.exiro.sprite.BuildingSprite;
import com.exiro.sprite.Harvester;
import com.exiro.sprite.complexCarter.ComplexCarter;
import com.exiro.sprite.industry.BronzeHarvester;
import com.exiro.systemCore.GameManager;
import com.exiro.terrainList.Rock;

public class Foundry extends IndustryHarverster {

    ComplexCarter cc;
    int harvester = 0;

    public Foundry() {
        super(false, ObjectType.FOUNDRY, BuildingCategory.INDUSTRY, 0, 15, 105, 5, 0, 0, 2, 2, null, false, GameManager.currentCity, 0, Resource.BRONZE, 8, 3, 25, 100);
    }

    @Override
    public boolean build(int xPos, int yPos) {
        boolean succ = super.build(xPos, yPos);
        if (succ) {
            BuildingSprite s = new BuildingSprite(getType().getPath(), getType().getBitmapID(), 36, 12, getCity(), this);
            s.setOffsetX(29);
            s.setOffsetY(-11);
            s.setTimeBetweenFrame(0.1f);
            addSprite(s);


            return true;
        }
        return false;
    }

    @Override
    public void processSprite(double delta) {
        super.processSprite(delta);
    }

    @Override
    public void process(double deltaTime) {
        super.process(deltaTime);
        if (isWorking()) {

            if (harvester < harvesterNbr) {
                Case dir = null;
                Rock r = null;
                for (Case c : city.getMap().getCoppers()) {
                    if (!((Rock) c.getTerrain()).isMined() && ((Rock) c.getTerrain()).isAccessible()) {
                        for (Case n : c.getNeighbour()) {
                            if (city.getPathManager().getPathTo(getAccess().get(0), n, FreeState.WALKABLE.getI()) != null) {
                                dir = n;
                                r = ((Rock) c.getTerrain());
                                ((Rock) c.getTerrain()).setMined(true);
                                break;
                            }
                        }
                        if (dir != null)
                            break;
                    }
                }
                if (dir != null) {
                    BronzeHarvester bh = new BronzeHarvester(city, dir, timeToHarvest, this, r);
                    addSprite(bh);
                    harvester++;
                }
            }
        }
    }

    @Override
    public void harvestFinished(Harvester har) {
        super.harvestFinished(har);
        harvester--;
    }

    @Override
    protected void addPopulation() {

    }
}
