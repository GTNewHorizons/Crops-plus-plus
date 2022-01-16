package com.github.bartimaeusnek.cropspp.abstracts;

import com.github.bartimaeusnek.cropspp.ConfigValues;
import ic2.api.crops.ICropTile;

import java.util.Arrays;
import java.util.List;

public abstract class BasicBerryCrop extends BasicCrop {


    public BasicBerryCrop() {
        super();
    }

    @Override
    public int tier() {
        return 2;
    }

    @Override
    public int stat(int n) {
        switch (n) {
            case 0:
                return 0;   // not chemical
            case 1:
                return 4;   // very edible
            case 2:
                return 0;   // no defensive properties
            case 3:
                return 4;   // quite colorful
            case 4:
                return 0;   // not particularly weed-like
            default:
                return 0;
        }
    }

    @Override
    public boolean canGrow(ICropTile crop) {
        return crop.getSize() < 3;
    }

    @Override
    public List<String> getCropInformation() {
        return Arrays.asList("Has increased Nutrient requirements (x1.2)", "Has decreased humidity and air requirements (x0.9)");
    }

    @Override
    public int weightInfluences(ICropTile crop, float humidity, float nutrients, float air) {
        // Requires more humidity than nutrients or air, but not much more
        return (int) ((double) humidity * 1.2 + (double) nutrients * 0.9 + (double) air * 0.9);
    }

    @Override
    public int growthDuration(ICropTile crop) {
        if (ConfigValues.debug) return 1;
        if (crop.getSize() == 2) return 200;
        return 700;
    }

    @Override
    public byte getSizeAfterHarvest(ICropTile crop) {
        // return to partially grown state when harvested
        return 2;
    }

    @Override
    public int maxSize() {
        return 3;
    }

    @Override
    public String discoveredBy() {
        return "bartimaeusnek";
    }

}
