package com.github.bartimaeusnek.cropspp.crops.TConstruct;

import net.minecraft.item.ItemStack;

import com.github.bartimaeusnek.cropspp.ConfigValues;
import com.github.bartimaeusnek.cropspp.abstracts.BasicTinkerBerryCrop;

import ic2.api.crops.ICropTile;
import tconstruct.world.TinkerWorld;

public class TinOreBerryCrop extends BasicTinkerBerryCrop {

    public TinOreBerryCrop() {
        super();
    }

    @Override
    public String name() {
        return "Tin " + BasicTinkerBerryCrop.OBname();
    }

    @Override
    public int tier() {
        return 4;
    }

    @Override
    public ItemStack getGain(ICropTile crop) {
        if (crop.getSize() == 4 && crop.isBlockBelow("blockTin")) {
            return new ItemStack(TinkerWorld.oreBerries, 6, 3);
        } else return new ItemStack(TinkerWorld.oreBerries, 2, 3);
    }

    @Override
    protected String hasBlock() {
        return "blockTin";
    }

    @Override
    public int growthDuration(ICropTile crop) {
        if (ConfigValues.debug) return 1;
        return crop.getSize() >= 2 ? 3000 : 500;
    }

    @Override
    public String[] attributes() {
        return new String[] { "OreBerry", "Tin", "Metal", "Shiny" };
    }

    @Override
    public ItemStack getDisplayItem() {
        return new ItemStack(TinkerWorld.oreBerries, 6, 3);
    }
}
