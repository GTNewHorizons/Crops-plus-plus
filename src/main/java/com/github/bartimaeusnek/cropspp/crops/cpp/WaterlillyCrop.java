package com.github.bartimaeusnek.cropspp.crops.cpp;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import com.github.bartimaeusnek.croploadcore.MyRandom;
import com.github.bartimaeusnek.cropspp.ConfigValues;
import com.github.bartimaeusnek.cropspp.abstracts.BasicDecorationCrop;

import ic2.api.crops.ICropTile;

public class WaterlillyCrop extends BasicDecorationCrop {

    @Override
    public int tier() {
        return 2;
    }

    @Override
    public String name() {
        return "Waterlilly";
    }

    @Override
    public String discoveredBy() {
        return "moronwmachinegun";
    }

    @Override
    public int growthDuration(ICropTile crop) {
        if (ConfigValues.debug) return 1;
        return crop.isBlockBelow(Blocks.water) || crop.isBlockBelow(Blocks.flowing_water) ? 225 : 550;
    }

    @Override
    public String[] attributes() {
        return new String[] { "Blue", "Water", "Green" };
    }

    @Override
    public ItemStack getGain(ICropTile crop) {
        // pink dye or lily pad
        return MyRandom.intrandom(0, 10) > 8 ? new ItemStack(Items.dye, 2, 9) : getDisplayItem();
    }

    @Override
    public ItemStack getDisplayItem() {
        // lily pad
        return new ItemStack(Item.getItemById(111), 2);
    }
}
