package com.github.bartimaeusnek.cropspp.croploader;

import com.github.bartimaeusnek.cropspp.crops.TC.CinderpearlCrop;
import com.github.bartimaeusnek.cropspp.crops.TC.MagicMetalBerryCrop;
import com.github.bartimaeusnek.cropspp.crops.TC.PrimordialPearlBerryCrop;
import com.github.bartimaeusnek.cropspp.crops.TC.ShimmerleafCrop;
import java.util.ArrayList;
import java.util.List;

public class ThaumcraftLoader {
    public static List<CropLoader> load() {
        List<CropLoader> p = new ArrayList<CropLoader>();
        p.add(new CropLoader(new PrimordialPearlBerryCrop(), thaumcraft.api.ItemApi.getItem("itemEldritchObject", 3)));
        p.add(new CropLoader(new MagicMetalBerryCrop(), thaumcraft.api.ItemApi.getItem("itemResource", 17)));
        p.add(new CropLoader(new ShimmerleafCrop(), null));
        p.add(new CropLoader(new CinderpearlCrop(), null));
        return p;
    }
}
