package com.exiro.buildingList.industry;

import com.exiro.buildingList.BuildingCategory;
import com.exiro.buildingList.IndustryConverter;
import com.exiro.buildingList.IndustryHarverster;
import com.exiro.constructionList.Tree;
import com.exiro.moveRelated.FreeState;
import com.exiro.object.Case;
import com.exiro.object.ObjectType;
import com.exiro.object.Resource;
import com.exiro.sprite.BuildingSprite;
import com.exiro.sprite.Harvester;
import com.exiro.sprite.industry.LumberJack;
import com.exiro.systemCore.GameManager;

import java.util.ArrayList;
import java.util.Comparator;

public class Sawmill extends IndustryHarverster {




    public Sawmill() {
        super(false, ObjectType.SAWMILL, BuildingCategory.INDUSTRY, 0, 12, 60, 5, 0, 0, 2, 2, null, false, GameManager.currentCity, 0, Resource.WOOD, 22, 3, 25, 100);
        maxPerCarter = 1;
    }

    public void createBuildingSpriteWork() {
        BuildingSprite s = new BuildingSprite(getType().getPath(), getType().getBitmapID(), 1, 10, getCity(), this);
        s.setOffsetX(16);
        s.setOffsetY(-12);
        s.setTimeBetweenFrame(0.1f);
        setSprite(0,s);
    }

    @Override
    public BuildingSprite createBuildingSpriteWait() {
        BuildingSprite s = super.createBuildingSpriteWait();
        s.setOffsetX(17);
        s.setOffsetY(8);
        return s;
    }

    @Override
    public boolean build(int xPos, int yPos) {
        boolean succ = super.build(xPos, yPos);
        if (succ) {
            setState(IndustryConverter.ConversionState.WAITING_RESOURCES);
            return true;
        }
        return false;
    }


    public void initTree(){
        ArrayList<Case> temp = new ArrayList<>(city.getMap().getTrees());
        closeTrees = new ArrayList<>();
        temp.sort((o1, o2) -> {
            int dist1 = (int) (Math.pow(o1.getxPos()-getxPos(),2) + Math.pow(o1.getyPos()-getyPos(),2));
            int dist2 = (int) (Math.pow(o2.getxPos()-getxPos(),2) + Math.pow(o2.getyPos()-getyPos(),2));
            if(dist2==dist1)
                return 0;
            return dist1>dist2?1:-1;
        });

        int j = 0;
        while (closeTrees.size()<30 && j<temp.size()){
            Tree t = (Tree) temp.get(j).getObject();
            j++;
            if(t.isBeingcut() || t.isCut())
                continue;
            for (Case n : t.getMainCase().getNeighbour()) {
                if (city.getPathManager().getPathTo(getAccess().get(0), n, FreeState.NON_BLOCKING.getI()) != null) {
                    closeTrees.add(t.getMainCase());
                    break;
                }
            }
        }
    }

    @Override
    public void setActive(boolean active) {
        super.setActive(active);
        if(active){
            initTree();
        }
    }

    ArrayList<Case> closeTrees;

    @Override
    public void processSprite(double delta) {
        super.processSprite(delta);
    }

    public void goCut(){
        Case dir = null;
        Tree tree=null;
        if(closeTrees.size() > 0){
            closeTrees.removeIf(o->!(o.getObject() instanceof Tree));
            for(Case c : closeTrees){
                Tree t = (Tree) c.getObject();
                if (!t.isBeingcut() && !t.isCut()) {
                    for (Case n : t.getMainCase().getNeighbour()) {
                        if (city.getPathManager().getPathTo(getAccess().get(0), n, FreeState.NON_BLOCKING.getI()) != null) {
                            dir = n;
                            tree = t;
                            t.setBeingcut(true);
                            break;
                        }
                    }
                    if (dir != null)
                        break;
                }
            }

        }else{
            initTree();
        }
        if (dir != null) {
            LumberJack bh = new LumberJack(city, dir, timeToHarvest, this, tree);
            addSprite(bh);
            harvester++;
        }
    }



    @Override
    public void process(double deltaTime, int deltaDays) {
        super.process(deltaTime, deltaDays);
        if (isWorking()) {
            if (harvester < harvesterNbr) {
                goCut();
            }
        }
    }

    @Override
    public void harvestFinished(Harvester har) {
        unit += unitPerHarvester;
        if (unit >= this.unitNeeded) {
            resourceCreated(1);
            unit -= unitNeeded;
            if(unit==0){
                setState(IndustryConverter.ConversionState.WAITING_RESOURCES);
            }
        }
        if(getState() == IndustryConverter.ConversionState.WAITING_RESOURCES && unit > 0){
            setState(IndustryConverter.ConversionState.CONVERSION);
        }

        harvester--;
    }

    @Override
    protected void addPopulation() {

    }
}
