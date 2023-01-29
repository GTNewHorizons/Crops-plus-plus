package com.github.bartimaeusnek.cropspp.croploader;

import java.util.ArrayList;
import java.util.List;

import com.github.bartimaeusnek.cropspp.crops.gregtechCrops.GarnydniaCrop;
import com.github.bartimaeusnek.cropspp.crops.gregtechCrops.StonelillyCrop;

public class GTLoader {

    public static List<CropLoader> load() {
        List<CropLoader> p = new ArrayList<CropLoader>();
        p.add(new CropLoader(new StonelillyCrop("Red"), null));
        p.add(new CropLoader(new StonelillyCrop("Black"), null));
        p.add(new CropLoader(new StonelillyCrop("Gray"), null));
        p.add(new CropLoader(new StonelillyCrop("White"), null));
        p.add(new CropLoader(new StonelillyCrop("Yellow"), null));
        p.add(new CropLoader(new StonelillyCrop("Nether"), null));
        p.add(new CropLoader(new GarnydniaCrop(), null));
        return p;
    }
}
