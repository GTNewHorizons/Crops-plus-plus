package com.github.bartimaeusnek.cropspp.croploader;

import com.github.bartimaeusnek.croploadcore.ModsLoaded;
import com.github.bartimaeusnek.cropspp.crops.TF.KnighmetalCrop;
import com.github.bartimaeusnek.cropspp.crops.TF.MossCrop;
import com.github.bartimaeusnek.cropspp.crops.TF.TorchberryCrop;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.item.ItemStack;
import twilightforest.item.TFItems;

public class TwilightForestLoader {

    public static List<CropLoader> load() {
        List<CropLoader> p = new ArrayList<CropLoader>();
        p.add(new CropLoader(new KnighmetalCrop(), null));
        p.add(new CropLoader(new TorchberryCrop(), new ItemStack(TFItems.torchberries, 1, 0)));

        if (ModsLoaded.BoP) {
            p.add(new CropLoader(new MossCrop(), null));
        }

        return p;
    }
}
