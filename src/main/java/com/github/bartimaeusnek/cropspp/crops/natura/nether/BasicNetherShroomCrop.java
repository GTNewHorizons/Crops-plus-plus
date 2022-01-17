package com.github.bartimaeusnek.cropspp.crops.natura.nether;

import biomesoplenty.api.content.BOPCBlocks;
import com.github.bartimaeusnek.cropspp.ConfigValues;
import com.github.bartimaeusnek.cropspp.abstracts.BasicNetherBerryCrop;
import ic2.api.crops.ICropTile;
import mods.natura.common.NContent;
import net.minecraft.item.ItemStack;

public class BasicNetherShroomCrop extends BasicNetherBerryCrop {

    private String name;

    public BasicNetherShroomCrop(String color) {
        super();
        if (color.equals("Purple") || color.equals("Blue") || color.equals("Green"))
            this.name = color + " Glowshroom";
        else
            this.name = "Glowshroom";
    }

    @Override
    public String name() {
        return name;
    }

    @Override
    public int tier() {
        return 3;
    }

    @Override
    public int stat(int n) {
        switch (n) {
            case 0:
                return 1;   // a bit chemical
            case 1:
                return 3;   // kinda edible
            case 2:
                return 0;   // no defensive properties
            case 3:
                return 4;   // quite colorful
            case 4:
                return 0;   // not particularly weed-like
            default:
                return 0;
        }
    }

    @Override
    public int growthDuration(ICropTile crop) {
        return ConfigValues.debug ? 1 : 600;
    }

    @Override
    public boolean canBeHarvested(ICropTile crop) {
        return crop.getSize() == 2;
    }

    public boolean canGrow(ICropTile crop) {
        return crop.getSize() < 2;
    }

    @Override
    public int maxSize() {
        return 2;
    }


    @Override
    public String[] attributes() {
        String[] r;
        r = new String[]{"Food", "Mushroom", "Ingredient", "Nether"};
        return r;
    }

    @Override
    public ItemStack getGain(ICropTile crop) {
        ItemStack r;
        if (name.contains("Blue"))
            r = new ItemStack(NContent.glowshroom, 1, 2);
        else if (name.contains("Green"))
            r = new ItemStack(NContent.glowshroom, 1, 0);
        else if (name.contains("Purple"))
            r = new ItemStack(NContent.glowshroom, 1, 1);
        else r = new ItemStack(BOPCBlocks.mushrooms, 1, 3);
        return r;
    }

    @Override
    public byte getSizeAfterHarvest(ICropTile crop) {
        return 1;
    }

    @Override
    public ItemStack getDisplayItem() {
        ItemStack r;
        if (name.contains("Blue"))
            r = new ItemStack(NContent.glowshroom, 1, 2);
        else if (name.contains("Green"))
            r = new ItemStack(NContent.glowshroom, 1, 0);
        else if (name.contains("Purple"))
            r = new ItemStack(NContent.glowshroom, 1, 1);
        else r = new ItemStack(BOPCBlocks.mushrooms, 1, 3);
        return r;
    }
}
