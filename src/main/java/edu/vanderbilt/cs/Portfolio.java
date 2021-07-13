/*
 * Copyright (c) 2021. Austin J. Hunt.
 * All rights reserved.
 */

package edu.vanderbilt.cs;


import edu.vanderbilt.cs.position.Position;

import java.util.ArrayList;
import java.util.List;

/* Portfolio class represents a portfolio which is a collection of investments;
generally these investments can be stocks, bonds, cash, commodities, and other types
of investments.
 */
public class Portfolio {
    List<Position> positionList;
    public Portfolio(){
        this.positionList = new ArrayList<>();
    }

    public boolean addPosition(Position position){
        return this.positionList.add(position);
    }
    public boolean removePosition(Position position){
        return this.positionList.remove(position);
    }

}
