package com.github.bartimaeusnek.cropspp.croploader;

import static gregtech.api.enums.Mods.BiomesOPlenty;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.item.ItemStack;

import com.github.bartimaeusnek.cropspp.crops.TF.KnighmetalCrop;
import com.github.bartimaeusnek.cropspp.crops.TF.MossCrop;
import com.github.bartimaeusnek.cropspp.crops.TF.TorchberryCrop;

import twilightforest.item.TFItems;

public class TwilightForestLoader {

    public static List<CropLoader> load() {
        List<CropLoader> p = new ArrayList<CropLoader>();
        p.add(new CropLoader(new KnighmetalCrop(), null));
        p.add(new CropLoader(new TorchberryCrop(), new ItemStack(TFItems.torchberries, 1, 0)));

        if (BiomesOPlenty.isModLoaded()) {
            p.add(new CropLoader(new MossCrop(), null));
        }

        return p;
    }
}
