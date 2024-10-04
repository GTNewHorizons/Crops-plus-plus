package com.github.bartimaeusnek.cropspp.crops.TConstruct;

import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;

import ic2.api.crops.ICropTile;
import tconstruct.world.TinkerWorld;

public class EssenceOreBerryCrop extends BasicTConstructOreBerryCrop {

    public EssenceOreBerryCrop() {
        super();
    }

    @Override
    public String name() {
        return "Essence Berry";
    }

    @Override
    public String[] attributes() {
        return new String[] { "OreBerry", "Essence", "Undead" };
    }

    @Override
    protected String hasBlock() {
        return "itemSkull";
    }

    @Override
    public boolean isBlockBelow(ICropTile crop) {
        // IC2 doesn't understand wildcards in its item comparison logic for block below checks.
        // So I'm using a workaround for vanilla skulls. EIO also adds a it's endermand head to the list and since that
        // doesn't use a wild card we can assume it that should keep working.
        return crop.isBlockBelow(Blocks.skull) || super.isBlockBelow(crop);
    }

    @Override
    public boolean canGrow(ICropTile crop) {
        return super.canGrowBase(crop);
    }

    @Override
    public ItemStack getDropItem(int amount) {
        return new ItemStack(TinkerWorld.oreBerries, amount, 5);
    }

}
