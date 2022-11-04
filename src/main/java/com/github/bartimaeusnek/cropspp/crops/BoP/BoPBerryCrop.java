package com.github.bartimaeusnek.cropspp.crops.BoP;

import com.github.bartimaeusnek.cropspp.ConfigValues;
import com.github.bartimaeusnek.cropspp.abstracts.BasicBerryCrop;
import ic2.api.crops.ICropTile;
import net.minecraft.item.ItemStack;

public class BoPBerryCrop extends BasicBerryCrop {

    public BoPBerryCrop() {
        super();
    }

    @Override
    public String name() {
        return "Berry";
    }

    @Override
    public int tier() {
        return 2;
    }

    @Override
    public int stat(int n) {
        switch (n) {
            case 0:
                return 0; // not chemical
            case 1:
                return 4; // very edible
            case 2:
                return 0; // no defensive properties
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
        return crop.getSize() < 3;
    }

    @Override
    public boolean canBeHarvested(ICropTile crop) {
        return crop.getSize() == 3;
    }

    @Override
    public int growthDuration(ICropTile crop) {
        if (ConfigValues.debug) return 1;
        return crop.getSize() == 2 ? 200 : 700;
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
    public ItemStack getGain(ICropTile crop) {
        return new ItemStack(biomesoplenty.api.content.BOPCItems.food, 3, 0);
    }

    @Override
    public String[] attributes() {
        return new String[] {"Berry", "Food", "Red", "Ingredient"};
    }

    @Override
    public String discoveredBy() {
        return "bartimaeusnek";
    }

    @Override
    public ItemStack getDisplayItem() {
        return new ItemStack(biomesoplenty.api.content.BOPCItems.food, 3, 0);
    }
}
