package com.github.bartimaeusnek.cropspp.crops.cpp;

import net.minecraft.item.ItemStack;

import com.github.bartimaeusnek.cropspp.abstracts.BasicFoodCrop;
import com.github.bartimaeusnek.cropspp.items.CppItems;

import ic2.api.crops.ICropTile;

public class SugarBeetCrop extends BasicFoodCrop {

    public SugarBeetCrop() {
        super();
    }

    @Override
    public int tier() {
        return 4;
    }

    @Override
    public String name() {
        return "Sugar Beet";
    }

    @Override
    public String[] attributes() {
        return new String[] { "Food", "White", "Ingredient" };
    }

    @Override
    public ItemStack getGain(ICropTile var1) {
        return new ItemStack(CppItems.CppBerries, 1, 1);
    }

    @Override
    public ItemStack getDisplayItem() {
        return new ItemStack(CppItems.CppBerries, 1, 1);
    }
}
