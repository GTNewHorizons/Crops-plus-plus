package com.github.bartimaeusnek.cropspp.abstracts;

import com.github.bartimaeusnek.cropspp.ConfigValues;

import ic2.api.crops.ICropTile;

public abstract class BasicDecorationCrop extends BasicBerryCrop {

    public BasicDecorationCrop() {
        super();
    }

    @Override
    public int tier() {
        return 1;
    }

    @Override
    public int stat(int n) {
        return switch (n) {
            case 0 -> 0; // not chemical
            case 1 -> 0; // not edible
            case 2 -> 0; // no defensive properties
            case 3 -> 4; // primarily decorative
            case 4 -> 0; // not particularly weed-like
            default -> 0;
        };
    }

    @Override
    public int growthDuration(ICropTile crop) {
        return ConfigValues.debug ? 1 : 225;
    }

    @Override
    public byte getSizeAfterHarvest(ICropTile crop) {
        return 1;
    }
}
