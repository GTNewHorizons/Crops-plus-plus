package com.github.bartimaeusnek.cropspp.croploader;

import static gregtech.api.enums.Mods.BiomesOPlenty;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.item.ItemStack;

import com.github.bartimaeusnek.cropspp.crops.natura.SaguaroCrop;
import com.github.bartimaeusnek.cropspp.crops.natura.nether.BasicNetherShroomCrop;
import com.github.bartimaeusnek.cropspp.crops.natura.nether.BlightberryCrop;
import com.github.bartimaeusnek.cropspp.crops.natura.nether.DuskberryCrop;
import com.github.bartimaeusnek.cropspp.crops.natura.nether.SkyberryCrop;
import com.github.bartimaeusnek.cropspp.crops.natura.nether.StingberryCrop;
import com.github.bartimaeusnek.cropspp.crops.natura.nether.Thornvines;

import mods.natura.common.NContent;

public class NaturaLoader {

    public static List<CropLoader> load() {

        List<CropLoader> p = new ArrayList<CropLoader>();

        p.add(new CropLoader(new BlightberryCrop(), new ItemStack(NContent.netherBerryItem, 1, 0)));
        p.add(new CropLoader(new DuskberryCrop(), new ItemStack(NContent.netherBerryItem, 1, 1)));
        p.add(new CropLoader(new SkyberryCrop(), new ItemStack(NContent.netherBerryItem, 1, 2)));
        p.add(new CropLoader(new StingberryCrop(), new ItemStack(NContent.netherBerryItem, 1, 3)));
        p.add(new CropLoader(new Thornvines(), new ItemStack(NContent.thornVines, 1, 0)));
        p.add(new CropLoader(new BasicNetherShroomCrop("Blue"), new ItemStack(NContent.glowshroom, 1, 2)));
        p.add(new CropLoader(new BasicNetherShroomCrop("Green"), new ItemStack(NContent.glowshroom, 1, 0)));
        p.add(new CropLoader(new BasicNetherShroomCrop("Purple"), new ItemStack(NContent.glowshroom, 1, 1)));
        p.add(new CropLoader(new SaguaroCrop(), new ItemStack(NContent.seedFood, 1, 0)));

        if (BiomesOPlenty.isModLoaded()) {
            p.add(
                    new CropLoader(
                            new BasicNetherShroomCrop("Yellow"),
                            new ItemStack(biomesoplenty.api.content.BOPCBlocks.mushrooms, 1, 3)));
        }

        return p;
    }
}
