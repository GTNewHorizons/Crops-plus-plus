package com.github.bartimaeusnek.cropspp.croploader;

import java.util.ArrayList;
import java.util.List;

import com.github.bartimaeusnek.cropspp.crops.TC.*;

public class ThaumcraftLoader {

    public static List<CropLoader> load() {
        List<CropLoader> p = new ArrayList<CropLoader>();
        p.add(new CropLoader(new PrimordialPearlBerryCrop(), thaumcraft.api.ItemApi.getItem("itemEldritchObject", 3)));
        p.add(new CropLoader(new MagicMetalBerryCrop(), thaumcraft.api.ItemApi.getItem("itemResource", 17)));
        p.add(new CropLoader(new ShimmerleafCrop(), null));
        p.add(new CropLoader(new CinderpearlCrop(), null));
        p.add(new CropLoader(new BasicManaBeanCrop(), null));
        return p;
    }
}
