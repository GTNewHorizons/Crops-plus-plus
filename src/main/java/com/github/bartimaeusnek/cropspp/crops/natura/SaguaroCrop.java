package com.github.bartimaeusnek.cropspp.crops.natura;

import static gregtech.api.enums.Mods.Natura;

import net.minecraft.item.ItemStack;

import com.github.bartimaeusnek.cropspp.ConfigValues;
import com.github.bartimaeusnek.cropspp.crops.cpp.CactiCrop;
import com.github.bartimaeusnek.cropspp.items.CppItems;

import ic2.api.crops.ICropTile;
import mods.natura.common.NContent;

public class SaguaroCrop extends CactiCrop {

    public SaguaroCrop() {
        super();
    }

    @Override
    public int tier() {
        return 4;
    }

    @Override
    public String name() {
        return "Saguaro Cactus";
    }

    @Override
    public String[] attributes() {
        return new String[] { "Green", "Food", "Cactus" };
    }

    @Override
    public byte getSizeAfterHarvest(ICropTile crop) {
        if (crop.getSize() >= this.maxSize() - 1) return 1;
        else return 2;
    }

    @Override
    public boolean canBeHarvested(ICropTile crop) {
        return crop.getSize() >= this.maxSize() - 1;
    }

    @Override
    public int growthDuration(ICropTile crop) {
        // used to have a 450 growth time for stage > 2
        // but that was impossible to reach since the crop reaches maturity at 3
        return ConfigValues.debug ? 1 : 225;
    }

    @Override
    public ItemStack getGain(ICropTile crop) {
        if (Natura.isModLoaded()) {
            if (crop.getSize() == this.maxSize() - 1) return new ItemStack(NContent.saguaro, 2, 0);
            else return new ItemStack(NContent.seedFood, 3, 0);
        } else {
            return new ItemStack(CppItems.BerryItems, 1, 4);
        }
    }

    @Override
    public ItemStack getDisplayItem() {
        if (Natura.isModLoaded()) return new ItemStack(NContent.seedFood, 3, 0);
        else return new ItemStack(CppItems.BerryItems, 1, 4);
    }
}
