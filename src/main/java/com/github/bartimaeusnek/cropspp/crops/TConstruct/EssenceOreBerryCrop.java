package com.github.bartimaeusnek.cropspp.crops.TConstruct;

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
    public boolean canGrow(ICropTile crop) {
        return super.canGrowBase(crop);
    }

    @Override
    public ItemStack getDropItem(int amount) {
        return new ItemStack(TinkerWorld.oreBerries, amount, 5);
    }

}
