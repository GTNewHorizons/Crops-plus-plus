package com.github.bartimaeusnek.cropspp.crops.TC;

import com.github.bartimaeusnek.croploadcore.BlockGetterTC;
import com.github.bartimaeusnek.cropspp.ConfigValues;
import com.github.bartimaeusnek.cropspp.abstracts.BasicThaumcraftCrop;
import ic2.api.crops.ICropTile;
import net.minecraft.item.ItemStack;

public class BasicManaBeanCrop extends BasicThaumcraftCrop {

    private String color;

    public BasicManaBeanCrop(String color) {
        super();
        this.color = color;
    }

    @Override
    public int stat(int n) {
        switch (n) {
            case 0:
                return 0; // a bit chemical
            case 1:
                return 4; // edible
            case 2:
                return 0; // strong defensive properties
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
        if (crop.getSize() > 1) {
            return crop.isBlockBelow(BlockGetterTC.getBlock_asBlock("blockCrystal", 6));
        } else return true;
    }

    @Override
    public int weightInfluences(ICropTile crop, float humidity, float nutrients, float air) {
        // Requires no humidity but nutrients.
        return (int) ((double) humidity * 1.3 + (double) nutrients * 1 + (double) air * 0.7);
    }

    @Override
    public int growthDuration(ICropTile crop) {
        if (ConfigValues.debug) return 1;
        return crop.getSize() == 2 ? 1200 : 800;
    }

    @Override
    public String[] attributes() {
        return new String[] {"Berry", "Bean", "Magic", "Colorful", color};
    }

    @Override
    public String name() {
        return null;
    }

    @Override
    public ItemStack getGain(ICropTile crop) {
        return this.getDisplayItem();
    }

    @Override
    public ItemStack getDisplayItem() {
        return null;
    }
}
