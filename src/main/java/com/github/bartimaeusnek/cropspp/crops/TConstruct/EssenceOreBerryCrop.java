package com.github.bartimaeusnek.cropspp.crops.TConstruct;

import net.minecraft.item.ItemStack;

import tconstruct.world.TinkerWorld;

import com.github.bartimaeusnek.cropspp.ConfigValues;
import com.github.bartimaeusnek.cropspp.abstracts.BasicTinkerBerryCrop;
import ic2.api.crops.ICropTile;

public class EssenceOreBerryCrop extends BasicTinkerBerryCrop {

    public EssenceOreBerryCrop() {
        super();
    }

    @Override
    public String name() {
        return "Essence Berry";
    }

    @Override
    public ItemStack getGain(ICropTile crop) {

        if (crop.getSize() == 4 && crop.isBlockBelow("itemSkull")) {
            return new ItemStack(TinkerWorld.oreBerries, 6, 5);
        } else return new ItemStack(TinkerWorld.oreBerries, 2, 5);
    }

    @Override
    protected String hasBlock() {
        return "itemSkull";
    }

    @Override
    public int growthDuration(ICropTile crop) {
        if (ConfigValues.debug) return 1;
        return crop.getSize() >= 2 ? 3000 : 500;
    }

    @Override
    public boolean canGrow(ICropTile crop) {
        return crop.getSize() < 4;
    }

    @Override
    public String[] attributes() {
        return new String[] { "OreBerry", "Essence", "Undead" };
    }

    @Override
    public ItemStack getDisplayItem() {
        return new ItemStack(TinkerWorld.oreBerries, 6, 5);
    }
}
