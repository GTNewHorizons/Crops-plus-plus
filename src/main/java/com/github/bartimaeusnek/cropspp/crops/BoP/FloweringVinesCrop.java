package com.github.bartimaeusnek.cropspp.crops.BoP;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import com.github.bartimaeusnek.cropspp.crops.cpp.VineCrop;

import biomesoplenty.api.content.BOPCBlocks;
import ic2.api.crops.ICropTile;

public class FloweringVinesCrop extends VineCrop {

    public FloweringVinesCrop() {
        super();
    }

    @Override
    public int tier() {
        return 3;
    }

    @Override
    public boolean canGrow(ICropTile crop) {
        return crop.getSize() < 4;
    }

    @Override
    public int maxSize() {
        return 4;
    }

    @Override
    public String name() {
        return "Flowering Vines";
    }

    @Override
    public String discoveredBy() {
        return "bartimaeusnek";
    }

    @Override
    public String[] attributes() {
        return new String[] { "Green", "Tendrilly", "Flower" };
    }

    @Override
    public boolean canBeHarvested(ICropTile crop) {
        return crop.getSize() >= 3;
    }

    @Override
    public ItemStack getGain(ICropTile crop) {
        // id 106 = vines
        if (crop.getSize() == 3) return new ItemStack(Item.getItemById(106), 2, 0);
        else if (crop.getSize() == 4) return new ItemStack(BOPCBlocks.flowerVine, 2, 0);
        else return null;
    }

    @Override
    public ItemStack getDisplayItem() {
        return new ItemStack(BOPCBlocks.flowerVine, 2, 0);
    }
}
