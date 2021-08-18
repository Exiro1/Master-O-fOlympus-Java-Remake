package com.exiro.terrainList;

import com.exiro.object.Case;
import com.exiro.object.City;
import com.exiro.object.ObjectType;

import java.util.ArrayList;

public class Rock extends Terrain {

    RockType rtype;
    boolean mined = false;
    boolean isAccessible;

    public Rock(int xpos, int ypos, City c, int size, RockType rtype, int nbr) {
        super(true, ObjectType.ROCK, false, xpos, ypos, c, false, false, true);
        this.setSize(size);
        this.rtype = rtype;
        setRockImg(nbr);
    }

    public void setRockImg(int number) {
        int id = 0;
        switch (rtype) {
            case NORMAL:
                id = 333;
                break;
            case COPPER:
                id = 347;
                break;
            case SILVER:
                id = 362;
                break;
        }
        if (getSize() == 1) {
            if (number > 7)
                number = 7;
            id += number;
        } else if (getSize() == 2) {
            if (number > 3)
                number = 3;
            id += number + 8;
        } else {
            if (number > 1)
                number = 1;
            id += number + 12;
        }
        this.setLocalID(id);
        updateImg();
    }

    @Override
    public boolean build(int xPos, int yPos) {
        return false;
    }

    @Override
    public void delete() {

    }

    public boolean isAccessible() {
        return isAccessible;
    }

    public void setAccessible(boolean accessible) {
        isAccessible = accessible;
    }

    @Override
    public ArrayList<Case> getAccess() {
        return null;
    }

    @Override
    public void process(double deltaTime) {

    }

    public boolean isMined() {
        return mined;
    }

    public void setMined(boolean mined) {
        this.mined = mined;
    }

    public enum RockType {
        NORMAL, COPPER, SILVER;
    }
}
