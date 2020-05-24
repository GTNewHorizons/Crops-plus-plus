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
        int r;
        // Same growth stages as melons and pumpkins
        if (ConfigValues.debug)
            r = 1;
        else if (crop.getSize() == 2) {
            // Ripens not so quickly
            r = 2200;
        } else {
            // Takes a while to grow from seed
            r = 1800;
        }
        return r;
    }

    @Override
    public int stat(int n) {
        switch (n) {
            case 0:
                return 2;   // Kinda Industrial Crop
            case 1:
                return 0;   // NOT Edible
            case 2:
                return 0;   // NO defensive properties
            case 3:
                return 2;   // Kinda colorful
            case 4:
                return 0;   // not particularly weed-like
            default:
                return 0;
        }
    }

    @Override
    public int maxSize() {
        return 3;
    }

    @Override
    public boolean canBeHarvested(ICropTile crop) {
        return crop.getSize() == 3;
    }

}
