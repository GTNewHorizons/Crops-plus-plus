package com.github.bartimaeusnek.cropspp.crops.cpp;

import com.github.bartimaeusnek.cropspp.abstracts.BasicDecorationCrop;
import ic2.api.crops.ICropTile;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class VineCrop extends BasicDecorationCrop {
    public VineCrop() {
        super();
    }

    @Override
    public int tier() {
        return 2;
    }

    @Override
    public String name() {
        return "Vines";
    }

    @Override
    public String discoveredBy() {
        return "jeb_";
    }

    @Override
    public String[] attributes() {
        return new String[]{"Green", "Tendrilly"};
    }

    @Override
    public ItemStack getGain(ICropTile crop) {
        return new ItemStack(Item.getItemById(106), 2, 0);
    }

    @Override
    public ItemStack getDisplayItem() {
        return new ItemStack(Item.getItemById(106), 2, 0);
    }

}