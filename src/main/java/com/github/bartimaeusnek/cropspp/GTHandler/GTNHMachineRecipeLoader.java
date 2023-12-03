package com.github.bartimaeusnek.cropspp.GTHandler;

import static gregtech.api.util.GT_Recipe.GT_Recipe_Map.sAutoclaveRecipes;
import static gregtech.api.util.GT_Recipe.GT_Recipe_Map.sBrewingRecipes;
import static gregtech.api.util.GT_Recipe.GT_Recipe_Map.sCentrifugeRecipes;
import static gregtech.api.util.GT_Recipe.GT_Recipe_Map.sChemicalRecipes;
import static gregtech.api.util.GT_Recipe.GT_Recipe_Map.sCompressorRecipes;
import static gregtech.api.util.GT_Recipe.GT_Recipe_Map.sDistillationRecipes;
import static gregtech.api.util.GT_Recipe.GT_Recipe_Map.sDistilleryRecipes;
import static gregtech.api.util.GT_Recipe.GT_Recipe_Map.sExtractorRecipes;
import static gregtech.api.util.GT_Recipe.GT_Recipe_Map.sExtruderRecipes;
import static gregtech.api.util.GT_Recipe.GT_Recipe_Map.sFermentingRecipes;
import static gregtech.api.util.GT_Recipe.GT_Recipe_Map.sFluidCannerRecipes;
import static gregtech.api.util.GT_Recipe.GT_Recipe_Map.sFluidExtractionRecipes;
import static gregtech.api.util.GT_Recipe.GT_Recipe_Map.sMaceratorRecipes;
import static gregtech.api.util.GT_Recipe.GT_Recipe_Map.sMixerRecipes;
import static gregtech.api.util.GT_Recipe.GT_Recipe_Map.sMultiblockChemicalRecipes;
import static gregtech.api.util.GT_RecipeBuilder.HOURS;
import static gregtech.api.util.GT_RecipeBuilder.MINUTES;
import static gregtech.api.util.GT_RecipeBuilder.SECONDS;
import static gregtech.api.util.GT_RecipeBuilder.TICKS;

import java.util.Locale;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.oredict.OreDictionary;

import com.github.bartimaeusnek.croploadcore.OreDict;
import com.github.bartimaeusnek.cropspp.fluids.CppFluids;
import com.github.bartimaeusnek.cropspp.items.CppItems;
import com.github.bartimaeusnek.cropspp.items.CppPotions;

import gregtech.api.GregTech_API;
import gregtech.api.enums.GT_Values;
import gregtech.api.enums.ItemList;
import gregtech.api.enums.Materials;
import gregtech.api.enums.Mods;
import gregtech.api.enums.OrePrefixes;
import gregtech.api.enums.TierEU;
import gregtech.api.util.GT_ModHandler;
import gregtech.api.util.GT_OreDictUnificator;
import gregtech.api.util.GT_Utility;
import ic2.core.Ic2Items;

public class GTNHMachineRecipeLoader implements Runnable {

    public GTNHMachineRecipeLoader() {
        super();
    }

    @Override
    public final void run() {

        // Space Modifier = Space Plant (Tier13, Naquadah plant +1 tier)
        // Magic Modifier = Primordial Pear,

        // StonePlant
        GT_Values.RA.stdBuilder().itemInputs(Materials.Marble.getDust(9)).itemOutputs(Materials.Marble.getBlocks(1))
                .duration(15 * SECONDS).eut(2).addTo(sCompressorRecipes);

        GT_Values.RA.stdBuilder().itemInputs(Materials.GraniteRed.getDust(1))
                .itemOutputs(Materials.GraniteRed.getPlates(1)).duration(15 * SECONDS).eut(2).addTo(sCompressorRecipes);

        GT_Values.RA.stdBuilder().itemInputs(Materials.GraniteBlack.getDust(1))
                .itemOutputs(Materials.GraniteBlack.getPlates(1)).duration(15 * SECONDS).eut(2)
                .addTo(sCompressorRecipes);

        GT_Values.RA.stdBuilder().itemInputs(Materials.Stone.getPlates(9)).itemOutputs(new ItemStack(Blocks.stone))
                .duration(15 * SECONDS).eut(2).addTo(sCompressorRecipes);

        GT_Values.RA.stdBuilder().itemInputs(Materials.GraniteRed.getPlates(9))
                .itemOutputs(new ItemStack(GregTech_API.sBlockGranites, 1, 8)).duration(15 * SECONDS).eut(2)
                .addTo(sCompressorRecipes);

        GT_Values.RA.stdBuilder().itemInputs(Materials.GraniteBlack.getPlates(9))
                .itemOutputs(new ItemStack(GregTech_API.sBlockGranites)).duration(15 * SECONDS).eut(2)
                .addTo(sCompressorRecipes);

        // honey related
        GT_Values.RA.stdBuilder().itemInputs(GT_Utility.getIntegratedCircuit(9))
                .itemOutputs(new ItemStack(Items.sugar, 9, 0))
                .fluidInputs(new FluidStack(FluidRegistry.getFluid("for.honey"), 1000)).duration(51 * SECONDS).eut(8)
                .addTo(sCentrifugeRecipes);

        if (Mods.BiomesOPlenty.isModLoaded()) {

            GT_Values.RA.stdBuilder().itemInputs(GT_Utility.getIntegratedCircuit(9))
                    .itemOutputs(new ItemStack(Items.sugar, 9, 0))
                    .fluidInputs(new FluidStack(FluidRegistry.getFluid("honey"), 1000)).duration(51 * SECONDS).eut(8)
                    .addTo(sCentrifugeRecipes);

            GT_Values.RA.stdBuilder().itemInputs(GT_Utility.getIntegratedCircuit(1))
                    .fluidInputs(new FluidStack(FluidRegistry.getFluid("honey"), 1000))
                    .fluidOutputs(new FluidStack(FluidRegistry.getFluid("for.honey"), 1000)).duration(51 * SECONDS)
                    .eut(8).addTo(sCentrifugeRecipes);

        }

        // Ethanol related

        for (int i = 0; i < CppPotions.textureNames.length; i++) {

            GT_Values.RA.stdBuilder().itemInputs(new ItemStack(CppItems.CppPotions, 1, i))
                    .itemOutputs(new ItemStack(Items.glass_bottle))
                    .fluidOutputs(
                            new FluidStack(
                                    FluidRegistry.getFluid(
                                            "potion." + CppPotions.textureNames[i].toLowerCase(Locale.ENGLISH)),
                                    250))
                    .duration(4 * TICKS).eut(1).addTo(sFluidCannerRecipes);

            GT_Values.RA.stdBuilder().itemInputs(new ItemStack(Items.glass_bottle))
                    .itemOutputs(new ItemStack(CppItems.CppPotions, 1, i))
                    .fluidInputs(
                            new FluidStack(
                                    FluidRegistry.getFluid(
                                            "potion." + CppPotions.textureNames[i].toLowerCase(Locale.ENGLISH)),
                                    250))
                    .duration(4 * TICKS).eut(1).addTo(sFluidCannerRecipes);

        }

        GT_Values.RA.stdBuilder().fluidInputs(new FluidStack(CppFluids.Mash, 10))
                .fluidOutputs(new FluidStack(CppFluids.Wash, 8)).duration(50 * SECONDS).eut(2)
                .addTo(sFermentingRecipes);

        GT_Values.RA.stdBuilder().fluidInputs(new FluidStack(CppFluids.Wash, 20))
                .fluidOutputs(new FluidStack(FluidRegistry.getFluid("potion.wine"), 8)).duration(50 * SECONDS).eut(2)
                .addTo(sFermentingRecipes);

        GT_Values.RA.stdBuilder().fluidInputs(new FluidStack(FluidRegistry.getFluid("potion.wheatyjuice"), 10))
                .fluidOutputs(new FluidStack(CppFluids.FWheat, 8)).duration(51 * SECONDS).eut(2)
                .addTo(sFermentingRecipes);

        GT_Values.RA.stdBuilder().fluidInputs(new FluidStack(FluidRegistry.getFluid("potion.reedwater"), 10))
                .fluidOutputs(new FluidStack(CppFluids.FReed, 8)).duration(51 * SECONDS).eut(2)
                .addTo(sFermentingRecipes);

        GT_Values.RA.stdBuilder().fluidInputs(new FluidStack(FluidRegistry.getFluid("potion.rum"), 1000))
                .fluidOutputs(Materials.Ethanol.getFluid(580), Materials.Water.getFluid(420)).duration(4 * SECONDS)
                .eut(180).addTo(sDistillationRecipes);

        GT_Values.RA.stdBuilder().fluidInputs(new FluidStack(FluidRegistry.getFluid("potion.piratebrew"), 1000))
                .fluidOutputs(Materials.Ethanol.getFluid(800), Materials.Water.getFluid(200)).duration(4 * SECONDS)
                .eut(180).addTo(sDistillationRecipes);

        GT_Values.RA.stdBuilder().fluidInputs(new FluidStack(FluidRegistry.getFluid("potion.beer"), 1000))
                .fluidOutputs(Materials.Ethanol.getFluid(45), Materials.Water.getFluid(955)).duration(4 * SECONDS)
                .eut(180).addTo(sDistillationRecipes);

        GT_Values.RA.stdBuilder().fluidInputs(new FluidStack(FluidRegistry.getFluid("potion.darkbeer"), 1000))
                .fluidOutputs(Materials.Ethanol.getFluid(75), Materials.Water.getFluid(925)).duration(4 * SECONDS)
                .eut(180).addTo(sDistillationRecipes);

        GT_Values.RA.stdBuilder().fluidInputs(new FluidStack(FluidRegistry.getFluid("potion.cider"), 1000))
                .fluidOutputs(Materials.Ethanol.getFluid(100), Materials.Water.getFluid(900)).duration(4 * SECONDS)
                .eut(180).addTo(sDistillationRecipes);

        GT_Values.RA.stdBuilder().fluidInputs(new FluidStack(FluidRegistry.getFluid("potion.wine"), 1000))
                .fluidOutputs(Materials.Ethanol.getFluid(120), Materials.Water.getFluid(880)).duration(4 * SECONDS)
                .eut(180).addTo(sDistillationRecipes);

        GT_Values.RA.stdBuilder().fluidInputs(new FluidStack(FluidRegistry.getFluid("potion.vodka"), 1000))
                .fluidOutputs(Materials.Ethanol.getFluid(400), Materials.Water.getFluid(600)).duration(4 * SECONDS)
                .eut(180).addTo(sDistillationRecipes);

        GT_Values.RA.stdBuilder().fluidInputs(new FluidStack(FluidRegistry.getFluid("potion.alcopops"), 1000))
                .fluidOutputs(Materials.Ethanol.getFluid(160), Materials.Water.getFluid(840)).duration(4 * SECONDS)
                .eut(180).addTo(sDistillationRecipes);

        GT_Values.RA.stdBuilder().fluidInputs(new FluidStack(CppFluids.Korn, 1000))
                .fluidOutputs(Materials.Ethanol.getFluid(320), Materials.Water.getFluid(680)).duration(4 * SECONDS)
                .eut(180).addTo(sDistillationRecipes);

        GT_Values.RA.stdBuilder().fluidInputs(new FluidStack(CppFluids.DKorn, 1000))
                .fluidOutputs(Materials.Ethanol.getFluid(380), Materials.Water.getFluid(620)).duration(4 * SECONDS)
                .eut(180).addTo(sDistillationRecipes);

        GT_Values.RA.stdBuilder().fluidInputs(new FluidStack(CppFluids.SWhine, 1000))
                .fluidOutputs(Materials.Ethanol.getFluid(700), Materials.Water.getFluid(300)).duration(4 * SECONDS)
                .eut(180).addTo(sDistillationRecipes);

        GT_Values.RA.stdBuilder().fluidInputs(new FluidStack(CppFluids.GHP, 1000))
                .fluidOutputs(Materials.Ethanol.getFluid(750), Materials.Water.getFluid(250)).duration(4 * SECONDS)
                .eut(180).addTo(sDistillationRecipes);

        GT_Values.RA.stdBuilder().fluidInputs(new FluidStack(CppFluids.jagi, 1000))
                .fluidOutputs(Materials.Ethanol.getFluid(350), Materials.Water.getFluid(650)).duration(4 * SECONDS)
                .eut(180).addTo(sDistillationRecipes);

        GT_Values.RA.stdBuilder().fluidInputs(new FluidStack(CppFluids.njagi, 1000))
                .fluidOutputs(Materials.Ethanol.getFluid(350), Materials.Water.getFluid(650)).duration(4 * SECONDS)
                .eut(180).addTo(sDistillationRecipes);

        GT_Values.RA.stdBuilder().itemInputs(GT_Utility.getIntegratedCircuit(1))
                .fluidInputs(new FluidStack(FluidRegistry.getFluid("potion.rum"), 100))
                .fluidOutputs(Materials.Ethanol.getFluid(50)).duration(16 * TICKS).eut(24).addTo(sDistilleryRecipes);

        GT_Values.RA.stdBuilder().itemInputs(GT_Utility.getIntegratedCircuit(2))
                .fluidInputs(new FluidStack(FluidRegistry.getFluid("potion.rum"), 100))
                .fluidOutputs(Materials.Water.getFluid(42)).duration(16 * TICKS).eut(24).addTo(sDistilleryRecipes);

        GT_Values.RA.stdBuilder().itemInputs(GT_Utility.getIntegratedCircuit(1))
                .fluidInputs(new FluidStack(FluidRegistry.getFluid("potion.vodka"), 100))
                .fluidOutputs(Materials.Ethanol.getFluid(35)).duration(16 * TICKS).eut(24).addTo(sDistilleryRecipes);

        GT_Values.RA.stdBuilder().itemInputs(GT_Utility.getIntegratedCircuit(2))
                .fluidInputs(new FluidStack(FluidRegistry.getFluid("potion.vodka"), 100))
                .fluidOutputs(Materials.Water.getFluid(60)).duration(16 * TICKS).eut(24).addTo(sDistilleryRecipes);

        GT_Values.RA.stdBuilder().itemInputs(GT_Utility.getIntegratedCircuit(1))
                .fluidInputs(new FluidStack(CppFluids.Korn, 100)).fluidOutputs(Materials.Ethanol.getFluid(25))
                .duration(16 * TICKS).eut(24).addTo(sDistilleryRecipes);

        GT_Values.RA.stdBuilder().itemInputs(GT_Utility.getIntegratedCircuit(2))
                .fluidInputs(new FluidStack(CppFluids.Korn, 100)).fluidOutputs(Materials.Water.getFluid(68))
                .duration(16 * TICKS).eut(24).addTo(sDistilleryRecipes);

        GT_Values.RA.stdBuilder().itemInputs(GT_Utility.getIntegratedCircuit(1))
                .fluidInputs(new FluidStack(CppFluids.DKorn, 100)).fluidOutputs(Materials.Ethanol.getFluid(30))
                .duration(16 * TICKS).eut(24).addTo(sDistilleryRecipes);

        GT_Values.RA.stdBuilder().itemInputs(GT_Utility.getIntegratedCircuit(2))
                .fluidInputs(new FluidStack(CppFluids.DKorn, 100)).fluidOutputs(Materials.Water.getFluid(62))
                .duration(16 * TICKS).eut(24).addTo(sDistilleryRecipes);

        GT_Values.RA.stdBuilder().itemInputs(GT_Utility.getIntegratedCircuit(1))
                .fluidInputs(new FluidStack(CppFluids.FWheat, 80)).fluidOutputs(new FluidStack(CppFluids.Korn, 1))
                .duration(1 * SECONDS + 2 * TICKS).eut(24).addTo(sDistilleryRecipes);

        GT_Values.RA.stdBuilder().itemInputs(GT_Utility.getIntegratedCircuit(2))
                .fluidInputs(new FluidStack(CppFluids.FWheat, 95)).fluidOutputs(new FluidStack(CppFluids.DKorn, 1))
                .duration(1 * SECONDS + 4 * TICKS).eut(24).addTo(sDistilleryRecipes);

        GT_Values.RA.stdBuilder().itemInputs(GT_Utility.getIntegratedCircuit(3))
                .fluidInputs(new FluidStack(CppFluids.FWheat, 100))
                .fluidOutputs(new FluidStack(FluidRegistry.getFluid("potion.vodka"), 1))
                .duration(1 * SECONDS + 8 * TICKS).eut(64).addTo(sDistilleryRecipes);

        GT_Values.RA.stdBuilder().itemInputs(GT_Utility.getIntegratedCircuit(4))
                .fluidInputs(new FluidStack(CppFluids.FWheat, 200))
                .fluidOutputs(new FluidStack(FluidRegistry.getFluid("fermentedbiomass"), 3))
                .duration(1 * SECONDS + 8 * TICKS).eut(64).addTo(sDistilleryRecipes);

        GT_Values.RA.stdBuilder().itemInputs(GT_Utility.getIntegratedCircuit(5))
                .fluidInputs(new FluidStack(CppFluids.FWheat, 1000)).fluidOutputs(Materials.Ethanol.getFluid(4))
                .duration(11 * SECONDS).eut(TierEU.RECIPE_MV).addTo(sDistilleryRecipes);

        GT_Values.RA.stdBuilder().itemInputs(GT_Utility.getIntegratedCircuit(1))
                .fluidInputs(new FluidStack(CppFluids.FReed, 100)).fluidOutputs(new FluidStack(CppFluids.SWhine, 7))
                .duration(1 * SECONDS + 2 * TICKS).eut(24).addTo(sDistilleryRecipes);

        GT_Values.RA.stdBuilder().itemInputs(GT_Utility.getIntegratedCircuit(4))
                .fluidInputs(new FluidStack(CppFluids.FReed, 200))
                .fluidOutputs(new FluidStack(FluidRegistry.getFluid("fermentedbiomass"), 4))
                .duration(1 * SECONDS + 4 * TICKS).eut(24).addTo(sDistilleryRecipes);

        GT_Values.RA.stdBuilder().itemInputs(GT_Utility.getIntegratedCircuit(5))
                .fluidInputs(new FluidStack(CppFluids.FReed, 1000)).fluidOutputs(Materials.Ethanol.getFluid(5))
                .duration(11 * SECONDS).eut(TierEU.RECIPE_MV).addTo(sDistilleryRecipes);

        GT_Values.RA.stdBuilder().itemInputs(GT_Utility.getIntegratedCircuit(4))
                .fluidInputs(new FluidStack(CppFluids.Mash, 200))
                .fluidOutputs(new FluidStack(FluidRegistry.getFluid("biomass"), 4)).duration(1 * SECONDS + 4 * TICKS)
                .eut(24).addTo(sDistilleryRecipes);

        GT_Values.RA.stdBuilder().itemInputs(GT_Utility.getIntegratedCircuit(1))
                .fluidInputs(new FluidStack(CppFluids.Wash, 100)).fluidOutputs(new FluidStack(CppFluids.GHP, 6))
                .duration(1 * SECONDS + 2 * TICKS).eut(24).addTo(sDistilleryRecipes);

        GT_Values.RA.stdBuilder().itemInputs(GT_Utility.getIntegratedCircuit(4))
                .fluidInputs(new FluidStack(CppFluids.Wash, 100))
                .fluidOutputs(new FluidStack(FluidRegistry.getFluid("fermentedbiomass"), 14))
                .duration(1 * SECONDS + 4 * TICKS).eut(24).addTo(sDistilleryRecipes);

        GT_Values.RA.stdBuilder()
                .itemInputs(
                        new ItemStack(Items.sugar, 32, 0),
                        new ItemStack(Items.dye, 4, 1),
                        new ItemStack(Items.dye, 4, 11),
                        new ItemStack(Items.dye, 4, 2),
                        Materials.Water.getCells(4))
                .itemOutputs(Materials.Empty.getCells(4)).fluidInputs(new FluidStack(CppFluids.GHP, 375))
                .fluidOutputs(new FluidStack(FluidRegistry.getFluid("potion.alcopops"), 4375)).duration(10 * TICKS)
                .eut(8).addTo(sMixerRecipes);

        GT_Values.RA.stdBuilder()
                .itemInputs(
                        new ItemStack(Items.sugar, 8, 0),
                        new ItemStack(Items.dye, 1, 1),
                        new ItemStack(Items.dye, 1, 11),
                        new ItemStack(Items.dye, 1, 2),
                        Materials.Water.getCells(1))
                .itemOutputs(Materials.Empty.getCells(1))
                .fluidInputs(new FluidStack(FluidRegistry.getFluid("potion.vodka"), 500))
                .fluidOutputs(new FluidStack(FluidRegistry.getFluid("potion.alcopops"), 1500)).duration(10 * TICKS)
                .eut(8).addTo(sMixerRecipes);

        GT_Values.RA.stdBuilder()
                .itemInputs(
                        new ItemStack(Items.sugar, 8, 0),
                        new ItemStack(Items.dye, 1, 1),
                        new ItemStack(Items.dye, 1, 11),
                        new ItemStack(Items.dye, 1, 2),
                        Materials.Water.getCells(1))
                .itemOutputs(Materials.Empty.getCells(1)).fluidInputs(new FluidStack(CppFluids.Korn, 1000))
                .fluidOutputs(new FluidStack(FluidRegistry.getFluid("potion.alcopops"), 2000)).duration(10 * TICKS)
                .eut(8).addTo(sMixerRecipes);

        GT_Values.RA.stdBuilder()
                .itemInputs(
                        new ItemStack(Items.sugar, 8, 0),
                        new ItemStack(Items.dye, 1, 1),
                        new ItemStack(Items.dye, 1, 11),
                        new ItemStack(Items.dye, 1, 2),
                        Materials.Water.getCells(1))
                .itemOutputs(Materials.Empty.getCells(1)).fluidInputs(new FluidStack(CppFluids.DKorn, 750))
                .fluidOutputs(new FluidStack(FluidRegistry.getFluid("potion.alcopops"), 1750)).duration(10 * TICKS)
                .eut(8).addTo(sMixerRecipes);

        GT_Values.RA.stdBuilder()
                .itemInputs(
                        new ItemStack(Items.sugar, 8, 0),
                        OreDict.ISget("cropSpiceleaf"),
                        OreDict.ISget("cropGinger"),
                        new ItemStack(Items.dye, 1, 2),
                        Materials.Water.getCells(1))
                .itemOutputs(Materials.Empty.getCells(1))
                .fluidInputs(new FluidStack(FluidRegistry.getFluid("potion.vodka"), 4000))
                .fluidOutputs(new FluidStack(CppFluids.njagi, 5000)).duration(10 * TICKS).eut(8).addTo(sMixerRecipes);

        GT_Values.RA.stdBuilder()
                .itemInputs(
                        ItemList.Crop_Drop_Chilly.get(1),
                        Materials.CosmicNeutronium.getDustTiny(1),
                        ItemList.Crop_Drop_Lemon.get(64),
                        ItemList.Crop_Drop_TeaLeaf.get(64),
                        CppItems.ModifierMagic.splitStack(8),
                        CppItems.ModifierSpace.splitStack(9))
                .fluidInputs(new FluidStack(CppFluids.njagi, 50000)).fluidOutputs(new FluidStack(CppFluids.jagi, 250))
                .duration(1 * TICKS).eut(TierEU.RECIPE_LuV).addTo(sMixerRecipes);

        GT_Values.RA.stdBuilder().itemInputs(Materials.Water.getCells(1)).itemOutputs(Materials.Empty.getCells(1))
                .fluidInputs(Materials.Ethanol.getFluid(1000))
                .fluidOutputs(new FluidStack(FluidRegistry.getFluid("potion.vodka"), 2500)).duration(10 * TICKS).eut(8)
                .addTo(sMixerRecipes);

        GT_Values.RA.stdBuilder().itemInputs(Materials.Water.getCells(1), new ItemStack(Items.sugar))
                .itemOutputs(Materials.Empty.getCells(1)).fluidInputs(new FluidStack(CppFluids.SWhine, 5000))
                .fluidOutputs(new FluidStack(FluidRegistry.getFluid("potion.rum"), 6000)).duration(10 * TICKS).eut(8)
                .addTo(sMixerRecipes);

        // Brewery
        if (OreDictionary.getOres("listAllberry").size() >= 1) {
            for (int i = 0; i < OreDictionary.getOres("listAllberry").size(); i++) {

                GT_Values.RA.stdBuilder().itemInputs(OreDictionary.getOres("listAllberry").get(i).splitStack(16))
                        .fluidInputs(Materials.Water.getFluid(750)).fluidOutputs(new FluidStack(CppFluids.Mash, 750))
                        .duration(6 * SECONDS + 8 * TICKS).eut(4).addTo(sBrewingRecipes);

            }
        }

        GT_Values.RA.stdBuilder().itemInputs(new ItemStack(Items.sugar, 8))
                .fluidInputs(new FluidStack(FluidRegistry.getFluid("potion.weakness"), 750))
                .fluidOutputs(new FluidStack(CppFluids.Mash, 750)).duration(6 * SECONDS + 8 * TICKS).eut(4)
                .addTo(sBrewingRecipes);

        // Sugar Related

        GT_Values.RA.stdBuilder().itemInputs(new ItemStack(CppItems.CppBerries, 1, 1))
                .itemOutputs(new ItemStack(Items.sugar, 8, 0)).duration(6 * SECONDS + 8 * TICKS).eut(4)
                .addTo(sExtractorRecipes);

        // Dyes from Plants
        for (int i = 0; i < OreDictionary.getOres("cropBlackberry").size(); i++) {

            GT_Values.RA.stdBuilder()
                    .itemInputs(
                            OreDictionary.getOres("cropBlackberry").get(i).splitStack(16),
                            Materials.Salt.getDust(2))
                    .itemOutputs(new ItemStack(Items.sugar)).fluidInputs(Materials.SulfuricAcid.getFluid(432))
                    .fluidOutputs(new FluidStack(FluidRegistry.getFluid("dye.chemical.dyeblack"), 288))
                    .duration(30 * SECONDS).eut(48).addTo(sChemicalRecipes);

        }
        for (int i = 0; i < OreDictionary.getOres("cropBlueberry").size(); i++) {

            GT_Values.RA.stdBuilder()
                    .itemInputs(OreDictionary.getOres("cropBlueberry").get(i).splitStack(16), Materials.Salt.getDust(2))
                    .itemOutputs(new ItemStack(Items.sugar)).fluidInputs(Materials.SulfuricAcid.getFluid(432))
                    .fluidOutputs(new FluidStack(FluidRegistry.getFluid("dye.chemical.dyeblue"), 288))
                    .duration(30 * SECONDS).eut(48).addTo(sChemicalRecipes);

        }
        for (int i = 0; i < OreDictionary.getOres("cropRaspberry").size(); i++) {

            GT_Values.RA.stdBuilder()
                    .itemInputs(OreDictionary.getOres("cropRaspberry").get(i).splitStack(16), Materials.Salt.getDust(2))
                    .itemOutputs(new ItemStack(Items.sugar)).fluidInputs(Materials.SulfuricAcid.getFluid(432))
                    .fluidOutputs(new FluidStack(FluidRegistry.getFluid("dye.chemical.dyepink"), 288))
                    .duration(30 * SECONDS).eut(48).addTo(sChemicalRecipes);
        }
        for (int i = 0; i < OreDictionary.getOres("cropVine").size(); i++) {
            if (!OreDictionary.getOres("cropVine").get(i).getUnlocalizedName().equals("tile.Thornvines")) {

                GT_Values.RA.stdBuilder()
                        .itemInputs(OreDictionary.getOres("cropVine").get(i).splitStack(16), Materials.Salt.getDust(2))
                        .fluidInputs(Materials.SulfuricAcid.getFluid(432))
                        .fluidOutputs(new FluidStack(FluidRegistry.getFluid("dye.chemical.dyegreen"), 288))
                        .duration(30 * SECONDS).eut(48).addTo(sChemicalRecipes);
            } else {

                GT_Values.RA.stdBuilder()
                        .itemInputs(OreDictionary.getOres("cropVine").get(i).splitStack(16), Materials.Salt.getDust(2))
                        .fluidInputs(Materials.SulfuricAcid.getFluid(432))
                        .fluidOutputs(new FluidStack(FluidRegistry.getFluid("dye.chemical.dyeyellow"), 288))
                        .duration(30 * SECONDS).eut(48).addTo(sChemicalRecipes);

            }
        }
        for (int i = 0; i < OreDictionary.getOres("cropCacti").size(); i++) {

            GT_Values.RA.stdBuilder()
                    .itemInputs(OreDictionary.getOres("cropCacti").get(i).splitStack(16), Materials.Salt.getDust(2))
                    .fluidInputs(Materials.SulfuricAcid.getFluid(432))
                    .fluidOutputs(new FluidStack(FluidRegistry.getFluid("dye.chemical.dyegreen"), 288))
                    .duration(30 * SECONDS).eut(48).addTo(sChemicalRecipes);

        }
        for (int i = 0; i < OreDictionary.getOres("cropGooseberry").size(); i++) {

            GT_Values.RA.stdBuilder()
                    .itemInputs(
                            OreDictionary.getOres("cropGooseberry").get(i).splitStack(16),
                            Materials.Salt.getDust(2))
                    .itemOutputs(new ItemStack(Items.sugar)).fluidInputs(Materials.SulfuricAcid.getFluid(432))
                    .fluidOutputs(new FluidStack(FluidRegistry.getFluid("dye.chemical.dyeyellow"), 288))
                    .duration(30 * SECONDS).eut(48).addTo(sChemicalRecipes);

        }
        for (int i = 0; i < OreDictionary.getOres("cropStrawberry").size(); i++) {

            GT_Values.RA.stdBuilder()
                    .itemInputs(
                            OreDictionary.getOres("cropStrawberry").get(i).splitStack(16),
                            Materials.Salt.getDust(2))
                    .itemOutputs(new ItemStack(Items.sugar)).fluidInputs(Materials.SulfuricAcid.getFluid(432))
                    .fluidOutputs(new FluidStack(FluidRegistry.getFluid("dye.chemical.dyered"), 288))
                    .duration(30 * SECONDS).eut(48).addTo(sChemicalRecipes);

        }

        if (Mods.BiomesOPlenty.isModLoaded()) {

            GT_Values.RA.stdBuilder()
                    .itemInputs(GT_ModHandler.getModItem("BiomesOPlenty", "food", 16), Materials.Salt.getDust(2))
                    .itemOutputs(new ItemStack(Items.sugar)).fluidInputs(Materials.SulfuricAcid.getFluid(432))
                    .fluidOutputs(new FluidStack(FluidRegistry.getFluid("dye.chemical.dyered"), 288))
                    .duration(30 * SECONDS).eut(48).addTo(sChemicalRecipes);

        }

        GT_Values.RA.stdBuilder().itemInputs(new ItemStack(CppItems.CppBerries, 16, 0), Materials.Salt.getDust(2))
                .itemOutputs(new ItemStack(Items.sugar)).fluidInputs(Materials.SulfuricAcid.getFluid(432))
                .fluidOutputs(new FluidStack(FluidRegistry.getFluid("dye.chemical.dyepurple"), 288))
                .duration(30 * SECONDS).eut(48).addTo(sChemicalRecipes);

        if (Mods.Natura.isModLoaded()) {

            GT_Values.RA.stdBuilder()
                    .itemInputs(GT_ModHandler.getModItem("Natura", "berry.nether", 16, 0), Materials.Salt.getDust(2))
                    .itemOutputs(new ItemStack(Items.sugar)).fluidInputs(Materials.SulfuricAcid.getFluid(432))
                    .fluidOutputs(new FluidStack(FluidRegistry.getFluid("dye.chemical.dyelime"), 288))
                    .duration(30 * SECONDS).eut(48).addTo(sChemicalRecipes);

            GT_Values.RA.stdBuilder()
                    .itemInputs(GT_ModHandler.getModItem("Natura", "berry.nether", 16, 1), Materials.Salt.getDust(2))
                    .itemOutputs(new ItemStack(Items.sugar)).fluidInputs(Materials.SulfuricAcid.getFluid(432))
                    .fluidOutputs(new FluidStack(FluidRegistry.getFluid("dye.chemical.dyelightgray"), 288))
                    .duration(30 * SECONDS).eut(48).addTo(sChemicalRecipes);

            GT_Values.RA.stdBuilder()
                    .itemInputs(GT_ModHandler.getModItem("Natura", "berry.nether", 16, 2), Materials.Salt.getDust(2))
                    .itemOutputs(new ItemStack(Items.sugar)).fluidInputs(Materials.SulfuricAcid.getFluid(432))
                    .fluidOutputs(new FluidStack(FluidRegistry.getFluid("dye.chemical.dyelightblue"), 288))
                    .duration(30 * SECONDS).eut(48).addTo(sChemicalRecipes);

            GT_Values.RA.stdBuilder()
                    .itemInputs(GT_ModHandler.getModItem("Natura", "berry.nether", 16, 3), Materials.Salt.getDust(2))
                    .itemOutputs(new ItemStack(Items.sugar)).fluidInputs(Materials.SulfuricAcid.getFluid(432))
                    .fluidOutputs(new FluidStack(FluidRegistry.getFluid("dye.chemical.dyelime"), 288))
                    .duration(30 * SECONDS).eut(48).addTo(sChemicalRecipes);

        }

        // Goldfish
        GT_Values.RA.stdBuilder().itemInputs(CppItems.GoldfischS).itemOutputs(new ItemStack(Items.gold_nugget))
                .outputChances(1).fluidOutputs(Materials.FishOil.getFluid(100)).duration(16 * TICKS).eut(8)
                .addTo(sFluidExtractionRecipes);

        GT_Values.RA.stdBuilder().itemInputs(CppItems.GoldfischS)
                .itemOutputs(Materials.MeatRaw.getDust(1), Materials.Gold.getDustTiny(1)).outputChances(10000, 100)
                .duration(3 * TICKS).eut(8).addTo(sMaceratorRecipes);

        // Space Modifier

        GT_Values.RA.stdBuilder().itemInputs(Materials.Iron.getDust(1), new ItemStack(CppItems.Modifier, 16, 0))
                .itemOutputs(Materials.MeteoricIron.getDust(1))
                .fluidInputs(
                        Materials.UUMatter
                                .getFluid(Materials.MeteoricIron.getNeutrons() + Materials.MeteoricIron.getProtons()))
                .duration(12 * SECONDS).eut(TierEU.RECIPE_HV).addTo(sMultiblockChemicalRecipes);

        GT_Values.RA.stdBuilder().itemInputs(Materials.Steel.getDust(1), new ItemStack(CppItems.Modifier, 16, 0))
                .itemOutputs(Materials.MeteoricSteel.getDust(1))
                .fluidInputs(
                        Materials.UUMatter
                                .getFluid(Materials.MeteoricSteel.getNeutrons() + Materials.MeteoricSteel.getProtons()))
                .duration(12 * SECONDS).eut(TierEU.RECIPE_HV).addTo(sMultiblockChemicalRecipes);

        GT_Values.RA.stdBuilder().itemInputs(Materials.Bismuth.getDust(1), new ItemStack(CppItems.Modifier, 16, 0))
                .itemOutputs(Materials.Oriharukon.getDust(1))
                .fluidInputs(
                        Materials.UUMatter
                                .getFluid(Materials.Oriharukon.getNeutrons() + Materials.Oriharukon.getProtons()))
                .duration(12 * SECONDS).eut(TierEU.RECIPE_HV).addTo(sMultiblockChemicalRecipes);

        GT_Values.RA.stdBuilder().itemInputs(Materials.Titanium.getDust(1), new ItemStack(CppItems.Modifier, 16, 0))
                .itemOutputs(Materials.Desh.getDust(1))
                .fluidInputs(Materials.UUMatter.getFluid(Materials.Desh.getNeutrons() + Materials.Desh.getProtons()))
                .duration(12 * SECONDS).eut(TierEU.RECIPE_HV).addTo(sMultiblockChemicalRecipes);
        // Non-elements

        GT_Values.RA.stdBuilder().itemInputs(Materials.Ice.getDust(1), new ItemStack(CppItems.Modifier, 16, 0))
                .itemOutputs(Materials.CallistoIce.getDust(1))
                .fluidInputs(
                        Materials.UUMatter
                                .getFluid((Materials.Water.getProtons() + Materials.Water.getNeutrons()) * 10))
                .duration(12 * SECONDS).eut(TierEU.RECIPE_IV).addTo(sMultiblockChemicalRecipes);

        GT_Values.RA.stdBuilder().itemInputs(Materials.Lead.getDust(1), new ItemStack(CppItems.Modifier, 16, 0))
                .itemOutputs(Materials.Ledox.getDust(1))
                .fluidInputs(
                        Materials.UUMatter
                                .getFluid((Materials.Water.getProtons() + Materials.Water.getNeutrons()) * 10))
                .duration(12 * SECONDS).eut(TierEU.RECIPE_IV).addTo(sMultiblockChemicalRecipes);

        if (Mods.Avaritia.isModLoaded()) {

            GT_Values.RA.stdBuilder()
                    .itemInputs(
                            GT_ModHandler.getModItem("Avaritia", "Resource", 1, 1),
                            new ItemStack(CppItems.Modifier, 32, 0))
                    .itemOutputs(Materials.MysteriousCrystal.getDust(1)).fluidInputs(Materials.UUMatter.getFluid(100))
                    .duration(12 * SECONDS).eut(TierEU.RECIPE_ZPM).addTo(sMultiblockChemicalRecipes);

        }

        GT_Values.RA.stdBuilder().itemInputs(Materials.MeteoricIron.getDust(1), new ItemStack(CppItems.Modifier, 64, 0))
                .itemOutputs(Materials.DeepIron.getDust(1))
                .fluidInputs(
                        Materials.UUMatter
                                .getFluid((Materials.Neutronium.getNeutrons() + Materials.Neutronium.getProtons())))
                .duration(6 * SECONDS).eut(TierEU.RECIPE_ZPM).addTo(sMultiblockChemicalRecipes);

        GT_Values.RA.stdBuilder().itemInputs(Materials.Plutonium241.getDust(1), new ItemStack(CppItems.Modifier, 64, 0))
                .itemOutputs(Materials.BlackPlutonium.getDust(1))
                .fluidInputs(
                        Materials.UUMatter
                                .getFluid((Materials.Neutronium.getNeutrons() + Materials.Neutronium.getProtons())))
                .duration(6 * SECONDS).eut(500000).addTo(sMultiblockChemicalRecipes);

        if (Mods.Thaumcraft.isModLoaded()) {
            // Magic Modifier PrimP

            GT_Values.RA.stdBuilder().itemInputs(GT_ModHandler.getModItem("Thaumcraft", "ItemEldritchObject", 1, 3))
                    .itemOutputs(new ItemStack(CppItems.Modifier, 8, 1)).duration(6 * SECONDS + 8 * TICKS).eut(4)
                    .addTo(sExtractorRecipes);

            GT_Values.RA.stdBuilder().itemInputs(new ItemStack(CppItems.Modifier, 1, 1))
                    .itemOutputs(GT_ModHandler.getModItem("Thaumcraft", "ItemResource", 16, 14))
                    .duration(6 * SECONDS + 8 * TICKS).eut(4).addTo(sExtractorRecipes);

            GT_Values.RA.stdBuilder().itemInputs(new ItemStack(CppItems.Modifier, 16, 1))
                    .itemOutputs(com.dreammaster.item.ItemList.PrimordialPearlFragment.getIS().splitStack(3))
                    .fluidInputs(Materials.UUMatter.getFluid(52)).duration(20 * MINUTES).eut(384)
                    .addTo(sAutoclaveRecipes);

            GT_Values.RA.stdBuilder().itemInputs(GT_ModHandler.getModItem("Thaumcraft", "ItemResource", 32, 14))
                    .itemOutputs(new ItemStack(CppItems.Modifier, 1, 1)).fluidInputs(Materials.UUMatter.getFluid(500))
                    .duration(2 * MINUTES).eut(TierEU.RECIPE_MV).addTo(sAutoclaveRecipes);

            // Magic Modifier

            GT_Values.RA.stdBuilder().itemInputs(Materials.Iron.getDust(1), new ItemStack(CppItems.Modifier, 1, 1))
                    .itemOutputs(Materials.Thaumium.getDust(1))
                    .fluidInputs(
                            Materials.UUMatter
                                    .getFluid(Materials.Thaumium.getNeutrons() + Materials.Thaumium.getProtons()))
                    .duration(12 * SECONDS).eut(TierEU.RECIPE_MV).addTo(sMultiblockChemicalRecipes);

            GT_Values.RA.stdBuilder().itemInputs(Materials.Thaumium.getDust(1), new ItemStack(CppItems.Modifier, 1, 1))
                    .itemOutputs(Materials.Void.getDust(1))
                    .fluidInputs(
                            Materials.UUMatter
                                    .getFluid(Materials.Arsenic.getNeutrons() + Materials.Arsenic.getProtons()))
                    .duration(12 * SECONDS).eut(TierEU.RECIPE_HV).addTo(sMultiblockChemicalRecipes);

            GT_Values.RA.stdBuilder().itemInputs(Materials.Void.getDust(1), new ItemStack(CppItems.Modifier, 2, 1))
                    .itemOutputs(Materials.Shadow.getDust(1))
                    .fluidInputs(
                            Materials.UUMatter.getFluid(Materials.Indium.getNeutrons() + Materials.Indium.getProtons()))
                    .duration(12 * SECONDS).eut(TierEU.RECIPE_IV).addTo(sMultiblockChemicalRecipes);

            GT_Values.RA.stdBuilder().itemInputs(Materials.Shadow.getDust(1), new ItemStack(CppItems.Modifier, 16, 1))
                    .itemOutputs(Materials.Ichorium.getIngots(1))
                    .fluidInputs(
                            Materials.UUMatter
                                    .getFluid((Materials.Osmium.getNeutrons() + Materials.Osmium.getProtons()) * 1000))
                    .duration(1 * MINUTES + 30 * SECONDS).eut(TierEU.RECIPE_LuV).addTo(sMultiblockChemicalRecipes);

        }

        // Magic Modifier + Space Modifier

        GT_Values.RA.stdBuilder()
                .itemInputs(new ItemStack(CppItems.Modifier, 16, 0), new ItemStack(CppItems.Modifier, 4, 1))
                .itemOutputs(Materials.Mytryl.getDust(1))
                .fluidInputs(
                        Materials.Platinum.getMolten(288),
                        Materials.MeteoricIron.getMolten(144),
                        Materials.UUMatter.getFluid(Materials.Platinum.getProtons() + Materials.Platinum.getNeutrons()))
                .duration(1 * MINUTES + 30 * SECONDS).eut(TierEU.RECIPE_IV).addTo(sMultiblockChemicalRecipes);

        // coral buff
        GT_Values.RA.stdBuilder().itemInputs(GT_ModHandler.getModItem("BiomesOPlenty", "coral1", 64, 15))
                .itemOutputs(Materials.Sunnarium.getDust(4)).fluidInputs(Materials.UUMatter.getFluid(2))
                .duration(12 * MINUTES + 30 * SECONDS).eut(TierEU.RECIPE_IV).requiresCleanRoom()
                .addTo(sAutoclaveRecipes);

        if (Mods.BiomesOPlenty.isModLoaded()) {
            GT_Values.RA.stdBuilder().itemInputs(GT_ModHandler.getModItem("BiomesOPlenty", "treeMoss", 8))
                    .itemOutputs(Ic2Items.plantBall.copy()).duration(15 * SECONDS).eut(2).addTo(sCompressorRecipes);
        }

        // Not a noob trophy.
        GT_Values.RA.stdBuilder().itemInputs(Materials.Neutronium.getBlocks(64), Materials.Neutronium.getBlocks(64))
                .itemOutputs(CppItems.Trophy).duration(29826 * HOURS + 9 * MINUTES + 42 * SECONDS + 7 * TICKS).eut(8)
                .addTo(sExtruderRecipes);

        GT_Values.RA.stdBuilder().itemInputs(new ItemStack(CppItems.Modifier, 1, 0)).outputChances(5000)
                .fluidOutputs(Materials.UUMatter.getFluid(2)).duration(6 * SECONDS + 8 * TICKS).eut(4)
                .addTo(sFluidExtractionRecipes);

        // Chem Refine

        GT_Values.RA.stdBuilder()
                .itemInputs(
                        new ItemStack(CppItems.Modifier, 9, 0),
                        GT_ModHandler.getModItem("GalacticraftCore", "item.meteoricIronRaw", 1, 0))
                .itemOutputs(Materials.MeteoricIron.getDust(4)).fluidInputs(Materials.Water.getFluid(1000))
                .duration(12 * SECONDS).eut(TierEU.RECIPE_HV).addTo(sMultiblockChemicalRecipes);

        GT_Values.RA.stdBuilder().itemOutputs(Materials.Desh.getDust(4)).fluidInputs(Materials.Water.getFluid(1000))
                .duration(12 * SECONDS).eut(TierEU.RECIPE_HV).addTo(sMultiblockChemicalRecipes);

        GT_Values.RA.stdBuilder()
                .itemInputs(
                        new ItemStack(CppItems.Modifier, 9, 0),
                        GT_OreDictUnificator.get(OrePrefixes.crushed.get(Materials.MeteoricIron), 1))
                .itemOutputs(GT_OreDictUnificator.get(OrePrefixes.crushedPurified.get(Materials.MeteoricIron), 4))
                .fluidInputs(Materials.Water.getFluid(1000)).duration(12 * SECONDS).eut(TierEU.RECIPE_HV)
                .addTo(sMultiblockChemicalRecipes);

        GT_Values.RA.stdBuilder()
                .itemInputs(
                        new ItemStack(CppItems.Modifier, 9, 0),
                        GT_OreDictUnificator.get(OrePrefixes.crushed.get(Materials.Desh), 1))
                .itemOutputs(GT_OreDictUnificator.get(OrePrefixes.crushedPurified.get(Materials.Desh), 4))
                .fluidInputs(Materials.Water.getFluid(1000)).duration(12 * SECONDS).eut(TierEU.RECIPE_HV)
                .addTo(sMultiblockChemicalRecipes);

        GT_Values.RA.stdBuilder()
                .itemInputs(
                        new ItemStack(CppItems.Modifier, 9, 0),
                        GT_OreDictUnificator.get(OrePrefixes.crushed.get(Materials.Oriharukon), 1))
                .itemOutputs(GT_OreDictUnificator.get(OrePrefixes.crushedPurified.get(Materials.Oriharukon), 4))
                .fluidInputs(Materials.Water.getFluid(1000)).duration(12 * SECONDS).eut(TierEU.RECIPE_HV)
                .addTo(sMultiblockChemicalRecipes);

        GT_Values.RA.stdBuilder()
                .itemInputs(
                        new ItemStack(CppItems.Modifier, 9, 0),
                        GT_OreDictUnificator.get(OrePrefixes.crushed.get(Materials.Ledox), 1))
                .itemOutputs(GT_OreDictUnificator.get(OrePrefixes.crushedPurified.get(Materials.Ledox), 4))
                .fluidInputs(Materials.Water.getFluid(1000)).duration(12 * SECONDS).eut(TierEU.RECIPE_HV)
                .addTo(sMultiblockChemicalRecipes);

        GT_Values.RA.stdBuilder()
                .itemInputs(
                        new ItemStack(CppItems.Modifier, 9, 0),
                        GT_OreDictUnificator.get(OrePrefixes.crushed.get(Materials.CallistoIce), 1))
                .itemOutputs(GT_OreDictUnificator.get(OrePrefixes.crushedPurified.get(Materials.CallistoIce), 4))
                .fluidInputs(Materials.Water.getFluid(1000)).duration(12 * SECONDS).eut(TierEU.RECIPE_HV)
                .addTo(sMultiblockChemicalRecipes);

        GT_Values.RA.stdBuilder()
                .itemInputs(
                        new ItemStack(CppItems.Modifier, 9, 0),
                        GT_OreDictUnificator.get(OrePrefixes.crushed.get(Materials.BlackPlutonium), 1))
                .itemOutputs(GT_OreDictUnificator.get(OrePrefixes.crushedPurified.get(Materials.BlackPlutonium), 4))
                .fluidInputs(Materials.Water.getFluid(1000)).duration(12 * SECONDS).eut(TierEU.RECIPE_LuV)
                .addTo(sMultiblockChemicalRecipes);

        GT_Values.RA.stdBuilder()
                .itemInputs(
                        new ItemStack(CppItems.Modifier, 9, 0),
                        GT_OreDictUnificator.get(OrePrefixes.crushed.get(Materials.DeepIron), 1))
                .itemOutputs(GT_OreDictUnificator.get(OrePrefixes.crushedPurified.get(Materials.DeepIron), 4))
                .fluidInputs(Materials.Water.getFluid(1000)).duration(12 * SECONDS).eut(TierEU.RECIPE_LuV)
                .addTo(sMultiblockChemicalRecipes);

        // Potions from Netherberries

        GT_Values.RA.stdBuilder().itemInputs(GT_ModHandler.getModItem("Natura", "berry.nether", 16, 0))
                .fluidInputs(Materials.Water.getFluid(750))
                .fluidOutputs(new FluidStack(FluidRegistry.getFluid("potion.regen"), 750))
                .duration(6 * SECONDS + 8 * TICKS).eut(4).addTo(sBrewingRecipes);

        GT_Values.RA.stdBuilder().itemInputs(GT_ModHandler.getModItem("Natura", "berry.nether", 16, 1))
                .fluidInputs(Materials.Water.getFluid(750))
                .fluidOutputs(new FluidStack(FluidRegistry.getFluid("potion.nightvision"), 750))
                .duration(6 * SECONDS + 8 * TICKS).eut(4).addTo(sBrewingRecipes);

        GT_Values.RA.stdBuilder().itemInputs(GT_ModHandler.getModItem("Natura", "berry.nether", 16, 2))
                .fluidInputs(Materials.Water.getFluid(750))
                .fluidOutputs(new FluidStack(FluidRegistry.getFluid("potion.speed"), 750))
                .duration(6 * SECONDS + 8 * TICKS).eut(4).addTo(sBrewingRecipes);

        GT_Values.RA.stdBuilder().itemInputs(GT_ModHandler.getModItem("Natura", "berry.nether", 16, 3))
                .fluidInputs(Materials.Water.getFluid(750))
                .fluidOutputs(new FluidStack(FluidRegistry.getFluid("potion.strength"), 750))
                .duration(6 * SECONDS + 8 * TICKS).eut(4).addTo(sBrewingRecipes);

    }
}
