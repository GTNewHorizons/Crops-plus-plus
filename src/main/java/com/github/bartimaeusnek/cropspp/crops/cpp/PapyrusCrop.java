package com.github.bartimaeusnek.cropspp.crops.cpp;

import com.github.bartimaeusnek.cropspp.abstracts.BasicDecorationCrop;
import ic2.api.crops.ICropTile;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

public class PapyrusCrop extends BasicDecorationCrop {

    public PapyrusCrop() {
        super();
    }

    @Override
    public int tier() {
        return 5;
    }

    @Override
    public ItemStack getDisplayItem() {
        return new ItemStack(Items.paper, 1, 0);
    }

    @Override
    public String name() {
        return "Papyrus";
    }

    @Override
    public String[] attributes() {
        return new String[] {"White", "Paper"};
    }

    @Override
    public ItemStack getGain(ICropTile crop) {
        return new ItemStack(Items.paper, 1, 0);
    }
}
