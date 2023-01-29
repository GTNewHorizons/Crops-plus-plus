package com.github.bartimaeusnek.cropspp.crops.cpp.trees;

import java.io.IOException;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.event.TextureStitchEvent;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;

public class PreloadTextures {

    @SubscribeEvent
    public void onTextureStitchPre(TextureStitchEvent.Pre e) {
        if (e.map == Minecraft.getMinecraft().getTextureManager().getTexture(TextureMap.locationBlocksTexture)) {
            for (int i = 0; i < InstalledTreesGetter.savedNames.size(); i++) {
                if ((!(InstalledTreesGetter.savedNames.get(i).contains("Shimmerleaf")
                        || InstalledTreesGetter.savedNames.get(i).contains("Cinderpearl"))
                        && !(InstalledTreesGetter.savedNames.get(i).contains("Vishroom")
                                || InstalledTreesGetter.savedNames.get(i).contains("Ethereal Bloom")))) {
                    ItemStack Sapling = InstalledTreesGetter.BaseSeed.get(i);
                    try {
                        BonsaiRenderer renderer = new BonsaiRenderer(
                                "bpp:" + Sapling.getIconIndex().getIconName().replaceAll(":", "_"),
                                InstalledTreesGetter.make_crop_IIcons(Sapling, Sapling.getIconIndex().getIconName()),
                                (TextureAtlasSprite) Sapling.getIconIndex());
                        e.map.setTextureEntry(renderer.getIconName(), renderer);
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                }
            }
        }
    }
}
