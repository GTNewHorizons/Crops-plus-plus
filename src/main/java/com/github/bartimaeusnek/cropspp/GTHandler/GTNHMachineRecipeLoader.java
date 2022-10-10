package com.github.bartimaeusnek.cropspp.GTHandler;

import com.github.bartimaeusnek.croploadcore.ModsLoaded;
import com.github.bartimaeusnek.croploadcore.OreDict;
import com.github.bartimaeusnek.cropspp.fluids.CppFluids;
import com.github.bartimaeusnek.cropspp.items.CppItems;
import com.github.bartimaeusnek.cropspp.items.CppPotions;

import cpw.mods.fml.common.Loader;
import gregtech.api.GregTech_API;
import gregtech.api.enums.GT_Values;
import gregtech.api.enums.ItemList;
import gregtech.api.enums.Materials;
import gregtech.api.enums.OrePrefixes;
import gregtech.api.util.GT_ModHandler;
import gregtech.api.util.GT_OreDictUnificator;
import gregtech.api.util.GT_Utility;
import ic2.api.item.IC2Items;
import ic2.core.Ic2Items;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.oredict.OreDictionary;

import java.util.Locale;

public class GTNHMachineRecipeLoader implements Runnable {


    public GTNHMachineRecipeLoader() {
        super();
    }

    @Override
    public final void run() {

        //Space Modifier = Space Plant (Tier13, Naquadah plant +1 tier)
        //Magic Modifier = Primordial Pearl,

        //StonePlant
        GT_Values.RA.addCompressorRecipe(Materials.Marble.getDust(9), Materials.Marble.getBlocks(1), 300, 2);// Materials.Marble.getPlates(1), 300, 2);
        GT_Values.RA.addCompressorRecipe(Materials.GraniteRed.getDust(1), Materials.GraniteRed.getPlates(1), 300, 2);
        GT_Values.RA.addCompressorRecipe(Materials.GraniteBlack.getDust(1), Materials.GraniteBlack.getPlates(1), 300, 2);
        GT_Values.RA.addCompressorRecipe(Materials.Stone.getPlates(9), new ItemStack(Blocks.stone), 300, 2);
        //GT_Values.RA.addCompressorRecipe(Materials.Marble.getPlates(9), Materials.Marble.getBlocks(1),300,2);
        GT_Values.RA.addCompressorRecipe(Materials.GraniteRed.getPlates(9), new ItemStack(GregTech_API.sBlockGranites, 1, 8), 300, 2);
        GT_Values.RA.addCompressorRecipe(Materials.GraniteBlack.getPlates(9), new ItemStack(GregTech_API.sBlockGranites), 300, 2);


        //honey related
        GT_Values.RA.addCentrifugeRecipe(GT_Utility.getIntegratedCircuit(9), GT_Values.NI, new FluidStack(FluidRegistry.getFluid("for.honey"), 1000), GT_Values.NF, new ItemStack(Items.sugar, 9, 0), GT_Values.NI, GT_Values.NI, GT_Values.NI, GT_Values.NI, GT_Values.NI, new int[]{10000}, 1020, 8, false);
        if (ModsLoaded.BoP) {
            GT_Values.RA.addCentrifugeRecipe(GT_Utility.getIntegratedCircuit(9), GT_Values.NI, new FluidStack(FluidRegistry.getFluid("honey"), 1000), GT_Values.NF, new ItemStack(Items.sugar, 9, 0), GT_Values.NI, GT_Values.NI, GT_Values.NI, GT_Values.NI, GT_Values.NI, new int[]{10000}, 1020, 8, false);
            GT_Values.RA.addCentrifugeRecipe(GT_Utility.getIntegratedCircuit(1), GT_Values.NI, new FluidStack(FluidRegistry.getFluid("honey"), 1000), new FluidStack(FluidRegistry.getFluid("for.honey"), 1000), GT_Values.NI, GT_Values.NI, GT_Values.NI, GT_Values.NI, GT_Values.NI, GT_Values.NI, new int[]{}, 1020, 8, false);
        }

        //Ethanol related
        //GT_Values.RA.addFluidCannerRecipe(new ItemStack(IC2Items.getItem("mugBooze").getItem(),1,OreDictionary.WILDCARD_VALUE), IC2Items.getItem("mugEmpty"), GT_Values.NF,new FluidStack(FluidRegistry.getFluid("potion.rum"), 250));

        for (int i = 0; i < CppPotions.textureNames.length; i++) {
            GT_Values.RA.addFluidCannerRecipe(new ItemStack(CppItems.CppPotions, 1, i), new ItemStack(Items.glass_bottle), GT_Values.NF, new FluidStack(FluidRegistry.getFluid("potion." + CppPotions.textureNames[i].toLowerCase(Locale.ENGLISH)), 250));
            GT_Values.RA.addFluidCannerRecipe(new ItemStack(Items.glass_bottle), new ItemStack(CppItems.CppPotions, 1, i), new FluidStack(FluidRegistry.getFluid("potion." + CppPotions.textureNames[i].toLowerCase(Locale.ENGLISH)), 250), GT_Values.NF);
        }

        GT_Values.RA.addFermentingRecipe(new FluidStack(CppFluids.Mash, 10), new FluidStack(CppFluids.Wash, 8), 1000, false);
        GT_Values.RA.addFermentingRecipe(new FluidStack(CppFluids.Wash, 20), new FluidStack(FluidRegistry.getFluid("potion.wine"), 8), 1000, false);
        GT_Values.RA.addFermentingRecipe(new FluidStack(FluidRegistry.getFluid("potion.wheatyjuice"), 10), new FluidStack(CppFluids.FWheat, 8), 1020, false);
        GT_Values.RA.addFermentingRecipe(new FluidStack(FluidRegistry.getFluid("potion.reedwater"), 10), new FluidStack(CppFluids.FReed, 8), 1020, false);

        GT_Values.RA.addDistillationTowerRecipe(new FluidStack(FluidRegistry.getFluid("potion.rum"), 1000), new FluidStack[]{Materials.Ethanol.getFluid(580L), Materials.Water.getFluid(420L)}, new ItemStack(Items.sugar), 80, 180);
        GT_Values.RA.addDistillationTowerRecipe(new FluidStack(FluidRegistry.getFluid("potion.piratebrew"), 1000), new FluidStack[]{Materials.Ethanol.getFluid(800L), Materials.Water.getFluid(200L)}, GT_Values.NI, 80, 180);
        GT_Values.RA.addDistillationTowerRecipe(new FluidStack(FluidRegistry.getFluid("potion.beer"), 1000), new FluidStack[]{Materials.Ethanol.getFluid(45L), Materials.Water.getFluid(955L)}, IC2Items.getItem("fertilizer"), 80, 180);
        GT_Values.RA.addDistillationTowerRecipe(new FluidStack(FluidRegistry.getFluid("potion.darkbeer"), 1000), new FluidStack[]{Materials.Ethanol.getFluid(75L), Materials.Water.getFluid(925L)}, IC2Items.getItem("fertilizer"), 80, 180);
        GT_Values.RA.addDistillationTowerRecipe(new FluidStack(FluidRegistry.getFluid("potion.cider"), 1000), new FluidStack[]{Materials.Ethanol.getFluid(100L), Materials.Water.getFluid(900L)}, IC2Items.getItem("fertilizer"), 80, 180);
        GT_Values.RA.addDistillationTowerRecipe(new FluidStack(FluidRegistry.getFluid("potion.wine"), 1000), new FluidStack[]{Materials.Ethanol.getFluid(120L), Materials.Water.getFluid(880L)}, IC2Items.getItem("fertilizer"), 80, 180);
        GT_Values.RA.addDistillationTowerRecipe(new FluidStack(FluidRegistry.getFluid("potion.vodka"), 1000), new FluidStack[]{Materials.Ethanol.getFluid(400L), Materials.Water.getFluid(600L)}, GT_Values.NI, 80, 180);
        GT_Values.RA.addDistillationTowerRecipe(new FluidStack(FluidRegistry.getFluid("potion.alcopops"), 1000), new FluidStack[]{Materials.Ethanol.getFluid(160L), Materials.Water.getFluid(840L)}, new ItemStack(Items.sugar, 8, 0), 80, 180);
        GT_Values.RA.addDistillationTowerRecipe(new FluidStack(CppFluids.Korn, 1000), new FluidStack[]{Materials.Ethanol.getFluid(320L), Materials.Water.getFluid(680L)}, GT_Values.NI, 80, 180);
        GT_Values.RA.addDistillationTowerRecipe(new FluidStack(CppFluids.DKorn, 1000), new FluidStack[]{Materials.Ethanol.getFluid(380L), Materials.Water.getFluid(620L)}, GT_Values.NI, 80, 180);
        GT_Values.RA.addDistillationTowerRecipe(new FluidStack(CppFluids.SWhine, 1000), new FluidStack[]{Materials.Ethanol.getFluid(700L), Materials.Water.getFluid(300L)}, GT_Values.NI, 80, 180);
        GT_Values.RA.addDistillationTowerRecipe(new FluidStack(CppFluids.GHP, 1000), new FluidStack[]{Materials.Ethanol.getFluid(750L), Materials.Water.getFluid(250L)}, GT_Values.NI, 80, 180);
        GT_Values.RA.addDistillationTowerRecipe(new FluidStack(CppFluids.jagi, 1000), new FluidStack[]{Materials.Ethanol.getFluid(350L), Materials.Water.getFluid(650L)}, GT_Values.NI, 80, 180);
        GT_Values.RA.addDistillationTowerRecipe(new FluidStack(CppFluids.njagi, 1000), new FluidStack[]{Materials.Ethanol.getFluid(350L), Materials.Water.getFluid(650L)}, GT_Values.NI, 80, 180);

        GT_Values.RA.addDistilleryRecipe(GT_Utility.getIntegratedCircuit(1), new FluidStack(FluidRegistry.getFluid("potion.rum"), 100), Materials.Ethanol.getFluid(50L), 16, 24, false);
        GT_Values.RA.addDistilleryRecipe(GT_Utility.getIntegratedCircuit(2), new FluidStack(FluidRegistry.getFluid("potion.rum"), 100), Materials.Water.getFluid(42L), 16, 24, false);
        GT_Values.RA.addDistilleryRecipe(GT_Utility.getIntegratedCircuit(1), new FluidStack(FluidRegistry.getFluid("potion.vodka"), 100), Materials.Ethanol.getFluid(35L), 16, 24, false);
        GT_Values.RA.addDistilleryRecipe(GT_Utility.getIntegratedCircuit(2), new FluidStack(FluidRegistry.getFluid("potion.vodka"), 100), Materials.Water.getFluid(60L), 16, 24, false);
        GT_Values.RA.addDistilleryRecipe(GT_Utility.getIntegratedCircuit(1), new FluidStack(CppFluids.Korn, 100), Materials.Ethanol.getFluid(25L), 16, 24, false);
        GT_Values.RA.addDistilleryRecipe(GT_Utility.getIntegratedCircuit(2), new FluidStack(CppFluids.Korn, 100), Materials.Water.getFluid(68L), 16, 24, false);
        GT_Values.RA.addDistilleryRecipe(GT_Utility.getIntegratedCircuit(1), new FluidStack(CppFluids.DKorn, 100), Materials.Ethanol.getFluid(30L), 16, 24, false);
        GT_Values.RA.addDistilleryRecipe(GT_Utility.getIntegratedCircuit(2), new FluidStack(CppFluids.DKorn, 100), Materials.Water.getFluid(62L), 16, 24, false);
        GT_Values.RA.addDistilleryRecipe(GT_Utility.getIntegratedCircuit(1), new FluidStack(CppFluids.FWheat, 80), new FluidStack(CppFluids.Korn, 1), 22, 24, false);
        GT_Values.RA.addDistilleryRecipe(GT_Utility.getIntegratedCircuit(2), new FluidStack(CppFluids.FWheat, 95), new FluidStack(CppFluids.DKorn, 1), 24, 24, false);
        GT_Values.RA.addDistilleryRecipe(GT_Utility.getIntegratedCircuit(3), new FluidStack(CppFluids.FWheat, 100), new FluidStack(FluidRegistry.getFluid("potion.vodka"), 1), 28, 64, false);
        GT_Values.RA.addDistilleryRecipe(GT_Utility.getIntegratedCircuit(4), new FluidStack(CppFluids.FWheat, 200), new FluidStack(FluidRegistry.getFluid("fermentedbiomass"), 3), 28, 64, false);
        GT_Values.RA.addDistilleryRecipe(GT_Utility.getIntegratedCircuit(5), new FluidStack(CppFluids.FWheat, 1000), Materials.Ethanol.getFluid(4L), 220, 120, false);
        GT_Values.RA.addDistilleryRecipe(GT_Utility.getIntegratedCircuit(1), new FluidStack(CppFluids.FReed, 100), new FluidStack(CppFluids.SWhine, 7), 22, 24, false);
        GT_Values.RA.addDistilleryRecipe(GT_Utility.getIntegratedCircuit(4), new FluidStack(CppFluids.FReed, 200), new FluidStack(FluidRegistry.getFluid("fermentedbiomass"), 4), 24, 24, false);
        GT_Values.RA.addDistilleryRecipe(GT_Utility.getIntegratedCircuit(5), new FluidStack(CppFluids.FReed, 1000), Materials.Ethanol.getFluid(5L), 220, 120, false);
        //GT_Values.RA.addDistilleryRecipe(GT_Utility.getIntegratedCircuit(1), new FluidStack(BppFluids.Mash,100), new FluidStack(FluidRegistry.getFluid("potion.wine"), 20), 22, 24, false);
        GT_Values.RA.addDistilleryRecipe(GT_Utility.getIntegratedCircuit(4), new FluidStack(CppFluids.Mash, 200), new FluidStack(FluidRegistry.getFluid("biomass"), 4), 24, 24, false);
        GT_Values.RA.addDistilleryRecipe(GT_Utility.getIntegratedCircuit(1), new FluidStack(CppFluids.Wash, 100), new FluidStack(CppFluids.GHP, 6), 22, 24, false);
        GT_Values.RA.addDistilleryRecipe(GT_Utility.getIntegratedCircuit(4), new FluidStack(CppFluids.Wash, 100), new FluidStack(FluidRegistry.getFluid("fermentedbiomass"), 14), 24, 24, false);

        GT_Values.RA.addMixerRecipe(new ItemStack(Items.sugar, 32, 0), new ItemStack(Items.dye, 4, 1), new ItemStack(Items.dye, 4, 11), new ItemStack(Items.dye, 4, 2), Materials.Water.getCells(4), GT_Values.NI, new FluidStack(CppFluids.GHP, 375), new FluidStack(FluidRegistry.getFluid("potion.alcopops"), 4375), Materials.Empty.getCells(4), 10, 8);
        GT_Values.RA.addMixerRecipe(new ItemStack(Items.sugar, 8, 0), new ItemStack(Items.dye, 1, 1), new ItemStack(Items.dye, 1, 11), new ItemStack(Items.dye, 1, 2), Materials.Water.getCells(1), GT_Values.NI, new FluidStack(FluidRegistry.getFluid("potion.vodka"), 500), new FluidStack(FluidRegistry.getFluid("potion.alcopops"), 1500), Materials.Empty.getCells(1), 10, 8);
        GT_Values.RA.addMixerRecipe(new ItemStack(Items.sugar, 8, 0), new ItemStack(Items.dye, 1, 1), new ItemStack(Items.dye, 1, 11), new ItemStack(Items.dye, 1, 2), Materials.Water.getCells(1), GT_Values.NI, new FluidStack(CppFluids.Korn, 1000), new FluidStack(FluidRegistry.getFluid("potion.alcopops"), 2000), Materials.Empty.getCells(1), 10, 8);
        GT_Values.RA.addMixerRecipe(new ItemStack(Items.sugar, 8, 0), new ItemStack(Items.dye, 1, 1), new ItemStack(Items.dye, 1, 11), new ItemStack(Items.dye, 1, 2), Materials.Water.getCells(1), GT_Values.NI, new FluidStack(CppFluids.DKorn, 750), new FluidStack(FluidRegistry.getFluid("potion.alcopops"), 1750), Materials.Empty.getCells(1), 10, 8);
        GT_Values.RA.addMixerRecipe(Materials.Water.getCells(1), GT_Values.NI, GT_Values.NI, GT_Values.NI, Materials.Ethanol.getFluid(1000L), new FluidStack(FluidRegistry.getFluid("potion.vodka"), 2500), Materials.Empty.getCells(1), 10, 8);
        //GT_Values.RA.addMixerRecipe(new ItemStack(Items.reeds,64),GT_Values.NI, GT_Values.NI, GT_Values.NI, GT_Values.NI, GT_Values.NI, Materials.Water.getFluid(1000L),new FluidStack(FluidRegistry.getFluid("potion.reedwater"),1000),GT_Values.NI, 1200,8);
        GT_Values.RA.addMixerRecipe(Materials.Water.getCells(1), new ItemStack(Items.sugar), GT_Values.NI, GT_Values.NI, new FluidStack(CppFluids.SWhine, 5000), new FluidStack(FluidRegistry.getFluid("potion.rum"), 6000), Materials.Empty.getCells(1), 10, 8);
        GT_Values.RA.addMixerRecipe(new ItemStack(Items.sugar, 8, 0), OreDict.ISget("cropSpiceleaf"), OreDict.ISget("cropGinger"), new ItemStack(Items.dye, 1, 2), Materials.Water.getCells(1), GT_Values.NI, new FluidStack(FluidRegistry.getFluid("potion.vodka"), 4000), new FluidStack(CppFluids.njagi, 5000), Materials.Empty.getCells(1), 10, 8);

        GT_Values.RA.addMixerRecipe(ItemList.Crop_Drop_Chilly.get(1), Materials.CosmicNeutronium.getDustTiny(1), ItemList.Crop_Drop_Lemon.get(64), ItemList.Crop_Drop_TeaLeaf.get(64), CppItems.ModifierMagic.splitStack(8), CppItems.ModifierSpace.splitStack(9), new FluidStack(CppFluids.njagi, 50000), new FluidStack(CppFluids.jagi, 250), GT_Values.NI, 1, 30720);


        //Brewery
        if (OreDictionary.getOres("listAllberry").size() >= 1)
            for (int i = 0; i < OreDictionary.getOres("listAllberry").size(); i++)
                GT_Values.RA.addBrewingRecipe(OreDictionary.getOres("listAllberry").get(i).splitStack(16), Materials.Water.getFluid(1000L).getFluid(), CppFluids.Mash, false);
        GT_Values.RA.addBrewingRecipe(new ItemStack(Items.sugar, 8), FluidRegistry.getFluid("potion.weakness"), CppFluids.Mash, false);
        //GT_Values.RA.addBrewingRecipe(new ItemStack(BppItems.BppBerries,1,1), Materials.Water.getFluid(1000L).getFluid(), FluidRegistry.getFluid("potion.reedwater"), false);


        //Sugar Related
        GT_Values.RA.addExtractorRecipe(new ItemStack(CppItems.CppBerries, 1, 1), new ItemStack(Items.sugar, 8, 0), 160, 8);

        //Dyes from Plants
        for (int i = 0; i < OreDictionary.getOres("cropBlackberry").size(); i++)
            GT_Values.RA.addChemicalRecipe(OreDictionary.getOres("cropBlackberry").get(i).splitStack(16), Materials.Salt.getDust(2), Materials.SulfuricAcid.getFluid(432L), new FluidStack(FluidRegistry.getFluid("dye.chemical.dyeblack"), 288), new ItemStack(Items.sugar), 600, 48);
        for (int i = 0; i < OreDictionary.getOres("cropBlueberry").size(); i++)
            GT_Values.RA.addChemicalRecipe(OreDictionary.getOres("cropBlueberry").get(i).splitStack(16), Materials.Salt.getDust(2), Materials.SulfuricAcid.getFluid(432L), new FluidStack(FluidRegistry.getFluid("dye.chemical.dyeblue"), 288), new ItemStack(Items.sugar), 600, 48);
        for (int i = 0; i < OreDictionary.getOres("cropRaspberry").size(); i++)
            GT_Values.RA.addChemicalRecipe(OreDictionary.getOres("cropRaspberry").get(i).splitStack(16), Materials.Salt.getDust(2), Materials.SulfuricAcid.getFluid(432L), new FluidStack(FluidRegistry.getFluid("dye.chemical.dyepink"), 288), new ItemStack(Items.sugar), 600, 48);
        for (int i = 0; i < OreDictionary.getOres("cropVine").size(); i++) {
            if (!OreDictionary.getOres("cropVine").get(i).getUnlocalizedName().equals("tile.Thornvines"))
                GT_Values.RA.addChemicalRecipe(OreDictionary.getOres("cropVine").get(i).splitStack(16), Materials.Salt.getDust(2), Materials.SulfuricAcid.getFluid(432L), new FluidStack(FluidRegistry.getFluid("dye.chemical.dyegreen"), 288), GT_Values.NI, 600, 48);
            else
                GT_Values.RA.addChemicalRecipe(OreDictionary.getOres("cropVine").get(i).splitStack(16), Materials.Salt.getDust(2), Materials.SulfuricAcid.getFluid(432L), new FluidStack(FluidRegistry.getFluid("dye.chemical.dyeyellow"), 288), GT_Values.NI, 600, 48);
        }
        for (int i = 0; i < OreDictionary.getOres("cropCacti").size(); i++)
            GT_Values.RA.addChemicalRecipe(OreDictionary.getOres("cropCacti").get(i).splitStack(16), Materials.Salt.getDust(2), Materials.SulfuricAcid.getFluid(432L), new FluidStack(FluidRegistry.getFluid("dye.chemical.dyegreen"), 288), GT_Values.NI, 600, 48);
        for (int i = 0; i < OreDictionary.getOres("cropGooseberry").size(); i++)
            GT_Values.RA.addChemicalRecipe(OreDictionary.getOres("cropGooseberry").get(i).splitStack(16), Materials.Salt.getDust(2), Materials.SulfuricAcid.getFluid(432L), new FluidStack(FluidRegistry.getFluid("dye.chemical.dyeyellow"), 288), new ItemStack(Items.sugar), 600, 48);
        for (int i = 0; i < OreDictionary.getOres("cropStrawberry").size(); i++)
            GT_Values.RA.addChemicalRecipe(OreDictionary.getOres("cropStrawberry").get(i).splitStack(16), Materials.Salt.getDust(2), Materials.SulfuricAcid.getFluid(432L), new FluidStack(FluidRegistry.getFluid("dye.chemical.dyered"), 288), new ItemStack(Items.sugar), 600, 48);

        if (ModsLoaded.BoP)
            GT_Values.RA.addChemicalRecipe(GT_ModHandler.getModItem("BiomesOPlenty", "food", 16L), Materials.Salt.getDust(2), Materials.SulfuricAcid.getFluid(432L), new FluidStack(FluidRegistry.getFluid("dye.chemical.dyered"), 288), new ItemStack(Items.sugar), 600, 48);

        GT_Values.RA.addChemicalRecipe(new ItemStack(CppItems.CppBerries, 16, 0), Materials.Salt.getDust(2), Materials.SulfuricAcid.getFluid(432L), new FluidStack(FluidRegistry.getFluid("dye.chemical.dyepurple"), 288), new ItemStack(Items.sugar), 600, 48);

        if (ModsLoaded.Natura) {
            GT_Values.RA.addChemicalRecipe(GT_ModHandler.getModItem("Natura", "berry.nether", 16L, 0), Materials.Salt.getDust(2), Materials.SulfuricAcid.getFluid(432L), new FluidStack(FluidRegistry.getFluid("dye.chemical.dyelime"), 288), new ItemStack(Items.sugar), 600, 48);
            GT_Values.RA.addChemicalRecipe(GT_ModHandler.getModItem("Natura", "berry.nether", 16L, 1), Materials.Salt.getDust(2), Materials.SulfuricAcid.getFluid(432L), new FluidStack(FluidRegistry.getFluid("dye.chemical.dyelightgray"), 288), new ItemStack(Items.sugar), 600, 48);
            GT_Values.RA.addChemicalRecipe(GT_ModHandler.getModItem("Natura", "berry.nether", 16L, 2), Materials.Salt.getDust(2), Materials.SulfuricAcid.getFluid(432L), new FluidStack(FluidRegistry.getFluid("dye.chemical.dyelightblue"), 288), new ItemStack(Items.sugar), 600, 48);
            GT_Values.RA.addChemicalRecipe(GT_ModHandler.getModItem("Natura", "berry.nether", 16L, 3), Materials.Salt.getDust(2), Materials.SulfuricAcid.getFluid(432L), new FluidStack(FluidRegistry.getFluid("dye.chemical.dyelime"), 288), new ItemStack(Items.sugar), 600, 48);
        }

        //Goldfish
        GT_Values.RA.addFluidExtractionRecipe(CppItems.GoldfischS, new ItemStack(Items.gold_nugget), Materials.FishOil.getFluid(100L), 1, 16, 8);
        GT_Values.RA.addPulveriserRecipe(CppItems.GoldfischS, new ItemStack[]{Materials.MeatRaw.getDust(1), Materials.Gold.getDustTiny(1)}, new int[]{10000, 100}, 3, 8, false);

        //Space Modifier
        GT_Values.RA.addMultiblockChemicalRecipe(new ItemStack[]{Materials.Iron.getDust(1), new ItemStack(CppItems.Modifier, 16, 0)}, new FluidStack[]{Materials.UUMatter.getFluid(Materials.MeteoricIron.getNeutrons() + Materials.MeteoricIron.getProtons())}, new FluidStack[]{}, new ItemStack[]{Materials.MeteoricIron.getDust(1)}, 240, 480);
        GT_Values.RA.addMultiblockChemicalRecipe(new ItemStack[]{Materials.Steel.getDust(1), new ItemStack(CppItems.Modifier, 16, 0)}, new FluidStack[]{Materials.UUMatter.getFluid(Materials.MeteoricSteel.getNeutrons() + Materials.MeteoricSteel.getProtons())}, new FluidStack[]{}, new ItemStack[]{Materials.MeteoricSteel.getDust(1)}, 240, 480);
        GT_Values.RA.addMultiblockChemicalRecipe(new ItemStack[]{Materials.Bismuth.getDust(1), new ItemStack(CppItems.Modifier, 16, 0)}, new FluidStack[]{Materials.UUMatter.getFluid(Materials.Oriharukon.getNeutrons() + Materials.Oriharukon.getProtons())}, new FluidStack[]{}, new ItemStack[]{Materials.Oriharukon.getDust(1)}, 240, 480);
        GT_Values.RA.addMultiblockChemicalRecipe(new ItemStack[]{Materials.Titanium.getDust(1), new ItemStack(CppItems.Modifier, 16, 0)}, new FluidStack[]{Materials.UUMatter.getFluid(Materials.Desh.getNeutrons() + Materials.Desh.getProtons())}, new FluidStack[]{}, new ItemStack[]{Materials.Desh.getDust(1)}, 240, 480);
        //Non-elements
        GT_Values.RA.addMultiblockChemicalRecipe(new ItemStack[]{Materials.Ice.getDust(1), new ItemStack(CppItems.Modifier, 16, 0)}, new FluidStack[]{Materials.UUMatter.getFluid((Materials.Water.getProtons() + Materials.Water.getNeutrons()) * 10)}, new FluidStack[]{}, new ItemStack[]{Materials.CallistoIce.getDust(1)}, 240, 7680);
        GT_Values.RA.addMultiblockChemicalRecipe(new ItemStack[]{Materials.Lead.getDust(1), new ItemStack(CppItems.Modifier, 16, 0)}, new FluidStack[]{Materials.UUMatter.getFluid((Materials.Water.getProtons() + Materials.Water.getNeutrons()) * 10)}, new FluidStack[]{}, new ItemStack[]{Materials.Ledox.getDust(1)}, 240, 7680);
        if (Loader.isModLoaded("Avaritia"))
        	GT_Values.RA.addMultiblockChemicalRecipe(new ItemStack[]{GT_ModHandler.getModItem("Avaritia", "Resource", 1L, 1), new ItemStack(CppItems.Modifier, 32, 0)}, new FluidStack[]{Materials.UUMatter.getFluid(100L)}, null, new ItemStack[]{Materials.MysteriousCrystal.getDust(1)}, 240, 122880);
        GT_Values.RA.addMultiblockChemicalRecipe(new ItemStack[]{Materials.MeteoricIron.getDust(1), new ItemStack(CppItems.Modifier, 64, 0)}, new FluidStack[]{Materials.UUMatter.getFluid((Materials.Neutronium.getNeutrons() + Materials.Neutronium.getProtons()))}, new FluidStack[]{}, new ItemStack[]{Materials.DeepIron.getDust(1)}, 120, 122880);
        GT_Values.RA.addMultiblockChemicalRecipe(new ItemStack[]{Materials.Plutonium241.getDust(1), new ItemStack(CppItems.Modifier, 64, 0)}, new FluidStack[]{Materials.UUMatter.getFluid((Materials.Neutronium.getNeutrons() + Materials.Neutronium.getProtons()))}, new FluidStack[]{}, new ItemStack[]{Materials.BlackPlutonium.getDust(1)}, 120, 500000);

        if (ModsLoaded.TC) {
            //Magic Modifier PrimP
            GT_Values.RA.addExtractorRecipe(GT_ModHandler.getModItem("Thaumcraft", "ItemEldritchObject", 1, 3), new ItemStack(CppItems.Modifier, 8, 1), 2000, 128);
            GT_Values.RA.addExtractorRecipe(new ItemStack(CppItems.Modifier, 1, 1), GT_ModHandler.getModItem("Thaumcraft", "ItemResource", 16L, 14), 2400, 128);
            if (!ModsLoaded.dreamcraft)
                GT_Values.RA.addAutoclaveRecipe(new ItemStack(CppItems.Modifier, 16, 1), Materials.UUMatter.getFluid(52L), GT_ModHandler.getModItem("Thaumcraft", "ItemEldritchObject", 1, 3), 10000, 24000, 384, false);
            else
                GT_Values.RA.addAutoclaveRecipe(new ItemStack(CppItems.Modifier, 16, 1), Materials.UUMatter.getFluid(52L), com.dreammaster.item.ItemList.PrimordialPearlFragment.getIS().splitStack(3), 10000, 24000, 384, false);
            GT_Values.RA.addAutoclaveRecipe(GT_ModHandler.getModItem("Thaumcraft", "ItemResource", 32L, 14), Materials.UUMatter.getFluid(500L), new ItemStack(CppItems.Modifier, 1, 1), 3300, 2400, 120, false);
            //Magic Modifier
            GT_Values.RA.addMultiblockChemicalRecipe(new ItemStack[]{Materials.Iron.getDust(1), new ItemStack(CppItems.Modifier, 1, 1)}, new FluidStack[]{Materials.UUMatter.getFluid(Materials.Thaumium.getNeutrons() + Materials.Thaumium.getProtons())}, new FluidStack[]{}, new ItemStack[]{Materials.Thaumium.getDust(1)}, 240, 120);
            GT_Values.RA.addMultiblockChemicalRecipe(new ItemStack[]{Materials.Thaumium.getDust(1), new ItemStack(CppItems.Modifier, 1, 1)}, new FluidStack[]{Materials.UUMatter.getFluid(Materials.Arsenic.getNeutrons() + Materials.Arsenic.getProtons())}, new FluidStack[]{}, new ItemStack[]{Materials.Void.getDust(1)}, 240, 480);
            GT_Values.RA.addMultiblockChemicalRecipe(new ItemStack[]{Materials.Void.getDust(1), new ItemStack(CppItems.Modifier, 2, 1)}, new FluidStack[]{Materials.UUMatter.getFluid(Materials.Indium.getNeutrons() + Materials.Indium.getProtons())}, new FluidStack[]{}, new ItemStack[]{Materials.Shadow.getDust(1)}, 240, 7680);
            GT_Values.RA.addMultiblockChemicalRecipe(new ItemStack[]{Materials.Shadow.getDust(1), new ItemStack(CppItems.Modifier, 16, 1)}, new FluidStack[]{Materials.UUMatter.getFluid((Materials.Osmium.getNeutrons() + Materials.Osmium.getProtons()) * 1000)}, new FluidStack[]{}, new ItemStack[]{Materials.Ichorium.getIngots(1)}, 1800, 30720);
        }

        //Magic Modifier + Space Modifier
        GT_Values.RA.addMultiblockChemicalRecipe(new ItemStack[]{new ItemStack(CppItems.Modifier, 16, 0), new ItemStack(CppItems.Modifier, 4, 1)}, new FluidStack[]{Materials.Platinum.getMolten(288L), Materials.MeteoricIron.getMolten(144L), Materials.UUMatter.getFluid(Materials.Platinum.getProtons() + Materials.Platinum.getNeutrons())}, new FluidStack[]{}, new ItemStack[]{Materials.Mytryl.getDust(1)}, 1800, 7680);
        //coral buff
        GT_Values.RA.addAutoclaveSpaceRecipe(GT_ModHandler.getModItem("BiomesOPlenty", "coral1", 64L, 15), Materials.UUMatter.getFluid(2L), Materials.Sunnarium.getDust(4), 10000, 15000, 7680, true);

        if (ModsLoaded.BoP)
            GT_ModHandler.addCompressionRecipe(GT_ModHandler.getModItem("BiomesOPlenty", "tile.treeMoss", 8L), Ic2Items.plantBall.copy());

        if (!ModsLoaded.dreamcraft) {
            if (ModsLoaded.BoP) {
                GT_Values.RA.addExtractorRecipe(GT_ModHandler.getModItem("BiomesOPlenty", "flowers", 2L, 3), new ItemStack(Items.glowstone_dust, 1, 32767), 300, 2);
                GT_Values.RA.addExtractorRecipe(GT_ModHandler.getModItem("BiomesOPlenty", "mushrooms", 2L, 3), new ItemStack(Items.glowstone_dust, 1, 32767), 300, 2);
                GT_Values.RA.addExtractorRecipe(GT_ModHandler.getModItem("BiomesOPlenty", "coral1", 2L, 15), new ItemStack(Items.glowstone_dust, 8, 32767), 300, 2);
            }
            if (ModsLoaded.Natura) {
                GT_Values.RA.addExtractorRecipe(GT_ModHandler.getModItem("Natura", "Glowshroom", 2L, 0), new ItemStack(Items.glowstone_dust, 1, 32767), 300, 2);
                GT_Values.RA.addExtractorRecipe(GT_ModHandler.getModItem("Natura", "Glowshroom", 2L, 1), new ItemStack(Items.glowstone_dust, 1, 32767), 300, 2);
                GT_Values.RA.addExtractorRecipe(GT_ModHandler.getModItem("Natura", "Glowshroom", 2L, 2), new ItemStack(Items.glowstone_dust, 1, 32767), 300, 2);
            }
        }

        // Not a noob trophy.
        GT_Values.RA.addExtruderRecipe(Materials.Neutronium.getBlocks(64), Materials.Neutronium.getBlocks(64), CppItems.Trophy, 2147483647, 8);

        GT_Values.RA.addFluidExtractionRecipe(new ItemStack(CppItems.Modifier, 1, 0), GT_Values.NI, Materials.UUMatter.getFluid(2L), 5000, 128, 4);

        //Chem Refine
        GT_Values.RA.addMultiblockChemicalRecipe(new ItemStack[]{new ItemStack(CppItems.Modifier, 9, 0), GT_ModHandler.getModItem("GalacticraftCore", "item.meteoricIronRaw", 1L, 0)}, new FluidStack[]{Materials.Water.getFluid(1000L)}, new FluidStack[]{}, new ItemStack[]{Materials.MeteoricIron.getDust(4)}, 240, 480);
        GT_Values.RA.addMultiblockChemicalRecipe(new ItemStack[]{new ItemStack(CppItems.Modifier, 9, 0), GT_ModHandler.getModItem("GalacticraftMars", "item.null", 1L, 0)}, new FluidStack[]{Materials.Water.getFluid(1000L)}, new FluidStack[]{}, new ItemStack[]{Materials.Desh.getDust(4)}, 240, 480);
        GT_Values.RA.addMultiblockChemicalRecipe(new ItemStack[]{new ItemStack(CppItems.Modifier, 9, 0), GT_OreDictUnificator.get(OrePrefixes.crushed.get(Materials.MeteoricIron), 1)}, new FluidStack[]{Materials.Water.getFluid(1000L)}, new FluidStack[]{}, new ItemStack[]{GT_OreDictUnificator.get(OrePrefixes.crushedPurified.get(Materials.MeteoricIron), 4)}, 240, 480);
        GT_Values.RA.addMultiblockChemicalRecipe(new ItemStack[]{new ItemStack(CppItems.Modifier, 9, 0), GT_OreDictUnificator.get(OrePrefixes.crushed.get(Materials.Desh), 1)}, new FluidStack[]{Materials.Water.getFluid(1000L)}, new FluidStack[]{}, new ItemStack[]{GT_OreDictUnificator.get(OrePrefixes.crushedPurified.get(Materials.Desh), 4)}, 240, 480);
        GT_Values.RA.addMultiblockChemicalRecipe(new ItemStack[]{new ItemStack(CppItems.Modifier, 9, 0), GT_OreDictUnificator.get(OrePrefixes.crushed.get(Materials.Oriharukon), 1)}, new FluidStack[]{Materials.Water.getFluid(1000L)}, new FluidStack[]{}, new ItemStack[]{GT_OreDictUnificator.get(OrePrefixes.crushedPurified.get(Materials.Oriharukon), 4)}, 240, 480);
        GT_Values.RA.addMultiblockChemicalRecipe(new ItemStack[]{new ItemStack(CppItems.Modifier, 9, 0), GT_OreDictUnificator.get(OrePrefixes.crushed.get(Materials.Ledox), 1)}, new FluidStack[]{Materials.Water.getFluid(1000L)}, new FluidStack[]{}, new ItemStack[]{GT_OreDictUnificator.get(OrePrefixes.crushedPurified.get(Materials.Ledox), 4)}, 240, 480);
        GT_Values.RA.addMultiblockChemicalRecipe(new ItemStack[]{new ItemStack(CppItems.Modifier, 9, 0), GT_OreDictUnificator.get(OrePrefixes.crushed.get(Materials.CallistoIce), 1)}, new FluidStack[]{Materials.Water.getFluid(1000L)}, new FluidStack[]{}, new ItemStack[]{GT_OreDictUnificator.get(OrePrefixes.crushedPurified.get(Materials.CallistoIce), 4)}, 240, 480);
        GT_Values.RA.addMultiblockChemicalRecipe(new ItemStack[]{new ItemStack(CppItems.Modifier, 9, 0), GT_OreDictUnificator.get(OrePrefixes.crushed.get(Materials.BlackPlutonium), 1)}, new FluidStack[]{Materials.Water.getFluid(1000L)}, new FluidStack[]{}, new ItemStack[]{GT_OreDictUnificator.get(OrePrefixes.crushedPurified.get(Materials.BlackPlutonium), 4)}, 240, 30720);
        GT_Values.RA.addMultiblockChemicalRecipe(new ItemStack[]{new ItemStack(CppItems.Modifier, 9, 0), GT_OreDictUnificator.get(OrePrefixes.crushed.get(Materials.DeepIron), 1)}, new FluidStack[]{Materials.Water.getFluid(1000L)}, new FluidStack[]{}, new ItemStack[]{GT_OreDictUnificator.get(OrePrefixes.crushedPurified.get(Materials.DeepIron), 4)}, 240, 30720);

        //Potions from Netherberries
        GT_Values.RA.addBrewingRecipe(GT_ModHandler.getModItem("Natura", "berry.nether", 16L, 0), Materials.Water.getFluid(1000L).getFluid(), FluidRegistry.getFluid("potion.regen"), false);
        GT_Values.RA.addBrewingRecipe(GT_ModHandler.getModItem("Natura", "berry.nether", 16L, 1), Materials.Water.getFluid(1000L).getFluid(), FluidRegistry.getFluid("potion.nightvision"), false);
        GT_Values.RA.addBrewingRecipe(GT_ModHandler.getModItem("Natura", "berry.nether", 16L, 2), Materials.Water.getFluid(1000L).getFluid(), FluidRegistry.getFluid("potion.speed"), false);
        GT_Values.RA.addBrewingRecipe(GT_ModHandler.getModItem("Natura", "berry.nether", 16L, 3), Materials.Water.getFluid(1000L).getFluid(), FluidRegistry.getFluid("potion.strength"), false);
    	
    	/*if (ModsLoaded.dreamcraft)
    	for (int i=0; i<OreDictionary.getOres("blockUnstable").size();i++)
    		for (int j=0; j<OreDictionary.getOres("ingotBedrockium").size();j++)
    	GT_Values.RA.addMultiblockChemicalRecipe(new ItemStack[] {OreDictionary.getOres("blockUnstable").get(i),OreDictionary.getOres("ingotBedrockium").get(j),GT_ModHandler.getModItem("Thaumcraft","ItemEldritchObject",1,3)} , new FluidStack[] {new FluidStack(FluidRegistry.getFluid("mutagen"),1000),Materials.UUMatter.getFluid(1000L)}, GT_Values.NI, new ItemStack[] {new ItemStack(BppItems.Modifier,1,6)}, 2400, 8192);
    	 */
    }
}