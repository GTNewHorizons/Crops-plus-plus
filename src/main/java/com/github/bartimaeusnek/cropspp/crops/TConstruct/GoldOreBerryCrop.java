package com.github.bartimaeusnek.cropspp.crops.TConstruct;

import net.minecraft.item.ItemStack;

import tconstruct.world.TinkerWorld;

import com.github.bartimaeusnek.cropspp.ConfigValues;
import com.github.bartimaeusnek.cropspp.abstracts.BasicTinkerBerryCrop;
import ic2.api.crops.ICropTile;

public class GoldOreBerryCrop extends BasicTinkerBerryCrop {

    public GoldOreBerryCrop() {
        super();
    }

    @Override
    public String name() {
        return "Gold " + BasicTinkerBerryCrop.OBname();
    }

    @Override
    public ItemStack getGain(ICropTile crop) {
        if (crop.getSize() == 4 && crop.isBlockBelow("blockGold")) {
            return new ItemStack(TinkerWorld.oreBerries, 6, 1);
        } else return new ItemStack(TinkerWorld.oreBerries, 2, 1);
    }

    @Override
    protected String hasBlock() {
        return "blockGold";
    }

    @Override
    public int growthDuration(ICropTile crop) {
        if (ConfigValues.debug) return 1;
        return crop.getSize() >= 2 ? 3000 : 500;
    }

    @Override
    public String[] attributes() {
        return new String[] { "OreBerry", "Gold", "Metal" };
    }

    @Override
    public ItemStack getDisplayItem() {
        return new ItemStack(TinkerWorld.oreBerries, 6, 1);
    }
}
