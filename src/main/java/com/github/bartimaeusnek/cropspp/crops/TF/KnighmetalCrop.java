package com.github.bartimaeusnek.cropspp.crops.TF;

import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

import com.github.bartimaeusnek.cropspp.ConfigValues;
import com.github.bartimaeusnek.cropspp.abstracts.BasicTinkerBerryCrop;

import ic2.api.crops.ICropTile;

public class KnighmetalCrop extends BasicTinkerBerryCrop {

    public KnighmetalCrop() {
        super();
    }

    @Override
    public int tier() {
        return 8;
    }

    @Override
    public String name() {
        return "Knightly " + BasicTinkerBerryCrop.OBname();
    }

    @Override
    public String[] attributes() {
        return new String[] { "OreBerry", "Knightly", "Metal" };
    }

    @Override
    protected String hasBlock() {
        return "blockKnightmetal";
    }

    @Override
    public int growthDuration(ICropTile crop) {
        if (ConfigValues.debug) return 1;
        return crop.getSize() >= this.maxSize() - 1 ? 4500 : 1000;
    }

    @Override
    public ItemStack getGain(ICropTile crop) {
        if (crop.getSize() < this.maxSize()
                || !((crop.isBlockBelow(this.hasBlock()) || !OreDictionary.doesOreNameExist(this.hasBlock())))) {
            return new ItemStack(twilightforest.item.TFItems.armorShard, 1);
        }
        return new ItemStack(twilightforest.item.TFItems.armorShard, 4);
    }

    @Override
    public ItemStack getDisplayItem() {
        return new ItemStack(twilightforest.item.TFItems.armorShard);
    }
}
