package com.exiro.sprite.delivery;

import com.exiro.ai.DeliveryAI;
import com.exiro.buildingList.Building;
import com.exiro.depacking.TileImage;
import com.exiro.object.Case;
import com.exiro.object.City;
import com.exiro.object.ObjectClass;
import com.exiro.sprite.DeliverySprite;
import com.exiro.sprite.Direction;

import java.util.ArrayList;
import java.util.Map;

public class SafetyGuard extends DeliverySprite {

    DeliveryAI ai;

    public SafetyGuard(City c, ObjectClass destination, Case start) {
        super("SprMain", 0, 608, 12, c, destination, start, 30);
        setOffsetY(-5);
        setTimeBetweenFrame(0.1);
    }

    @Override
    public void deliverBuildings() {

        for (Building b : getBuildingsToDeliver()) {
            b.setSafetyLvl(200);
        }
    }

    @Override
    public boolean build(int xPos, int yPos) {
        return false;
    }

    @Override
    public void delete() {

    }

    @Override
    public ArrayList<Case> getAccess() {
        return null;
    }

    @Override
    public Map<Direction, TileImage[]> getSpriteSet() {
        return null;
    }
}