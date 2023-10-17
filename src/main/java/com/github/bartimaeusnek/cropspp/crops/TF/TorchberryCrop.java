package com.github.bartimaeusnek.cropspp.crops.TF;

import net.minecraft.item.ItemStack;

import com.github.bartimaeusnek.cropspp.abstracts.BasicCrop;

import ic2.api.crops.ICropTile;
import twilightforest.item.TFItems;

public class TorchberryCrop extends BasicCrop {

    public TorchberryCrop() {
        super();
    }

    @Override
    public int tier() {
        return 2;
    }

    @Override
    public String name() {
        return "Torchberry";
    }

    @Override
    public String discoveredBy() {
        return "Minepolz320";
    }

    @Override
    public String[] attributes() {
        return new String[] { "Berry", "Glow", "Shimmer" };
    }

    @Override
    public int stat(int n) {
        switch (n) {
            case 0:
                return 2; // chemical
            case 1:
                return 0; // not edible
            case 2:
                return 0; // has defensive properties
            case 3:
                return 2; // colorful
            case 4:
                return 1; // weed-like
            default:
                return 0;
        }
    }

    @Override
    public int getEmittedLight(ICropTile crop) {
        switch (crop.getSize()) {
            case 1:
                return 3;
            case 3:
                return 7;
            default:
                return 0;
        }
    }

    @Override
    public int maxSize() {
        return 3;
    }

    @Override
    public byte getSizeAfterHarvest(ICropTile crop) {
        return 2;
    }

    @Override
    public int growthDuration(ICropTile crop) {
        return crop.getSize() == 1 ? 100 : 150;
    }

    @Override
    public boolean canGrow(ICropTile crop) {
        // do the static check before the world check please
        return super.canGrow(crop) && crop.getLightLevel() <= 10;
    }

    @Override
    public ItemStack getGain(ICropTile crop) {
        return new ItemStack(TFItems.torchberries, 1);
    }

    @Override
    public ItemStack getDisplayItem() {
        return new ItemStack(TFItems.torchberries, 1);
    }

}
