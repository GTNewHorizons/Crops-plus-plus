package com.github.bartimaeusnek.cropspp;

import static gregtech.api.enums.MetaTileEntityIDs.cropGeneExtractorEV;
import static gregtech.api.enums.MetaTileEntityIDs.cropGeneExtractorHV;
import static gregtech.api.enums.MetaTileEntityIDs.cropGeneExtractorIV;
import static gregtech.api.enums.MetaTileEntityIDs.cropGeneExtractorLV;
import static gregtech.api.enums.MetaTileEntityIDs.cropGeneExtractorLuV;
import static gregtech.api.enums.MetaTileEntityIDs.cropGeneExtractorMV;
import static gregtech.api.enums.MetaTileEntityIDs.cropGeneExtractorUHV;
import static gregtech.api.enums.MetaTileEntityIDs.cropGeneExtractorUV;
import static gregtech.api.enums.MetaTileEntityIDs.cropGeneExtractorZPM;
import static gregtech.api.enums.MetaTileEntityIDs.cropReplicatorEV;
import static gregtech.api.enums.MetaTileEntityIDs.cropReplicatorHV;
import static gregtech.api.enums.MetaTileEntityIDs.cropReplicatorIV;
import static gregtech.api.enums.MetaTileEntityIDs.cropReplicatorLV;
import static gregtech.api.enums.MetaTileEntityIDs.cropReplicatorLuV;
import static gregtech.api.enums.MetaTileEntityIDs.cropReplicatorMV;
import static gregtech.api.enums.MetaTileEntityIDs.cropReplicatorUHV;
import static gregtech.api.enums.MetaTileEntityIDs.cropReplicatorUV;
import static gregtech.api.enums.MetaTileEntityIDs.cropReplicatorZPM;
import static gregtech.api.enums.MetaTileEntityIDs.cropSynthesiserEV;
import static gregtech.api.enums.MetaTileEntityIDs.cropSynthesiserHV;
import static gregtech.api.enums.MetaTileEntityIDs.cropSynthesiserIV;
import static gregtech.api.enums.MetaTileEntityIDs.cropSynthesiserLV;
import static gregtech.api.enums.MetaTileEntityIDs.cropSynthesiserLuV;
import static gregtech.api.enums.MetaTileEntityIDs.cropSynthesiserMV;
import static gregtech.api.enums.MetaTileEntityIDs.cropSynthesiserUHV;
import static gregtech.api.enums.MetaTileEntityIDs.cropSynthesiserUV;
import static gregtech.api.enums.MetaTileEntityIDs.cropSynthesiserZPM;

import com.github.bartimaeusnek.cropspp.fluids.CppFluids;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.github.bartimaeusnek.cropspp.GTHandler.CropItemList;
import com.github.bartimaeusnek.cropspp.GTHandler.GTHandler;
import com.github.bartimaeusnek.cropspp.GTHandler.machines.CropGeneExtractor;
import com.github.bartimaeusnek.cropspp.GTHandler.machines.CropReplicator;
import com.github.bartimaeusnek.cropspp.GTHandler.machines.CropSynthesiser;
import com.github.bartimaeusnek.cropspp.GTHandler.machines.CropWeedPicker;
import com.github.bartimaeusnek.cropspp.commands.EnableDebug;
import com.github.bartimaeusnek.cropspp.croploader.CropLoader;
import com.github.bartimaeusnek.cropspp.fluids.CppFluids;
import com.github.bartimaeusnek.cropspp.items.CppItems;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;

@Mod(
        modid = Cropspp.modID,
        name = Cropspp.name,
        version = Cropspp.version,
        dependencies = "required-after:IC2; " + "required-after:croploadcore@0.1.4; "
                + "after:GalacticraftCore; "
                + "after:Mantle; "
                + "after:Natura; "
                + "after:TConstruct; "
                + "after:BiomesOPlenty; "
                + "after:Thaumcraft; "
                + "after:ExtraTrees; "
                + "after:witchery; "
                + "after:gregtech; "
                + "after:TwilightForest; ")
public final class Cropspp {

    public static final String name = "Crops++";
    public static final String version = Tags.VERSION;
    public static final String modID = "berriespp";
    public static final Logger cpplogger = LogManager.getLogger(name);

    @Instance(modID)
    public static Cropspp instance;

    @EventHandler
    public void preInit(FMLPreInitializationEvent preinit) {
        CropLoader.load(preinit);
        CropItemList.cropGeneExtractorLV.set(
                new CropGeneExtractor(
                        cropGeneExtractorLV.ID,
                        "Basic Crop Gene Extractor",
                        "Basic Crop Gene Extractor",
                        1).getStackForm(1));
        CropItemList.cropGeneExtractorMV.set(
                new CropGeneExtractor(
                        cropGeneExtractorMV.ID,
                        "Advanced Crop Gene Extractor I",
                        "Advanced Crop Gene Extractor I",
                        2).getStackForm(1));
        CropItemList.cropGeneExtractorHV.set(
                new CropGeneExtractor(
                        cropGeneExtractorHV.ID,
                        "Advanced Crop Gene Extractor II",
                        "Advanced Crop Gene Extractor II",
                        3).getStackForm(1));
        CropItemList.cropGeneExtractorEV.set(
                new CropGeneExtractor(
                        cropGeneExtractorEV.ID,
                        "Advanced Crop Gene Extractor III",
                        "Advanced Crop Gene Extractor III",
                        4).getStackForm(1));
        CropItemList.cropGeneExtractorIV.set(
                new CropGeneExtractor(
                        cropGeneExtractorIV.ID,
                        "Advanced Crop Gene Extractor IV",
                        "Advanced Crop Gene Extractor IV",
                        5).getStackForm(1));
        CropItemList.cropGeneExtractorLuV.set(
                new CropGeneExtractor(
                        cropGeneExtractorLuV.ID,
                        "Advanced Crop Gene Extractor V",
                        "Advanced Crop Gene Extractor V",
                        6).getStackForm(1));
        CropItemList.cropGeneExtractorZPM.set(
                new CropGeneExtractor(
                        cropGeneExtractorZPM.ID,
                        "Advanced Crop Gene Extractor VI",
                        "Advanced Crop Gene Extractor VI",
                        7).getStackForm(1));
        CropItemList.cropGeneExtractorUV.set(
                new CropGeneExtractor(
                        cropGeneExtractorUV.ID,
                        "Advanced Crop Gene Extractor VII",
                        "Advanced Crop Gene Extractor VII",
                        8).getStackForm(1));
        CropItemList.cropGeneExtractorUHV.set(
                new CropGeneExtractor(
                        cropGeneExtractorUHV.ID,
                        "Advanced Crop Gene Extractor VIII",
                        "Advanced Crop Gene Extractor VIII",
                        9).getStackForm(1));

        CropItemList.cropReplicatorLV.set(
                new CropReplicator(cropReplicatorLV.ID, "Basic Crop Replicator", "Basic Crop Replicator", 1)
                        .getStackForm(1));
        CropItemList.cropReplicatorMV.set(
                new CropReplicator(cropReplicatorMV.ID, "Advanced Crop Replicator I", "Advanced Crop Replicator I", 2)
                        .getStackForm(1));
        CropItemList.cropReplicatorHV.set(
                new CropReplicator(cropReplicatorHV.ID, "Advanced Crop Replicator II", "Advanced Crop Replicator II", 3)
                        .getStackForm(1));
        CropItemList.cropReplicatorEV.set(
                new CropReplicator(
                        cropReplicatorEV.ID,
                        "Advanced Crop Replicator III",
                        "Advanced Crop Replicator III",
                        4).getStackForm(1));
        CropItemList.cropReplicatorIV.set(
                new CropReplicator(cropReplicatorIV.ID, "Advanced Crop Replicator IV", "Advanced Crop Replicator IV", 5)
                        .getStackForm(1));
        CropItemList.cropReplicatorLuV.set(
                new CropReplicator(cropReplicatorLuV.ID, "Advanced Crop Replicator V", "Advanced Crop Replicator V", 6)
                        .getStackForm(1));
        CropItemList.cropReplicatorZPM.set(
                new CropReplicator(
                        cropReplicatorZPM.ID,
                        "Advanced Crop Replicator VI",
                        "Advanced Crop Replicator VI",
                        7).getStackForm(1));
        CropItemList.cropReplicatorUV.set(
                new CropReplicator(
                        cropReplicatorUV.ID,
                        "Advanced Crop Replicator VII",
                        "Advanced Crop Replicator VII",
                        8).getStackForm(1));
        CropItemList.cropReplicatorUHV.set(
                new CropReplicator(
                        cropReplicatorUHV.ID,
                        "Advanced Crop Replicator VIII",
                        "Advanced Crop Replicator VIII",
                        9).getStackForm(1));

        CropItemList.cropSynthesiserLV.set(
                new CropSynthesiser(cropSynthesiserLV.ID, "Basic Crop Synthesiser", "Basic Crop Synthesiser", 1)
                        .getStackForm(1));
        CropItemList.cropSynthesiserMV.set(
                new CropSynthesiser(
                        cropSynthesiserMV.ID,
                        "Advanced Crop Synthesiser I",
                        "Advanced Crop Synthesiser I",
                        2).getStackForm(1));
        CropItemList.cropSynthesiserHV.set(
                new CropSynthesiser(
                        cropSynthesiserHV.ID,
                        "Advanced Crop Synthesiser II",
                        "Advanced Crop Synthesiser II",
                        3).getStackForm(1));
        CropItemList.cropSynthesiserEV.set(
                new CropSynthesiser(
                        cropSynthesiserEV.ID,
                        "Advanced Crop Synthesiser III",
                        "Advanced Crop Synthesiser III",
                        4).getStackForm(1));
        CropItemList.cropSynthesiserIV.set(
                new CropSynthesiser(
                        cropSynthesiserIV.ID,
                        "Advanced Crop Synthesiser IV",
                        "Advanced Crop Synthesiser IV",
                        5).getStackForm(1));
        CropItemList.cropSynthesiserLuV.set(
                new CropSynthesiser(
                        cropSynthesiserLuV.ID,
                        "Advanced Crop Synthesiser V",
                        "Advanced Crop Synthesiser V",
                        6).getStackForm(1));
        CropItemList.cropSynthesiserZPM.set(
                new CropSynthesiser(
                        cropSynthesiserZPM.ID,
                        "Advanced Crop Synthesiser VI",
                        "Advanced Crop Synthesiser VI",
                        7).getStackForm(1));
        CropItemList.cropSynthesiserUV.set(
                new CropSynthesiser(
                        cropSynthesiserUV.ID,
                        "Advanced Crop Synthesiser VII",
                        "Advanced Crop Synthesiser VII",
                        8).getStackForm(1));
        CropItemList.cropSynthesiserUHV.set(
                new CropSynthesiser(
                        cropSynthesiserUHV.ID,
                        "Advanced Crop Synthesiser VIII",
                        "Advanced Crop Synthesiser VIII",
                        9).getStackForm(1));

        CropItemList.cropWeedPicker
                .set(new CropWeedPicker(12528, "Basic CropWeedPicker", "Basic CropWeedPicker", 1).getStackForm(1));

        CppFluids.register_Fluids();
        CppItems.register_Items();
        CppItems.OreDictItems();

    }

    @EventHandler
    public void init(FMLInitializationEvent init) {
        CppItems.register_recipes();
        BiomeHumidityBonus.apply();
    }

    @EventHandler
    public void postInit(FMLPostInitializationEvent postinit) {
        CropLoader.register();
        CropLoader.registerBaseSeed();
        final GTHandler GTHandler = new GTHandler();
        GTHandler.run();
    }

    @EventHandler
    public void onFMLServerStart(FMLServerStartingEvent event) {
        event.registerServerCommand(new EnableDebug());
    }
}
