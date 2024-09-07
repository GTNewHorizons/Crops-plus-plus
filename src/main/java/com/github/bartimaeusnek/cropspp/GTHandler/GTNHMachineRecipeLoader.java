package com.github.bartimaeusnek.cropspp.GTHandler;

import static gregtech.api.enums.Mods.Avaritia;
import static gregtech.api.enums.Mods.BiomesOPlenty;
import static gregtech.api.enums.Mods.GalacticraftCore;
import static gregtech.api.enums.Mods.Natura;
import static gregtech.api.enums.Mods.Thaumcraft;
import static gregtech.api.recipe.RecipeMaps.brewingRecipes;
import static gregtech.api.recipe.RecipeMaps.centrifugeRecipes;
import static gregtech.api.recipe.RecipeMaps.compressorRecipes;
import static gregtech.api.recipe.RecipeMaps.distillationTowerRecipes;
import static gregtech.api.recipe.RecipeMaps.distilleryRecipes;
import static gregtech.api.recipe.RecipeMaps.extractorRecipes;
import static gregtech.api.recipe.RecipeMaps.extruderRecipes;
import static gregtech.api.recipe.RecipeMaps.fermentingRecipes;
import static gregtech.api.recipe.RecipeMaps.fluidCannerRecipes;
import static gregtech.api.recipe.RecipeMaps.fluidExtractionRecipes;
import static gregtech.api.recipe.RecipeMaps.maceratorRecipes;
import static gregtech.api.recipe.RecipeMaps.mixerRecipes;
import static gregtech.api.recipe.RecipeMaps.multiblockChemicalReactorRecipes;
import static gregtech.api.util.GTRecipeBuilder.HOURS;
import static gregtech.api.util.GTRecipeBuilder.MINUTES;
import static gregtech.api.util.GTRecipeBuilder.SECONDS;
import static gregtech.api.util.GTRecipeBuilder.TICKS;
import static gregtech.api.util.GTRecipeConstants.UniversalChemical;

import java.util.Locale;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.oredict.OreDictionary;

import com.github.bartimaeusnek.croploadcore.OreDict;
import com.github.bartimaeusnek.cropspp.fluids.CppFluids;
import com.github.bartimaeusnek.cropspp.items.CppItems;
import com.github.bartimaeusnek.cropspp.items.CppPotions;

import gregtech.api.GregTechAPI;
import gregtech.api.enums.GTValues;
import gregtech.api.enums.ItemList;
import gregtech.api.enums.Materials;
import gregtech.api.enums.Mods;
import gregtech.api.enums.OrePrefixes;
import gregtech.api.enums.TierEU;
import gregtech.api.recipe.RecipeMaps;
import gregtech.api.util.GTModHandler;
import gregtech.api.util.GTOreDictUnificator;
import gregtech.api.util.GTUtility;
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
        GTValues.RA.stdBuilder().itemInputs(Materials.Marble.getDust(9)).itemOutputs(Materials.Marble.getBlocks(1))
                .duration(15 * SECONDS).eut(2).addTo(compressorRecipes);

        GTValues.RA.stdBuilder().itemInputs(Materials.GraniteRed.getDust(1))
                .itemOutputs(Materials.GraniteRed.getPlates(1)).duration(15 * SECONDS).eut(2).addTo(compressorRecipes);

        GTValues.RA.stdBuilder().itemInputs(Materials.GraniteBlack.getDust(1))
                .itemOutputs(Materials.GraniteBlack.getPlates(1)).duration(15 * SECONDS).eut(2)
                .addTo(compressorRecipes);

        GTValues.RA.stdBuilder().itemInputs(Materials.Stone.getPlates(9)).itemOutputs(new ItemStack(Blocks.stone))
                .duration(15 * SECONDS).eut(2).addTo(compressorRecipes);

        GTValues.RA.stdBuilder().itemInputs(Materials.GraniteRed.getPlates(9))
                .itemOutputs(new ItemStack(GregTechAPI.sBlockGranites, 1, 8)).duration(15 * SECONDS).eut(2)
                .addTo(compressorRecipes);

        GTValues.RA.stdBuilder().itemInputs(Materials.GraniteBlack.getPlates(9))
                .itemOutputs(new ItemStack(GregTechAPI.sBlockGranites)).duration(15 * SECONDS).eut(2)
                .addTo(compressorRecipes);

        // honey related
        GTValues.RA.stdBuilder().itemInputs(GTUtility.getIntegratedCircuit(9))
                .itemOutputs(new ItemStack(Items.sugar, 9, 0))
                .fluidInputs(new FluidStack(FluidRegistry.getFluid("for.honey"), 1000)).duration(51 * SECONDS).eut(8)
                .addTo(centrifugeRecipes);

        if (BiomesOPlenty.isModLoaded()) {

            GTValues.RA.stdBuilder().itemInputs(GTUtility.getIntegratedCircuit(9))
                    .itemOutputs(new ItemStack(Items.sugar, 9, 0))
                    .fluidInputs(new FluidStack(FluidRegistry.getFluid("honey"), 1000)).duration(51 * SECONDS).eut(8)
                    .addTo(centrifugeRecipes);

            GTValues.RA.stdBuilder().itemInputs(GTUtility.getIntegratedCircuit(1))
                    .fluidInputs(new FluidStack(FluidRegistry.getFluid("honey"), 1000))
                    .fluidOutputs(new FluidStack(FluidRegistry.getFluid("for.honey"), 1000)).duration(51 * SECONDS)
                    .eut(8).addTo(centrifugeRecipes);

        }

        // Ethanol related

        for (int i = 0; i < CppPotions.textureNames.length; i++) {

            Fluid potion = FluidRegistry.getFluid("potion." + CppPotions.textureNames[i].toLowerCase(Locale.ENGLISH));

            if (potion == null) continue;

            GTValues.RA.stdBuilder().itemInputs(new ItemStack(CppItems.CppPotions, 1, i))
                    .itemOutputs(new ItemStack(Items.glass_bottle)).fluidOutputs(new FluidStack(potion, 250))
                    .duration(4 * TICKS).eut(1).addTo(fluidCannerRecipes);

            GTValues.RA.stdBuilder().itemInputs(new ItemStack(Items.glass_bottle))
                    .itemOutputs(new ItemStack(CppItems.CppPotions, 1, i)).fluidInputs(new FluidStack(potion, 250))
                    .duration(4 * TICKS).eut(1).addTo(fluidCannerRecipes);

        }

        GTValues.RA.stdBuilder().fluidInputs(new FluidStack(CppFluids.Mash, 10))
                .fluidOutputs(new FluidStack(CppFluids.Wash, 8)).duration(50 * SECONDS).eut(2).addTo(fermentingRecipes);

        GTValues.RA.stdBuilder().fluidInputs(new FluidStack(CppFluids.Wash, 20))
                .fluidOutputs(new FluidStack(FluidRegistry.getFluid("potion.wine"), 8)).duration(50 * SECONDS).eut(2)
                .addTo(fermentingRecipes);

        GTValues.RA.stdBuilder().fluidInputs(new FluidStack(FluidRegistry.getFluid("potion.wheatyjuice"), 10))
                .fluidOutputs(new FluidStack(CppFluids.FWheat, 8)).duration(51 * SECONDS).eut(2)
                .addTo(fermentingRecipes);

        GTValues.RA.stdBuilder().fluidInputs(new FluidStack(FluidRegistry.getFluid("potion.reedwater"), 10))
                .fluidOutputs(new FluidStack(CppFluids.FReed, 8)).duration(51 * SECONDS).eut(2)
                .addTo(fermentingRecipes);

        GTValues.RA.stdBuilder().fluidInputs(new FluidStack(FluidRegistry.getFluid("potion.rum"), 1000))
                .fluidOutputs(Materials.Ethanol.getFluid(580), Materials.Water.getFluid(420)).duration(4 * SECONDS)
                .eut(180).addTo(distillationTowerRecipes);

        GTValues.RA.stdBuilder().fluidInputs(new FluidStack(FluidRegistry.getFluid("potion.piratebrew"), 1000))
                .fluidOutputs(Materials.Ethanol.getFluid(800), Materials.Water.getFluid(200)).duration(4 * SECONDS)
                .eut(180).addTo(distillationTowerRecipes);

        GTValues.RA.stdBuilder().fluidInputs(new FluidStack(FluidRegistry.getFluid("potion.beer"), 1000))
                .fluidOutputs(Materials.Ethanol.getFluid(45), Materials.Water.getFluid(955)).duration(4 * SECONDS)
                .eut(180).addTo(distillationTowerRecipes);

        GTValues.RA.stdBuilder().fluidInputs(new FluidStack(FluidRegistry.getFluid("potion.darkbeer"), 1000))
                .fluidOutputs(Materials.Ethanol.getFluid(75), Materials.Water.getFluid(925)).duration(4 * SECONDS)
                .eut(180).addTo(distillationTowerRecipes);

        GTValues.RA.stdBuilder().fluidInputs(new FluidStack(FluidRegistry.getFluid("potion.cider"), 1000))
                .fluidOutputs(Materials.Ethanol.getFluid(100), Materials.Water.getFluid(900)).duration(4 * SECONDS)
                .eut(180).addTo(distillationTowerRecipes);

        GTValues.RA.stdBuilder().fluidInputs(new FluidStack(FluidRegistry.getFluid("potion.wine"), 1000))
                .fluidOutputs(Materials.Ethanol.getFluid(120), Materials.Water.getFluid(880)).duration(4 * SECONDS)
                .eut(180).addTo(distillationTowerRecipes);

        GTValues.RA.stdBuilder().fluidInputs(new FluidStack(FluidRegistry.getFluid("potion.vodka"), 1000))
                .fluidOutputs(Materials.Ethanol.getFluid(400), Materials.Water.getFluid(600)).duration(4 * SECONDS)
                .eut(180).addTo(distillationTowerRecipes);

        GTValues.RA.stdBuilder().fluidInputs(new FluidStack(FluidRegistry.getFluid("potion.alcopops"), 1000))
                .fluidOutputs(Materials.Ethanol.getFluid(160), Materials.Water.getFluid(840)).duration(4 * SECONDS)
                .eut(180).addTo(distillationTowerRecipes);

        GTValues.RA.stdBuilder().fluidInputs(new FluidStack(CppFluids.Korn, 1000))
                .fluidOutputs(Materials.Ethanol.getFluid(320), Materials.Water.getFluid(680)).duration(4 * SECONDS)
                .eut(180).addTo(distillationTowerRecipes);

        GTValues.RA.stdBuilder().fluidInputs(new FluidStack(CppFluids.DKorn, 1000))
                .fluidOutputs(Materials.Ethanol.getFluid(380), Materials.Water.getFluid(620)).duration(4 * SECONDS)
                .eut(180).addTo(distillationTowerRecipes);

        GTValues.RA.stdBuilder().fluidInputs(new FluidStack(CppFluids.SWhine, 1000))
                .fluidOutputs(Materials.Ethanol.getFluid(700), Materials.Water.getFluid(300)).duration(4 * SECONDS)
                .eut(180).addTo(distillationTowerRecipes);

        GTValues.RA.stdBuilder().fluidInputs(new FluidStack(CppFluids.GHP, 1000))
                .fluidOutputs(Materials.Ethanol.getFluid(750), Materials.Water.getFluid(250)).duration(4 * SECONDS)
                .eut(180).addTo(distillationTowerRecipes);

        GTValues.RA.stdBuilder().fluidInputs(new FluidStack(CppFluids.jagi, 1000))
                .fluidOutputs(Materials.Ethanol.getFluid(350), Materials.Water.getFluid(650)).duration(4 * SECONDS)
                .eut(180).addTo(distillationTowerRecipes);

        GTValues.RA.stdBuilder().fluidInputs(new FluidStack(CppFluids.njagi, 1000))
                .fluidOutputs(Materials.Ethanol.getFluid(350), Materials.Water.getFluid(650)).duration(4 * SECONDS)
                .eut(180).addTo(distillationTowerRecipes);

        GTValues.RA.stdBuilder().itemInputs(GTUtility.getIntegratedCircuit(1))
                .fluidInputs(new FluidStack(FluidRegistry.getFluid("potion.rum"), 100))
                .fluidOutputs(Materials.Ethanol.getFluid(50)).duration(16 * TICKS).eut(24).addTo(distilleryRecipes);

        GTValues.RA.stdBuilder().itemInputs(GTUtility.getIntegratedCircuit(2))
                .fluidInputs(new FluidStack(FluidRegistry.getFluid("potion.rum"), 100))
                .fluidOutputs(Materials.Water.getFluid(42)).duration(16 * TICKS).eut(24).addTo(distilleryRecipes);

        GTValues.RA.stdBuilder().itemInputs(GTUtility.getIntegratedCircuit(1))
                .fluidInputs(new FluidStack(FluidRegistry.getFluid("potion.vodka"), 100))
                .fluidOutputs(Materials.Ethanol.getFluid(35)).duration(16 * TICKS).eut(24).addTo(distilleryRecipes);

        GTValues.RA.stdBuilder().itemInputs(GTUtility.getIntegratedCircuit(2))
                .fluidInputs(new FluidStack(FluidRegistry.getFluid("potion.vodka"), 100))
                .fluidOutputs(Materials.Water.getFluid(60)).duration(16 * TICKS).eut(24).addTo(distilleryRecipes);

        GTValues.RA.stdBuilder().itemInputs(GTUtility.getIntegratedCircuit(1))
                .fluidInputs(new FluidStack(CppFluids.Korn, 100)).fluidOutputs(Materials.Ethanol.getFluid(25))
                .duration(16 * TICKS).eut(24).addTo(distilleryRecipes);

        GTValues.RA.stdBuilder().itemInputs(GTUtility.getIntegratedCircuit(2))
                .fluidInputs(new FluidStack(CppFluids.Korn, 100)).fluidOutputs(Materials.Water.getFluid(68))
                .duration(16 * TICKS).eut(24).addTo(distilleryRecipes);

        GTValues.RA.stdBuilder().itemInputs(GTUtility.getIntegratedCircuit(1))
                .fluidInputs(new FluidStack(CppFluids.DKorn, 100)).fluidOutputs(Materials.Ethanol.getFluid(30))
                .duration(16 * TICKS).eut(24).addTo(distilleryRecipes);

        GTValues.RA.stdBuilder().itemInputs(GTUtility.getIntegratedCircuit(2))
                .fluidInputs(new FluidStack(CppFluids.DKorn, 100)).fluidOutputs(Materials.Water.getFluid(62))
                .duration(16 * TICKS).eut(24).addTo(distilleryRecipes);

        GTValues.RA.stdBuilder().itemInputs(GTUtility.getIntegratedCircuit(1))
                .fluidInputs(new FluidStack(CppFluids.FWheat, 80)).fluidOutputs(new FluidStack(CppFluids.Korn, 1))
                .duration(1 * SECONDS + 2 * TICKS).eut(24).addTo(distilleryRecipes);

        GTValues.RA.stdBuilder().itemInputs(GTUtility.getIntegratedCircuit(2))
                .fluidInputs(new FluidStack(CppFluids.FWheat, 95)).fluidOutputs(new FluidStack(CppFluids.DKorn, 1))
                .duration(1 * SECONDS + 4 * TICKS).eut(24).addTo(distilleryRecipes);

        GTValues.RA.stdBuilder().itemInputs(GTUtility.getIntegratedCircuit(3))
                .fluidInputs(new FluidStack(CppFluids.FWheat, 100))
                .fluidOutputs(new FluidStack(FluidRegistry.getFluid("potion.vodka"), 1))
                .duration(1 * SECONDS + 8 * TICKS).eut(64).addTo(distilleryRecipes);

        GTValues.RA.stdBuilder().itemInputs(GTUtility.getIntegratedCircuit(4))
                .fluidInputs(new FluidStack(CppFluids.FWheat, 200))
                .fluidOutputs(new FluidStack(FluidRegistry.getFluid("fermentedbiomass"), 3))
                .duration(1 * SECONDS + 8 * TICKS).eut(64).addTo(distilleryRecipes);

        GTValues.RA.stdBuilder().itemInputs(GTUtility.getIntegratedCircuit(5))
                .fluidInputs(new FluidStack(CppFluids.FWheat, 1000)).fluidOutputs(Materials.Ethanol.getFluid(4))
                .duration(11 * SECONDS).eut(TierEU.RECIPE_MV).addTo(distilleryRecipes);

        GTValues.RA.stdBuilder().itemInputs(GTUtility.getIntegratedCircuit(1))
                .fluidInputs(new FluidStack(CppFluids.FReed, 100)).fluidOutputs(new FluidStack(CppFluids.SWhine, 7))
                .duration(1 * SECONDS + 2 * TICKS).eut(24).addTo(distilleryRecipes);

        GTValues.RA.stdBuilder().itemInputs(GTUtility.getIntegratedCircuit(4))
                .fluidInputs(new FluidStack(CppFluids.FReed, 200))
                .fluidOutputs(new FluidStack(FluidRegistry.getFluid("fermentedbiomass"), 4))
                .duration(1 * SECONDS + 4 * TICKS).eut(24).addTo(distilleryRecipes);

        GTValues.RA.stdBuilder().itemInputs(GTUtility.getIntegratedCircuit(5))
                .fluidInputs(new FluidStack(CppFluids.FReed, 1000)).fluidOutputs(Materials.Ethanol.getFluid(5))
                .duration(11 * SECONDS).eut(TierEU.RECIPE_MV).addTo(distilleryRecipes);

        GTValues.RA.stdBuilder().itemInputs(GTUtility.getIntegratedCircuit(4))
                .fluidInputs(new FluidStack(CppFluids.Mash, 200))
                .fluidOutputs(new FluidStack(FluidRegistry.getFluid("biomass"), 4)).duration(1 * SECONDS + 4 * TICKS)
                .eut(24).addTo(distilleryRecipes);

        GTValues.RA.stdBuilder().itemInputs(GTUtility.getIntegratedCircuit(1))
                .fluidInputs(new FluidStack(CppFluids.Wash, 100)).fluidOutputs(new FluidStack(CppFluids.GHP, 6))
                .duration(1 * SECONDS + 2 * TICKS).eut(24).addTo(distilleryRecipes);

        GTValues.RA.stdBuilder().itemInputs(GTUtility.getIntegratedCircuit(4))
                .fluidInputs(new FluidStack(CppFluids.Wash, 100))
                .fluidOutputs(new FluidStack(FluidRegistry.getFluid("fermentedbiomass"), 14))
                .duration(1 * SECONDS + 4 * TICKS).eut(24).addTo(distilleryRecipes);

        GTValues.RA.stdBuilder()
                .itemInputs(
                        new ItemStack(Items.sugar, 32, 0),
                        new ItemStack(Items.dye, 4, 1),
                        new ItemStack(Items.dye, 4, 11),
                        new ItemStack(Items.dye, 4, 2),
                        Materials.Water.getCells(4))
                .itemOutputs(Materials.Empty.getCells(4)).fluidInputs(new FluidStack(CppFluids.GHP, 375))
                .fluidOutputs(new FluidStack(FluidRegistry.getFluid("potion.alcopops"), 4375)).duration(10 * TICKS)
                .eut(8).addTo(mixerRecipes);

        GTValues.RA.stdBuilder()
                .itemInputs(
                        new ItemStack(Items.sugar, 8, 0),
                        new ItemStack(Items.dye, 1, 1),
                        new ItemStack(Items.dye, 1, 11),
                        new ItemStack(Items.dye, 1, 2),
                        Materials.Water.getCells(1))
                .itemOutputs(Materials.Empty.getCells(1))
                .fluidInputs(new FluidStack(FluidRegistry.getFluid("potion.vodka"), 500))
                .fluidOutputs(new FluidStack(FluidRegistry.getFluid("potion.alcopops"), 1500)).duration(10 * TICKS)
                .eut(8).addTo(mixerRecipes);

        GTValues.RA.stdBuilder()
                .itemInputs(
                        new ItemStack(Items.sugar, 8, 0),
                        new ItemStack(Items.dye, 1, 1),
                        new ItemStack(Items.dye, 1, 11),
                        new ItemStack(Items.dye, 1, 2),
                        Materials.Water.getCells(1))
                .itemOutputs(Materials.Empty.getCells(1)).fluidInputs(new FluidStack(CppFluids.Korn, 1000))
                .fluidOutputs(new FluidStack(FluidRegistry.getFluid("potion.alcopops"), 2000)).duration(10 * TICKS)
                .eut(8).addTo(mixerRecipes);

        GTValues.RA.stdBuilder()
                .itemInputs(
                        new ItemStack(Items.sugar, 8, 0),
                        new ItemStack(Items.dye, 1, 1),
                        new ItemStack(Items.dye, 1, 11),
                        new ItemStack(Items.dye, 1, 2),
                        Materials.Water.getCells(1))
                .itemOutputs(Materials.Empty.getCells(1)).fluidInputs(new FluidStack(CppFluids.DKorn, 750))
                .fluidOutputs(new FluidStack(FluidRegistry.getFluid("potion.alcopops"), 1750)).duration(10 * TICKS)
                .eut(8).addTo(mixerRecipes);

        GTValues.RA.stdBuilder()
                .itemInputs(
                        new ItemStack(Items.sugar, 8, 0),
                        OreDict.ISget("cropSpiceleaf"),
                        OreDict.ISget("cropGinger"),
                        new ItemStack(Items.dye, 1, 2),
                        Materials.Water.getCells(1))
                .itemOutputs(Materials.Empty.getCells(1))
                .fluidInputs(new FluidStack(FluidRegistry.getFluid("potion.vodka"), 4000))
                .fluidOutputs(new FluidStack(CppFluids.njagi, 5000)).duration(10 * TICKS).eut(8).addTo(mixerRecipes);

        GTValues.RA.stdBuilder()
                .itemInputs(
                        ItemList.Crop_Drop_Chilly.get(1),
                        Materials.CosmicNeutronium.getDustTiny(1),
                        ItemList.Crop_Drop_Lemon.get(64),
                        ItemList.Crop_Drop_TeaLeaf.get(64),
                        CppItems.ModifierMagic.splitStack(8),
                        CppItems.ModifierSpace.splitStack(9))
                .fluidInputs(new FluidStack(CppFluids.njagi, 50000)).fluidOutputs(new FluidStack(CppFluids.jagi, 250))
                .duration(1 * TICKS).eut(TierEU.RECIPE_LuV).addTo(mixerRecipes);

        GTValues.RA.stdBuilder().itemInputs(Materials.Water.getCells(1)).itemOutputs(Materials.Empty.getCells(1))
                .fluidInputs(Materials.Ethanol.getFluid(1000))
                .fluidOutputs(new FluidStack(FluidRegistry.getFluid("potion.vodka"), 2500)).duration(10 * TICKS).eut(8)
                .addTo(mixerRecipes);

        GTValues.RA.stdBuilder().itemInputs(Materials.Water.getCells(1), new ItemStack(Items.sugar))
                .itemOutputs(Materials.Empty.getCells(1)).fluidInputs(new FluidStack(CppFluids.SWhine, 5000))
                .fluidOutputs(new FluidStack(FluidRegistry.getFluid("potion.rum"), 6000)).duration(10 * TICKS).eut(8)
                .addTo(mixerRecipes);

        // Brewery
        for (ItemStack itemStack : OreDictionary.getOres("listAllberry")) {
            GTValues.RA.stdBuilder().itemInputs(itemStack.splitStack(16)).fluidInputs(Materials.Water.getFluid(750))
                    .fluidOutputs(new FluidStack(CppFluids.Mash, 750)).duration(6 * SECONDS + 8 * TICKS).eut(4)
                    .addTo(brewingRecipes);

        }

        GTValues.RA.stdBuilder().itemInputs(new ItemStack(Items.sugar, 8))
                .fluidInputs(new FluidStack(FluidRegistry.getFluid("potion.weakness"), 750))
                .fluidOutputs(new FluidStack(CppFluids.Mash, 750)).duration(6 * SECONDS + 8 * TICKS).eut(4)
                .addTo(brewingRecipes);

        // Sugar Related

        GTValues.RA.stdBuilder().itemInputs(new ItemStack(CppItems.CppBerries, 1, 1))
                .itemOutputs(new ItemStack(Items.sugar, 8, 0)).duration(6 * SECONDS + 8 * TICKS).eut(4)
                .addTo(extractorRecipes);

        // Dyes from Plants
        for (ItemStack itemStack : OreDictionary.getOres("cropBlackberry")) {
            GTValues.RA.stdBuilder().itemInputs(itemStack.splitStack(16), Materials.Salt.getDust(2))
                    .itemOutputs(new ItemStack(Items.sugar)).fluidInputs(Materials.SulfuricAcid.getFluid(432))
                    .fluidOutputs(new FluidStack(FluidRegistry.getFluid("dye.chemical.dyeblack"), 288))
                    .duration(30 * SECONDS).eut(48).addTo(UniversalChemical);

        }
        for (ItemStack itemStack : OreDictionary.getOres("cropBlueberry")) {
            GTValues.RA.stdBuilder().itemInputs(itemStack.splitStack(16), Materials.Salt.getDust(2))
                    .itemOutputs(new ItemStack(Items.sugar)).fluidInputs(Materials.SulfuricAcid.getFluid(432))
                    .fluidOutputs(new FluidStack(FluidRegistry.getFluid("dye.chemical.dyeblue"), 288))
                    .duration(30 * SECONDS).eut(48).addTo(UniversalChemical);

        }
        for (ItemStack itemStack : OreDictionary.getOres("cropRaspberry")) {
            GTValues.RA.stdBuilder().itemInputs(itemStack.splitStack(16), Materials.Salt.getDust(2))
                    .itemOutputs(new ItemStack(Items.sugar)).fluidInputs(Materials.SulfuricAcid.getFluid(432))
                    .fluidOutputs(new FluidStack(FluidRegistry.getFluid("dye.chemical.dyepink"), 288))
                    .duration(30 * SECONDS).eut(48).addTo(UniversalChemical);
        }
        for (ItemStack itemStack : OreDictionary.getOres("cropVine")) {
            if (!itemStack.getUnlocalizedName().equals("tile.Thornvines")) {
                GTValues.RA.stdBuilder().itemInputs(itemStack.splitStack(16), Materials.Salt.getDust(2))
                        .fluidInputs(Materials.SulfuricAcid.getFluid(432))
                        .fluidOutputs(new FluidStack(FluidRegistry.getFluid("dye.chemical.dyegreen"), 288))
                        .duration(30 * SECONDS).eut(48).addTo(UniversalChemical);
            } else {
                GTValues.RA.stdBuilder().itemInputs(itemStack.splitStack(16), Materials.Salt.getDust(2))
                        .fluidInputs(Materials.SulfuricAcid.getFluid(432))
                        .fluidOutputs(new FluidStack(FluidRegistry.getFluid("dye.chemical.dyeyellow"), 288))
                        .duration(30 * SECONDS).eut(48).addTo(UniversalChemical);

            }
        }
        for (ItemStack itemStack : OreDictionary.getOres("cropCacti")) {
            GTValues.RA.stdBuilder().itemInputs(itemStack.splitStack(16), Materials.Salt.getDust(2))
                    .fluidInputs(Materials.SulfuricAcid.getFluid(432))
                    .fluidOutputs(new FluidStack(FluidRegistry.getFluid("dye.chemical.dyegreen"), 288))
                    .duration(30 * SECONDS).eut(48).addTo(UniversalChemical);

        }
        for (ItemStack itemStack : OreDictionary.getOres("cropGooseberry")) {
            GTValues.RA.stdBuilder().itemInputs(itemStack.splitStack(16), Materials.Salt.getDust(2))
                    .itemOutputs(new ItemStack(Items.sugar)).fluidInputs(Materials.SulfuricAcid.getFluid(432))
                    .fluidOutputs(new FluidStack(FluidRegistry.getFluid("dye.chemical.dyeyellow"), 288))
                    .duration(30 * SECONDS).eut(48).addTo(UniversalChemical);

        }
        for (ItemStack itemStack : OreDictionary.getOres("cropStrawberry")) {
            GTValues.RA.stdBuilder().itemInputs(itemStack.splitStack(16), Materials.Salt.getDust(2))
                    .itemOutputs(new ItemStack(Items.sugar)).fluidInputs(Materials.SulfuricAcid.getFluid(432))
                    .fluidOutputs(new FluidStack(FluidRegistry.getFluid("dye.chemical.dyered"), 288))
                    .duration(30 * SECONDS).eut(48).addTo(UniversalChemical);

        }

        if (BiomesOPlenty.isModLoaded()) {

            GTValues.RA.stdBuilder()
                    .itemInputs(GTModHandler.getModItem(BiomesOPlenty.ID, "food", 16), Materials.Salt.getDust(2))
                    .itemOutputs(new ItemStack(Items.sugar)).fluidInputs(Materials.SulfuricAcid.getFluid(432))
                    .fluidOutputs(new FluidStack(FluidRegistry.getFluid("dye.chemical.dyered"), 288))
                    .duration(30 * SECONDS).eut(48).addTo(UniversalChemical);

        }

        GTValues.RA.stdBuilder().itemInputs(new ItemStack(CppItems.CppBerries, 16, 0), Materials.Salt.getDust(2))
                .itemOutputs(new ItemStack(Items.sugar)).fluidInputs(Materials.SulfuricAcid.getFluid(432))
                .fluidOutputs(new FluidStack(FluidRegistry.getFluid("dye.chemical.dyepurple"), 288))
                .duration(30 * SECONDS).eut(48).addTo(UniversalChemical);

        if (Natura.isModLoaded()) {

            GTValues.RA.stdBuilder()
                    .itemInputs(GTModHandler.getModItem(Natura.ID, "berry.nether", 16, 0), Materials.Salt.getDust(2))
                    .itemOutputs(new ItemStack(Items.sugar)).fluidInputs(Materials.SulfuricAcid.getFluid(432))
                    .fluidOutputs(new FluidStack(FluidRegistry.getFluid("dye.chemical.dyelime"), 288))
                    .duration(30 * SECONDS).eut(48).addTo(UniversalChemical);

            GTValues.RA.stdBuilder()
                    .itemInputs(GTModHandler.getModItem(Natura.ID, "berry.nether", 16, 1), Materials.Salt.getDust(2))
                    .itemOutputs(new ItemStack(Items.sugar)).fluidInputs(Materials.SulfuricAcid.getFluid(432))
                    .fluidOutputs(new FluidStack(FluidRegistry.getFluid("dye.chemical.dyelightgray"), 288))
                    .duration(30 * SECONDS).eut(48).addTo(UniversalChemical);

            GTValues.RA.stdBuilder()
                    .itemInputs(GTModHandler.getModItem(Natura.ID, "berry.nether", 16, 2), Materials.Salt.getDust(2))
                    .itemOutputs(new ItemStack(Items.sugar)).fluidInputs(Materials.SulfuricAcid.getFluid(432))
                    .fluidOutputs(new FluidStack(FluidRegistry.getFluid("dye.chemical.dyelightblue"), 288))
                    .duration(30 * SECONDS).eut(48).addTo(UniversalChemical);

            GTValues.RA.stdBuilder()
                    .itemInputs(GTModHandler.getModItem(Natura.ID, "berry.nether", 16, 3), Materials.Salt.getDust(2))
                    .itemOutputs(new ItemStack(Items.sugar)).fluidInputs(Materials.SulfuricAcid.getFluid(432))
                    .fluidOutputs(new FluidStack(FluidRegistry.getFluid("dye.chemical.dyelime"), 288))
                    .duration(30 * SECONDS).eut(48).addTo(UniversalChemical);

        }

        // Goldfish
        GTValues.RA.stdBuilder().itemInputs(CppItems.GoldfischS).itemOutputs(new ItemStack(Items.gold_nugget))
                .outputChances(1).fluidOutputs(Materials.FishOil.getFluid(100)).duration(16 * TICKS).eut(8)
                .addTo(fluidExtractionRecipes);

        GTValues.RA.stdBuilder().itemInputs(CppItems.GoldfischS)
                .itemOutputs(Materials.MeatRaw.getDust(1), Materials.Gold.getDustTiny(1)).outputChances(10000, 100)
                .duration(3 * TICKS).eut(8).addTo(maceratorRecipes);

        // Space Modifier

        GTValues.RA.stdBuilder().itemInputs(Materials.Iron.getDust(1), new ItemStack(CppItems.Modifier, 16, 0))
                .itemOutputs(Materials.MeteoricIron.getDust(1))
                .fluidInputs(
                        Materials.UUMatter
                                .getFluid(Materials.MeteoricIron.getNeutrons() + Materials.MeteoricIron.getProtons()))
                .duration(12 * SECONDS).eut(TierEU.RECIPE_HV).addTo(multiblockChemicalReactorRecipes);

        GTValues.RA.stdBuilder().itemInputs(Materials.Steel.getDust(1), new ItemStack(CppItems.Modifier, 16, 0))
                .itemOutputs(Materials.MeteoricSteel.getDust(1))
                .fluidInputs(
                        Materials.UUMatter
                                .getFluid(Materials.MeteoricSteel.getNeutrons() + Materials.MeteoricSteel.getProtons()))
                .duration(12 * SECONDS).eut(TierEU.RECIPE_HV).addTo(multiblockChemicalReactorRecipes);

        GTValues.RA.stdBuilder().itemInputs(Materials.Bismuth.getDust(1), new ItemStack(CppItems.Modifier, 16, 0))
                .itemOutputs(Materials.Oriharukon.getDust(1))
                .fluidInputs(
                        Materials.UUMatter
                                .getFluid(Materials.Oriharukon.getNeutrons() + Materials.Oriharukon.getProtons()))
                .duration(12 * SECONDS).eut(TierEU.RECIPE_HV).addTo(multiblockChemicalReactorRecipes);

        GTValues.RA.stdBuilder().itemInputs(Materials.Titanium.getDust(1), new ItemStack(CppItems.Modifier, 16, 0))
                .itemOutputs(Materials.Desh.getDust(1))
                .fluidInputs(Materials.UUMatter.getFluid(Materials.Desh.getNeutrons() + Materials.Desh.getProtons()))
                .duration(12 * SECONDS).eut(TierEU.RECIPE_HV).addTo(multiblockChemicalReactorRecipes);
        // Non-elements

        GTValues.RA.stdBuilder().itemInputs(Materials.Ice.getDust(1), new ItemStack(CppItems.Modifier, 16, 0))
                .itemOutputs(Materials.CallistoIce.getDust(1))
                .fluidInputs(
                        Materials.UUMatter
                                .getFluid((Materials.Water.getProtons() + Materials.Water.getNeutrons()) * 10))
                .duration(12 * SECONDS).eut(TierEU.RECIPE_IV).addTo(multiblockChemicalReactorRecipes);

        GTValues.RA.stdBuilder().itemInputs(Materials.Lead.getDust(1), new ItemStack(CppItems.Modifier, 16, 0))
                .itemOutputs(Materials.Ledox.getDust(1))
                .fluidInputs(
                        Materials.UUMatter
                                .getFluid((Materials.Water.getProtons() + Materials.Water.getNeutrons()) * 10))
                .duration(12 * SECONDS).eut(TierEU.RECIPE_IV).addTo(multiblockChemicalReactorRecipes);

        if (Mods.Avaritia.isModLoaded()) {

            GTValues.RA.stdBuilder()
                    .itemInputs(
                            GTModHandler.getModItem(Avaritia.ID, "Resource", 1, 1),
                            new ItemStack(CppItems.Modifier, 32, 0))
                    .itemOutputs(Materials.MysteriousCrystal.getDust(1)).fluidInputs(Materials.UUMatter.getFluid(100))
                    .duration(12 * SECONDS).eut(TierEU.RECIPE_ZPM).addTo(multiblockChemicalReactorRecipes);

        }

        GTValues.RA.stdBuilder().itemInputs(Materials.MeteoricIron.getDust(1), new ItemStack(CppItems.Modifier, 64, 0))
                .itemOutputs(Materials.DeepIron.getDust(1))
                .fluidInputs(
                        Materials.UUMatter
                                .getFluid((Materials.Neutronium.getNeutrons() + Materials.Neutronium.getProtons())))
                .duration(6 * SECONDS).eut(TierEU.RECIPE_ZPM).addTo(multiblockChemicalReactorRecipes);

        GTValues.RA.stdBuilder().itemInputs(Materials.Plutonium241.getDust(1), new ItemStack(CppItems.Modifier, 64, 0))
                .itemOutputs(Materials.BlackPlutonium.getDust(1))
                .fluidInputs(
                        Materials.UUMatter
                                .getFluid((Materials.Neutronium.getNeutrons() + Materials.Neutronium.getProtons())))
                .duration(6 * SECONDS).eut(TierEU.RECIPE_UV).addTo(multiblockChemicalReactorRecipes);

        if (Mods.Thaumcraft.isModLoaded()) {
            // Magic Modifier PrimP

            GTValues.RA.stdBuilder().itemInputs(GTModHandler.getModItem(Thaumcraft.ID, "ItemEldritchObject", 1, 3))
                    .itemOutputs(new ItemStack(CppItems.Modifier, 8, 1)).duration(6 * SECONDS + 8 * TICKS).eut(4)
                    .addTo(extractorRecipes);

            GTValues.RA.stdBuilder().itemInputs(new ItemStack(CppItems.Modifier, 1, 1))
                    .itemOutputs(GTModHandler.getModItem(Thaumcraft.ID, "ItemResource", 16, 14))
                    .duration(6 * SECONDS + 8 * TICKS).eut(4).addTo(extractorRecipes);

            if (Mods.NewHorizonsCoreMod.isModLoaded()) {

                GTValues.RA.stdBuilder().itemInputs(new ItemStack(CppItems.Modifier, 16, 1))
                        .itemOutputs(com.dreammaster.item.ItemList.PrimordialPearlFragment.getIS().splitStack(3))
                        .fluidInputs(Materials.UUMatter.getFluid(52)).duration(20 * MINUTES).eut(384)
                        .addTo(RecipeMaps.autoclaveRecipes);
            }
            GTValues.RA.stdBuilder().itemInputs(GTModHandler.getModItem(Thaumcraft.ID, "ItemResource", 32, 14))
                    .itemOutputs(new ItemStack(CppItems.Modifier, 1, 1)).fluidInputs(Materials.UUMatter.getFluid(500))
                    .duration(2 * MINUTES).eut(TierEU.RECIPE_MV).addTo(RecipeMaps.autoclaveRecipes);

            // Magic Modifier

            GTValues.RA.stdBuilder().itemInputs(Materials.Iron.getDust(1), new ItemStack(CppItems.Modifier, 1, 1))
                    .itemOutputs(Materials.Thaumium.getDust(1))
                    .fluidInputs(
                            Materials.UUMatter
                                    .getFluid(Materials.Thaumium.getNeutrons() + Materials.Thaumium.getProtons()))
                    .duration(12 * SECONDS).eut(TierEU.RECIPE_MV).addTo(multiblockChemicalReactorRecipes);

            GTValues.RA.stdBuilder().itemInputs(Materials.Thaumium.getDust(1), new ItemStack(CppItems.Modifier, 1, 1))
                    .itemOutputs(Materials.Void.getDust(1))
                    .fluidInputs(
                            Materials.UUMatter
                                    .getFluid(Materials.Arsenic.getNeutrons() + Materials.Arsenic.getProtons()))
                    .duration(12 * SECONDS).eut(TierEU.RECIPE_HV).addTo(multiblockChemicalReactorRecipes);

            GTValues.RA.stdBuilder().itemInputs(Materials.Void.getDust(1), new ItemStack(CppItems.Modifier, 2, 1))
                    .itemOutputs(Materials.Shadow.getDust(1))
                    .fluidInputs(
                            Materials.UUMatter.getFluid(Materials.Indium.getNeutrons() + Materials.Indium.getProtons()))
                    .duration(12 * SECONDS).eut(TierEU.RECIPE_IV).addTo(multiblockChemicalReactorRecipes);

            GTValues.RA.stdBuilder().itemInputs(Materials.Shadow.getDust(1), new ItemStack(CppItems.Modifier, 16, 1))
                    .itemOutputs(Materials.Ichorium.getIngots(1))
                    .fluidInputs(
                            Materials.UUMatter
                                    .getFluid((Materials.Osmium.getNeutrons() + Materials.Osmium.getProtons()) * 1000))
                    .duration(1 * MINUTES + 30 * SECONDS).eut(TierEU.RECIPE_LuV)
                    .addTo(multiblockChemicalReactorRecipes);

        }

        // Magic Modifier + Space Modifier

        GTValues.RA.stdBuilder()
                .itemInputs(new ItemStack(CppItems.Modifier, 16, 0), new ItemStack(CppItems.Modifier, 4, 1))
                .itemOutputs(Materials.Mytryl.getDust(1))
                .fluidInputs(
                        Materials.Platinum.getMolten(288),
                        Materials.MeteoricIron.getMolten(144),
                        Materials.UUMatter.getFluid(Materials.Platinum.getProtons() + Materials.Platinum.getNeutrons()))
                .duration(1 * MINUTES + 30 * SECONDS).eut(TierEU.RECIPE_IV).addTo(multiblockChemicalReactorRecipes);

        // coral buff
        GTValues.RA.stdBuilder().itemInputs(GTModHandler.getModItem(BiomesOPlenty.ID, "coral1", 64, 15))
                .itemOutputs(Materials.Sunnarium.getDust(4)).fluidInputs(Materials.UUMatter.getFluid(2))
                .duration(12 * MINUTES + 30 * SECONDS).eut(TierEU.RECIPE_IV).requiresCleanRoom()
                .addTo(RecipeMaps.autoclaveRecipes);

        if (BiomesOPlenty.isModLoaded()) {
            GTValues.RA.stdBuilder().itemInputs(GTModHandler.getModItem(BiomesOPlenty.ID, "treeMoss", 8))
                    .itemOutputs(Ic2Items.plantBall.copy()).duration(15 * SECONDS).eut(2).addTo(compressorRecipes);
        }

        // Not a noob trophy.
        GTValues.RA.stdBuilder().itemInputs(Materials.Neutronium.getBlocks(64), Materials.Neutronium.getBlocks(64))
                .itemOutputs(CppItems.Trophy).duration(29826 * HOURS + 9 * MINUTES + 42 * SECONDS + 7 * TICKS).eut(8)
                .addTo(extruderRecipes);
        // Space Flower UUM
        GTValues.RA.stdBuilder().itemInputs(new ItemStack(CppItems.Modifier, 1, 0))
                .fluidOutputs(Materials.UUMatter.getFluid(2L)).duration(6 * SECONDS + 8 * TICKS).eut(4)
                .addTo(fluidExtractionRecipes);

        // Chem Refine
        GTValues.RA.stdBuilder()
                .itemInputs(
                        new ItemStack(CppItems.Modifier, 9, 0),
                        GTModHandler.getModItem(GalacticraftCore.ID, "item.meteoricIronRaw", 1, 0))
                .itemOutputs(Materials.MeteoricIron.getDust(4)).fluidInputs(Materials.Water.getFluid(1000))
                .duration(12 * SECONDS).eut(TierEU.RECIPE_HV).addTo(multiblockChemicalReactorRecipes);

        GTValues.RA.stdBuilder()
                .itemInputs(
                        new ItemStack(CppItems.Modifier, 9, 0),
                        GTModHandler.getModItem("GalacticraftMars", "item.null", 1L, 0))
                .itemOutputs(Materials.Desh.getDust(4)).fluidInputs(Materials.Water.getFluid(1000))
                .duration(12 * SECONDS).eut(TierEU.RECIPE_HV).addTo(multiblockChemicalReactorRecipes);

        GTValues.RA.stdBuilder()
                .itemInputs(
                        new ItemStack(CppItems.Modifier, 9, 0),
                        GTOreDictUnificator.get(OrePrefixes.crushed.get(Materials.MeteoricIron), 1))
                .itemOutputs(GTOreDictUnificator.get(OrePrefixes.crushedPurified.get(Materials.MeteoricIron), 4))
                .fluidInputs(Materials.Water.getFluid(1000)).duration(12 * SECONDS).eut(TierEU.RECIPE_HV)
                .addTo(multiblockChemicalReactorRecipes);

        GTValues.RA.stdBuilder()
                .itemInputs(
                        new ItemStack(CppItems.Modifier, 9, 0),
                        GTOreDictUnificator.get(OrePrefixes.crushed.get(Materials.Desh), 1))
                .itemOutputs(GTOreDictUnificator.get(OrePrefixes.crushedPurified.get(Materials.Desh), 4))
                .fluidInputs(Materials.Water.getFluid(1000)).duration(12 * SECONDS).eut(TierEU.RECIPE_HV)
                .addTo(multiblockChemicalReactorRecipes);

        GTValues.RA.stdBuilder()
                .itemInputs(
                        new ItemStack(CppItems.Modifier, 9, 0),
                        GTOreDictUnificator.get(OrePrefixes.crushed.get(Materials.Oriharukon), 1))
                .itemOutputs(GTOreDictUnificator.get(OrePrefixes.crushedPurified.get(Materials.Oriharukon), 4))
                .fluidInputs(Materials.Water.getFluid(1000)).duration(12 * SECONDS).eut(TierEU.RECIPE_HV)
                .addTo(multiblockChemicalReactorRecipes);

        GTValues.RA.stdBuilder()
                .itemInputs(
                        new ItemStack(CppItems.Modifier, 9, 0),
                        GTOreDictUnificator.get(OrePrefixes.crushed.get(Materials.Ledox), 1))
                .itemOutputs(GTOreDictUnificator.get(OrePrefixes.crushedPurified.get(Materials.Ledox), 4))
                .fluidInputs(Materials.Water.getFluid(1000)).duration(12 * SECONDS).eut(TierEU.RECIPE_HV)
                .addTo(multiblockChemicalReactorRecipes);

        GTValues.RA.stdBuilder()
                .itemInputs(
                        new ItemStack(CppItems.Modifier, 9, 0),
                        GTOreDictUnificator.get(OrePrefixes.crushed.get(Materials.CallistoIce), 1))
                .itemOutputs(GTOreDictUnificator.get(OrePrefixes.crushedPurified.get(Materials.CallistoIce), 4))
                .fluidInputs(Materials.Water.getFluid(1000)).duration(12 * SECONDS).eut(TierEU.RECIPE_HV)
                .addTo(multiblockChemicalReactorRecipes);

        GTValues.RA.stdBuilder()
                .itemInputs(
                        new ItemStack(CppItems.Modifier, 9, 0),
                        GTOreDictUnificator.get(OrePrefixes.crushed.get(Materials.BlackPlutonium), 1))
                .itemOutputs(GTOreDictUnificator.get(OrePrefixes.crushedPurified.get(Materials.BlackPlutonium), 4))
                .fluidInputs(Materials.Water.getFluid(1000)).duration(12 * SECONDS).eut(TierEU.RECIPE_LuV)
                .addTo(multiblockChemicalReactorRecipes);

        GTValues.RA.stdBuilder()
                .itemInputs(
                        new ItemStack(CppItems.Modifier, 9, 0),
                        GTOreDictUnificator.get(OrePrefixes.crushed.get(Materials.DeepIron), 1))
                .itemOutputs(GTOreDictUnificator.get(OrePrefixes.crushedPurified.get(Materials.DeepIron), 4))
                .fluidInputs(Materials.Water.getFluid(1000)).duration(12 * SECONDS).eut(TierEU.RECIPE_LuV)
                .addTo(multiblockChemicalReactorRecipes);

        // Potions from Netherberries

        GTValues.RA.stdBuilder().itemInputs(GTModHandler.getModItem(Natura.ID, "berry.nether", 16, 0))
                .fluidInputs(Materials.Water.getFluid(750))
                .fluidOutputs(new FluidStack(FluidRegistry.getFluid("potion.regen"), 750))
                .duration(6 * SECONDS + 8 * TICKS).eut(4).addTo(brewingRecipes);

        GTValues.RA.stdBuilder().itemInputs(GTModHandler.getModItem(Natura.ID, "berry.nether", 16, 1))
                .fluidInputs(Materials.Water.getFluid(750))
                .fluidOutputs(new FluidStack(FluidRegistry.getFluid("potion.nightvision"), 750))
                .duration(6 * SECONDS + 8 * TICKS).eut(4).addTo(brewingRecipes);

        GTValues.RA.stdBuilder().itemInputs(GTModHandler.getModItem(Natura.ID, "berry.nether", 16, 2))
                .fluidInputs(Materials.Water.getFluid(750))
                .fluidOutputs(new FluidStack(FluidRegistry.getFluid("potion.speed"), 750))
                .duration(6 * SECONDS + 8 * TICKS).eut(4).addTo(brewingRecipes);

        GTValues.RA.stdBuilder().itemInputs(GTModHandler.getModItem(Natura.ID, "berry.nether", 16, 3))
                .fluidInputs(Materials.Water.getFluid(750))
                .fluidOutputs(new FluidStack(FluidRegistry.getFluid("potion.strength"), 750))
                .duration(6 * SECONDS + 8 * TICKS).eut(4).addTo(brewingRecipes);

    }
}
