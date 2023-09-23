package com.github.bartimaeusnek.cropspp.croploader;

import java.util.ArrayList;
import java.util.List;

import com.github.bartimaeusnek.cropspp.crops.witchery.BelladonnaCrop;
import com.github.bartimaeusnek.cropspp.crops.witchery.EmberMossCrop;
import com.github.bartimaeusnek.cropspp.crops.witchery.GlintWeedCrop;
import com.github.bartimaeusnek.cropspp.crops.witchery.MandragoraCrop;
import com.github.bartimaeusnek.cropspp.crops.witchery.SnowbellCrop;
import com.github.bartimaeusnek.cropspp.crops.witchery.SpanishMossCrop;
import com.github.bartimaeusnek.cropspp.crops.witchery.WaterArtichokeCrop;
import com.github.bartimaeusnek.cropspp.crops.witchery.WolfsBaneCrop;

public class WitcheryLoader {

    public static List<CropLoader> load() {
        List<CropLoader> p = new ArrayList<CropLoader>();

        p.add(new CropLoader(new GlintWeedCrop(), null));
        p.add(new CropLoader(new SpanishMossCrop(), null));
        p.add(new CropLoader(new BelladonnaCrop(), null));
        p.add(new CropLoader(new MandragoraCrop(), null));
        p.add(new CropLoader(new SnowbellCrop(), null));;
        p.add(new CropLoader(new WolfsBaneCrop(), null));
        p.add(new CropLoader(new WaterArtichokeCrop(), null));
        p.add(new CropLoader(new EmberMossCrop(), null));

        return p;
    }
}
