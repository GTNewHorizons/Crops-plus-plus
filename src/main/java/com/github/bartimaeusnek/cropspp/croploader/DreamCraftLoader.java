package com.github.bartimaeusnek.cropspp.croploader;

import static com.github.bartimaeusnek.cropspp.items.CppItems.ModifierMagic;

import com.github.bartimaeusnek.cropspp.crops.cpp.MagicModifierCrop;
import com.github.bartimaeusnek.cropspp.crops.cpp.SpacePlantCrop;
import java.util.ArrayList;
import java.util.List;

public class DreamCraftLoader {

    public static List<CropLoader> load() {
        List<CropLoader> p = new ArrayList<CropLoader>();
        p.add(new CropLoader(new SpacePlantCrop(), null));
        p.add(new CropLoader(new MagicModifierCrop(), ModifierMagic));
        return p;
    }
}
