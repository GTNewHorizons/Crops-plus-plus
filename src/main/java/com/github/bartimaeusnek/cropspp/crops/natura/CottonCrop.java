package com.github.bartimaeusnek.cropspp.crops.natura;

import static gregtech.api.enums.Mods.Natura;

import net.minecraft.item.ItemStack;

import com.github.bartimaeusnek.croploadcore.OreDict;
import com.github.bartimaeusnek.cropspp.CCropUtility;
import com.github.bartimaeusnek.cropspp.abstracts.BasicDecorationCrop;

import ic2.api.crops.ICropTile;
import mods.natura.common.NContent;

public class CottonCrop extends BasicDecorationCrop {

    private static final String cropOreName = "cropCotton";

    public CottonCrop() {
        super();
        OreDict.BSget(cropOreName, this);
    }

    @Override
    public int tier() {
        return 3;
    }

    @Override
    public String name() {
        return "Cotton";
    }

    @Override
    public String[] attributes() {
        return new String[] { "White", "Cotton" };
    }

    @Override
    public int stat(int n) {
        return switch (n) {
            case 0, 1 -> 4;
            case 2, 3 -> 0;
            case 4 -> 2;
            default -> 0;
        };
    }

    @Override
    public int maxSize() {
        return 5;
    }

    @Override
    public boolean canGrow(ICropTile crop) {
        return super.canGrow(crop) && crop.getLightLevel() >= 9;
    }

    @Override
    public ItemStack getGain(ICropTile crop) {
        if (!Natura.isModLoaded()) {
            return CCropUtility.getCopiedOreStack(cropOreName);
        }
        return new ItemStack(NContent.plantItem, 1, 3);
    }

    @Override
    public ItemStack getDisplayItem() {
        return OreDict.ISget(cropOreName);
    }
}
