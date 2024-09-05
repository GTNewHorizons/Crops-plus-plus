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
        return switch (n) {
            case 0 -> 1; // a bit chemical
            case 1 -> 3; // kinda edible
            case 2 -> 4; // strong defensive properties
            case 3 -> 4; // quite colorful
            case 4 -> 0; // not particularly weed-like
            default -> 0;
        };
    }

    @Override
    public List<String> getCropInformation() {
        return Arrays
                .asList("Has increased Nutrient requirements (x1.5)", "Has decreased humidity requirements (x0.5)");
    }

    @Override
    public int weightInfluences(ICropTile crop, float humidity, float nutrients, float air) {
        // Requires no humidity but nutrients.
        return (int) ((double) humidity / 0.5D + (double) nutrients / 1.5D + (double) air);
    }

    @Override
    public int growthDuration(ICropTile crop) {
        if (ConfigValues.debug) return 1;
        // last stage is faster
        if (crop.getSize() >= this.maxSize() - 1) return 300;
        return 700;
    }
}
