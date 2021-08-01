package com.exiro.object;

import com.exiro.environment.Tree;
import com.exiro.sprite.Direction;
import com.exiro.terrainList.Elevation;
import com.exiro.terrainList.Empty;
import com.exiro.terrainList.Water;
import com.exiro.terrainList.WaterCoast;

import java.util.ArrayList;
import java.util.Random;

public class CityMap {

    private ArrayList<Case> cases;
    private int lenght;
    private int height, width;
    private Case startCase;
    private final City city;


    public CityMap(ArrayList<Case> cases, Case startCase, City city) {
        this.cases = cases;
        this.startCase = getCase(startCase.getxPos(), startCase.getyPos());
        this.city = city;
    }

    public CityMap(int height, int width, Case startCase, City city) {
        this.height = height;
        this.width = width;
        this.cases = new ArrayList<>();
        this.city = city;
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                Empty e = new Empty(1, city);
                e.setxPos(j);
                e.setyPos(i);
                cases.add(new Case(j, i, null, e));
                cases.get(cases.size() - 1).setMainCase(true);
                //TODO to remove
                /*
                cases.get(cases.size() - 1).setImg(e.getImg());
                cases.get(cases.size() - 1).setHeight(e.getHeight());
                cases.get(cases.size() - 1).setWidth(e.getWidth());
                cases.get(cases.size() - 1).setSize(e.getSize());
                */

            }
        }

        for (Case c : this.cases) {
            c.initNeighbour(this);
        }
        //getCase(startCase.getxPos(),startCase.getyPos()).setOccuped(true);
        //getCase(startCase.getxPos(),startCase.getyPos()).setBuildingType(BuildingType.ROAD);
        //getCase(startCase.getxPos(),startCase.getyPos()).setObject(new Road(city));
        // getCase(startCase.getxPos(),startCase.getyPos()).getObject().setActive(true);

        this.startCase = getCase(startCase.getxPos(), startCase.getyPos());
    }

    public void populateMap() {
        createSea(15, 15, 9, 7);
        createForest(30, 30, 4, 6);
        createElevation(5, 30, 5, 5, 1);
        createElevation(13, 30, 5, 5, 2);
    }


    public void createForest(int xs, int ys, int xlen, int ylen) {
        for (int i = xs; i < xs + xlen; i++) {
            for (int j = ys; j < ys + ylen; j++) {
                Case c = getCase(i, j);
                Random r = new Random();
                int nbr = r.nextInt(96);
                Tree tree = new Tree(i, j, nbr, city);
                c.setObject(tree);
            }
        }
    }

    public void createElevation(int xs, int ys, int xlen, int ylen, int size) {

        for (int i = xs; i < xs + xlen; i++) {
            for (int j = ys; j < ys + ylen; j++) {
                Case c = getCase(i, j);

                Random r = new Random();
                int nbr = 0;
                if (i == xs) {
                    Elevation w;
                    if (j == ys)
                        w = new Elevation(i, j, Direction.NORD, nbr, city, null, size);
                    else if (j == ys + ylen - 1)
                        w = new Elevation(i, j, Direction.OUEST, nbr, city, null, size);
                    else
                        w = new Elevation(i, j, Direction.NORD_OUEST, nbr, city, null, size);
                    c.setTerrain(w);
                } else if (i == xs + xlen - 1) {
                    Elevation w;

                    if (j == ys)
                        w = new Elevation(i, j, Direction.EST, nbr, city, null, size);
                    else if (j == ys + ylen - 1)
                        w = new Elevation(i, j, Direction.SUD, nbr, city, null, size);
                    else
                        w = new Elevation(i, j, Direction.SUD_EST, nbr, city, null, size);
                    c.setTerrain(w);
                } else if (j == ys) {
                    Elevation w;
                    if (i == xs + xlen - 1)
                        w = new Elevation(i, j, Direction.EST, nbr, city, null, size);
                    else
                        w = new Elevation(i, j, Direction.NORD_EST, nbr, city, null, size);
                    c.setTerrain(w);
                } else if (j == ys + ylen - 1) {
                    Elevation w = new Elevation(i, j, Direction.SUD_OUEST, nbr, city, null, size);
                    c.setTerrain(w);
                } else {
                    Empty e = new Empty(1, city);
                    e.setxPos(i);
                    e.setyPos(j);
                    c.setTerrain(e);
                }


            }
        }
    }


    public void createSea(int xs, int ys, int xlen, int ylen) {


        for (int i = xs; i < xs + xlen; i++) {
            for (int j = ys; j < ys + ylen; j++) {
                Case c = getCase(i, j);

                Random r = new Random();
                int nbr = r.nextInt(4);
                if (i == xs) {
                    WaterCoast w;
                    if (j == ys)
                        w = new WaterCoast(i, j, false, Direction.SUD, nbr, city);
                    else if (j == ys + ylen - 1)
                        w = new WaterCoast(i, j, false, Direction.EST, nbr, city);
                    else
                        w = new WaterCoast(i, j, false, Direction.SUD_EST, nbr, city);
                    c.setTerrain(w);
                } else if (i == xs + xlen - 1) {
                    WaterCoast w;

                    if (j == ys)
                        w = new WaterCoast(i, j, false, Direction.OUEST, nbr, city);
                    else if (j == ys + ylen - 1)
                        w = new WaterCoast(i, j, false, Direction.NORD, nbr, city);
                    else
                        w = new WaterCoast(i, j, false, Direction.NORD_OUEST, nbr, city);
                    c.setTerrain(w);
                } else if (j == ys) {
                    WaterCoast w;
                    if (i == xs + xlen - 1)
                        w = new WaterCoast(i, j, false, Direction.OUEST, nbr, city);
                    else
                        w = new WaterCoast(i, j, false, Direction.SUD_OUEST, nbr, city);
                    c.setTerrain(w);
                } else if (j == ys + ylen - 1) {
                    WaterCoast w = new WaterCoast(i, j, false, Direction.NORD_EST, nbr, city);
                    c.setTerrain(w);
                } else {
                    Water w = new Water(i, j, city);
                    c.setTerrain(w);
                }


            }
        }


    }


    public Case getCase(int x, int y) {
        if (x >= 0 && y >= 0 && x < width && y < height)
            return cases.get(width * y + x);

        return null;
    }


    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < lenght; i++) {
            for (int j = 0; j < lenght; j++) {
                str.append(" ").append(cases.get(j + i * lenght).getObject().getBuildingType().ordinal());
            }
            str.append("\n");
        }
        return str.toString();

    }


    public Case getStartCase() {
        return startCase;
    }

    public void setStartCase(Case startCase) {
        this.startCase = startCase;
    }

    public ArrayList<Case> getCases() {
        return cases;
    }

    public void setCases(ArrayList<Case> cases) {
        this.cases = cases;
    }

    public int getLenght() {
        return lenght;
    }

    public void setLenght(int lenght) {
        this.lenght = lenght;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }
}


