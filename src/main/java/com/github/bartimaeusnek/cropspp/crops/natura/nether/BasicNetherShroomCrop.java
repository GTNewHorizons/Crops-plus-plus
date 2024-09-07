package com.github.bartimaeusnek.cropspp.crops.natura.nether;

import net.minecraft.item.ItemStack;

import com.github.bartimaeusnek.cropspp.ConfigValues;
import com.github.bartimaeusnek.cropspp.abstracts.BasicNetherBerryCrop;

import biomesoplenty.api.content.BOPCBlocks;
import ic2.api.crops.ICropTile;
import mods.natura.common.NContent;

public class BasicNetherShroomCrop extends BasicNetherBerryCrop {

    private enum ShroomType {
        Blue,
        Green,
        Purple,
        Other
    }

    private final String name;
    private final ShroomType shroomType;

    public BasicNetherShroomCrop(String color) {
        super();
        if (color.equals("Purple") || color.equals("Blue") || color.equals("Green")) this.name = color + " Glowshroom";
        else this.name = "Glowshroom";

        // just cache it, the old implementation was doing string.contains checks at runtime...
        if (color.contains("Blue")) this.shroomType = ShroomType.Blue;
        else if (color.contains("Green")) this.shroomType = ShroomType.Green;
        else if (color.contains("Purple")) this.shroomType = ShroomType.Purple;
        else this.shroomType = ShroomType.Other;
    }

    @Override
    public int tier() {
        return 3;
    }

    @Override
    public String name() {
        return name;
    }

    @Override
    public String[] attributes() {
        return new String[] { "Food", "Mushroom", "Ingredient", "Nether" };
    }

    @Override
    public int stat(int n) {
        return switch (n) {
            case 0 -> 1; // a bit chemical
            case 1 -> 3; // kinda edible
            case 2 -> 0; // no defensive properties
            case 3 -> 4; // quite colorful
            case 4 -> 0; // not particularly weed-like
            default -> 0;
        };
    }

    @Override
    public int maxSize() {
        return 2;
    }

    @Override
    public byte getSizeAfterHarvest(ICropTile crop) {
        return 1;
    }

    @Override
    public boolean canBeHarvested(ICropTile crop) {
        return crop.getSize() >= this.maxSize();
    }

    @Override
    public int growthDuration(ICropTile crop) {
        return ConfigValues.debug ? 1 : 600;
    }

    @Override
    public boolean canGrow(ICropTile crop) {
        return crop.getSize() < this.maxSize();
    }

    @Override
    public ItemStack getGain(ICropTile crop) {
        return switch (this.shroomType) {
            case Blue -> new ItemStack(NContent.glowshroom, 1, 2);
            case Green -> new ItemStack(NContent.glowshroom, 1, 0);
            case Purple -> new ItemStack(NContent.glowshroom, 1, 1);
            default -> new ItemStack(BOPCBlocks.mushrooms, 1, 3);
        };
    }

    @Override
    public ItemStack getDisplayItem() {
        return switch (this.shroomType) {
            case Blue -> new ItemStack(NContent.glowshroom, 1, 2);
            case Green -> new ItemStack(NContent.glowshroom, 1, 0);
            case Purple -> new ItemStack(NContent.glowshroom, 1, 1);
            default -> new ItemStack(BOPCBlocks.mushrooms, 1, 3);
        };
    }
}
