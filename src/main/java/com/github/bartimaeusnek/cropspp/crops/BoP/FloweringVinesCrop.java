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
    public int maxSize() {
        return 4;
    }

    @Override
    public boolean canBeHarvested(ICropTile crop) {
        return crop.getSize() >= this.maxSize() - 1;
    }

    @Override
    public ItemStack getGain(ICropTile crop) {
        // id 106 = vines
        if (crop.getSize() >= this.maxSize()) return new ItemStack(BOPCBlocks.flowerVine, 2, 0);
        // there was a null return if the max side was under maxSize - 1
        // in normal operation that doesn't happen, so I removed it
        return new ItemStack(Item.getItemById(106), 2, 0);
    }

    @Override
    public ItemStack getDisplayItem() {
        return new ItemStack(BOPCBlocks.flowerVine, 2, 0);
    }
}
