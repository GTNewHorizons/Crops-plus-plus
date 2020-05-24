package com.github.bartimaeusnek.cropspp.abstracts;

import ic2.api.crops.ICropTile;

import java.util.Arrays;
import java.util.List;

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
        return new String[]{"Food"};
    }

    @Override
    public int maxSize() {
        return 3;
    }

    @Override
    public boolean canGrow(ICropTile crop) {
        return crop.getSize() < 3 && crop.getLightLevel() >= 9;
    }

    @Override
    public List<String> getCropInformation() {
        return Arrays.asList("Needs a light Level of 9 or higher.");
    }

    @Override
    public boolean canBeHarvested(ICropTile crop) {
        return crop.getSize() == 3;
    }

    @Override
    public byte getSizeAfterHarvest(ICropTile crop) {
        return 1;
    }

}
