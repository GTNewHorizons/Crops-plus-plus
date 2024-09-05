package com.github.bartimaeusnek.cropspp.abstracts;

import com.github.bartimaeusnek.cropspp.ConfigValues;

import ic2.api.crops.ICropTile;

public abstract class BasicThaumcraftCrop extends BasicCrop {

    public BasicThaumcraftCrop() {
        super();
    }

    @Override
    public int tier() {
        return 5;
    }

    @Override
    public int growthDuration(ICropTile crop) {
        if (ConfigValues.debug) return 1;
        // last stage is longer
        if (crop.getSize() >= this.maxSize() - 1) return 2200;
        return 1800;
    }

    @Override
    public int stat(int n) {
        return switch (n) {
            case 0 -> 2; // Kinda Industrial Crop
            case 1 -> 0; // NOT Edible
            case 2 -> 0; // NO defensive properties
            case 3 -> 2; // Kinda colorful
            case 4 -> 0; // not particularly weed-like
            default -> 0;
        };
    }

    @Override
    public int maxSize() {
        return 3;
    }

    @Override
    public boolean canBeHarvested(ICropTile crop) {
        return crop.getSize() >= this.maxSize();
    }
}
