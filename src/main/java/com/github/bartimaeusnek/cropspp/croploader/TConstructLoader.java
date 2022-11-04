package com.github.bartimaeusnek.cropspp.croploader;

import com.github.bartimaeusnek.cropspp.crops.TConstruct.*;
import com.github.bartimaeusnek.cropspp.crops.cpp.ArditeBerryCrop;
import com.github.bartimaeusnek.cropspp.crops.cpp.CobaltBerryCrop;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.item.ItemStack;

public class TConstructLoader {

    public static List<CropLoader> load() {
        List<CropLoader> p = new ArrayList<CropLoader>();
        p.add(new CropLoader(new IronOreBerryCrop(), new ItemStack(tconstruct.world.TinkerWorld.oreBerries, 1, 0)));
        p.add(new CropLoader(new GoldOreBerryCrop(), new ItemStack(tconstruct.world.TinkerWorld.oreBerries, 1, 1)));
        p.add(new CropLoader(new CopperOreBerryCrop(), new ItemStack(tconstruct.world.TinkerWorld.oreBerries, 1, 2)));
        p.add(new CropLoader(new TinOreBerryCrop(), new ItemStack(tconstruct.world.TinkerWorld.oreBerries, 1, 3)));
        p.add(new CropLoader(
                new AluminiumOreBerryCrop(), new ItemStack(tconstruct.world.TinkerWorld.oreBerries, 1, 4)));
        p.add(new CropLoader(new EssenceOreBerryCrop(), new ItemStack(tconstruct.world.TinkerWorld.oreBerries, 1, 5)));
        p.add(new CropLoader(new ArditeBerryCrop(), null));
        p.add(new CropLoader(new CobaltBerryCrop(), null));
        return p;
    }
}
