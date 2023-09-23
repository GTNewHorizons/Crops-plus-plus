package com.github.bartimaeusnek.cropspp.croploader;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.item.ItemStack;

import com.github.bartimaeusnek.cropspp.crops.BoP.Bamboo;
import com.github.bartimaeusnek.cropspp.crops.BoP.BoPBerryCrop;
import com.github.bartimaeusnek.cropspp.crops.BoP.EyebulbCrop;
import com.github.bartimaeusnek.cropspp.crops.BoP.FloweringVinesCrop;
import com.github.bartimaeusnek.cropspp.crops.BoP.GlowflowerCrop;
import com.github.bartimaeusnek.cropspp.crops.BoP.GlowingCoralCrop;
import com.github.bartimaeusnek.cropspp.crops.BoP.IvyCrop;
import com.github.bartimaeusnek.cropspp.crops.BoP.TurnipCrop;
import com.github.bartimaeusnek.cropspp.crops.BoP.WildCarrotsCrop;

import biomesoplenty.api.content.BOPCBlocks;

public class BoPLoader {

    public static List<CropLoader> BoPLoaderList() {

        List<CropLoader> p = new ArrayList<CropLoader>();
        p.add(new CropLoader(new BoPBerryCrop(), new ItemStack(biomesoplenty.api.content.BOPCItems.food, 1, 0)));
        p.add(
                new CropLoader(
                        new FloweringVinesCrop(),
                        new ItemStack(biomesoplenty.api.content.BOPCBlocks.flowerVine, 1, 0)));
        p.add(new CropLoader(new IvyCrop(), new ItemStack(biomesoplenty.api.content.BOPCBlocks.ivy, 1, 0)));
        p.add(new CropLoader(new EyebulbCrop(), new ItemStack(biomesoplenty.api.content.BOPCBlocks.flowers, 1, 13)));
        p.add(
                new CropLoader(
                        new GlowingCoralCrop(),
                        new ItemStack(biomesoplenty.api.content.BOPCBlocks.coral1, 1, 15)));
        p.add(new CropLoader(new GlowflowerCrop(), new ItemStack(biomesoplenty.api.content.BOPCBlocks.flowers, 1, 3)));
        p.add(new CropLoader(new TurnipCrop(), null));
        p.add(new CropLoader(new WildCarrotsCrop(), null));
        p.add(new CropLoader(new Bamboo(), new ItemStack(BOPCBlocks.saplings, 1, 2)));
        return p;
    }
}
