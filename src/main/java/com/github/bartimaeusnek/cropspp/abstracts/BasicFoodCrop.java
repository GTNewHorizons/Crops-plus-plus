package com.github.bartimaeusnek.cropspp.abstracts;

import java.util.Arrays;
import java.util.List;

import ic2.api.crops.ICropTile;

public abstract class BasicFoodCrop extends BasicDecorationCrop {

    public BasicFoodCrop() {
        super();
    }

    @Override
    public int tier() {
        return 2;
    }

    @Override
    public int stat(int n) {
        switch (n) {
            case 0: {
                return 0;
            }
            case 1: {
                return 4;
            }
            case 2: {
                return 0;
            }
            case 3: {
                return 0;
            }
            case 4: {
                return 2;
            }
        }
        return 0;
    }

    @Override
    public String[] attributes() {
        return new String[] { "Food" };
    }

    @Override
    public int maxSize() {
        return 3;
    }

    @Override
    public boolean canGrow(ICropTile crop) {
        return crop.getSize() < this.maxSize() && crop.getLightLevel() >= 9;
    }

    @Override
    public List<String> getCropInformation() {
        return Arrays.asList("Needs a light Level of 9 or higher.");
    }

    @Override
    public boolean canBeHarvested(ICropTile crop) {
        return crop.getSize() >= this.maxSize();
    }
}
