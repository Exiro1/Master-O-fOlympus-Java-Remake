package com.exiro.ConstructionList;

import com.exiro.BuildingList.BuildingType;
import com.exiro.Object.Case;
import com.exiro.Object.City;
import com.exiro.SystemCore.GameManager;

import java.util.ArrayList;

public class Empty extends Construction {


    public Empty(boolean isActive, BuildingType type, int width, int height, int size, ArrayList<Case> cases, int cost, int deleteCost, int xPos, int yPos, int xLenght, int yLenght, float cachet, City city, boolean built) {
        super(isActive, type, width, height, size, cases, cost, deleteCost, xPos, yPos, xLenght, yLenght, cachet, city, built);
    }

    public Empty(City city) {
        super(false, BuildingType.EMPTY, 1, 1, 1, new ArrayList<>(), 0, 0, 0, 0, 1, 1, 0f, city, false);

    }

    public Empty() {
        super(false, BuildingType.EMPTY, 1, 1, 1, new ArrayList<>(), 0, 0, 0, 0, 1, 1, 0f, GameManager.currentCity, false);

    }

    @Override
    public boolean build(int xPos, int yPos) {
        return true;
    }

}
