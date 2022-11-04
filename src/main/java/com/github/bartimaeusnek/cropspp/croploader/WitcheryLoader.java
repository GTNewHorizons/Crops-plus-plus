package com.github.bartimaeusnek.cropspp.croploader;

import com.github.bartimaeusnek.cropspp.crops.witchery.*;
import java.util.ArrayList;
import java.util.List;

public class WitcheryLoader {

    public static List<CropLoader> load() {
        List<CropLoader> p = new ArrayList<CropLoader>();

        p.add(new CropLoader(new GlintWeedCrop(), null));
        p.add(new CropLoader(new SpanishMossCrop(), null));
        p.add(new CropLoader(new BelladonnaCrop(), null));
        p.add(new CropLoader(new MandragoraCrop(), null));
        p.add(new CropLoader(new SnowbellCrop(), null));
        ;
        p.add(new CropLoader(new WolfsBaneCrop(), null));
        p.add(new CropLoader(new WaterArtichokeCrop(), null));
        p.add(new CropLoader(new EmberMossCrop(), null));

        return p;
    }
}
