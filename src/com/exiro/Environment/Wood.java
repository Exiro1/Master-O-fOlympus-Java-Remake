package com.exiro.Environment;

import com.exiro.BuildingList.BuildingType;
import com.exiro.Object.Case;
import com.exiro.Object.City;

import java.util.ArrayList;

public class Wood {

    private final int state;   //0 =normal , 1 = coupé
    private final float durability; //pour la coupe
    private final boolean isCutting;


    public Wood(City city, ArrayList<Case> cases, int xPos, int ypos, BuildingType type, float cachet, int state, float durability, boolean isCutting) {

        this.state = state;
        this.durability = durability;
        this.isCutting = isCutting;
    }
}
