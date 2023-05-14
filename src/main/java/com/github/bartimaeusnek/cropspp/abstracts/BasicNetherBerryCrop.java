package com.github.bartimaeusnek.cropspp.abstracts;

import java.util.Arrays;
import java.util.List;

import com.github.bartimaeusnek.cropspp.ConfigValues;

import ic2.api.crops.ICropTile;

public abstract class BasicNetherBerryCrop extends BasicBerryCrop {

    public BasicNetherBerryCrop() {
        super();
    }

    @Override
    public int tier() {
        return 4;
    }

    @Override
    public int stat(int n) {
        switch (n) {
            case 0:
                return 1; // a bit chemical
            case 1:
                return 3; // kinda edible
            case 2:
                return 4; // strong defensive properties
            case 3:
                return 4; // quite colorful
            case 4:
                return 0; // not particularly weed-like
            default:
                return 0;
        }
    }

    @Override
    public boolean canGrow(ICropTile crop) {
        if (crop.getSize() < 1) return true;
        else return crop.getSize() < 3;
    }

    @Override
    public List<String> getCropInformation() {
        return Arrays
                .asList("Has increased Nutrient requirements (x1.5)", "Has decreased humidity requirements (x0.5)");
    }

    @Override
    public int weightInfluences(ICropTile crop, float humidity, float nutrients, float air) {
        // Requires no humidity but nutrients.
        return (int) ((double) humidity * 0.5 + (double) nutrients * 1.5 + (double) air * 1);
    }

    @Override
    public int growthDuration(ICropTile crop) {
        if (ConfigValues.debug) return 1;
        return crop.getSize() == 2 ? 300 : 700;
    }
}
