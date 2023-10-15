package com.github.bartimaeusnek.cropspp.crops.cpp.trees;

import java.awt.image.BufferedImage;

import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.resources.IResourceManager;
import net.minecraft.client.resources.data.AnimationMetadataSection;
import net.minecraft.util.ResourceLocation;

import com.github.bartimaeusnek.cropspp.Cropspp;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

// This class is no longer in use
@Deprecated
@SideOnly(Side.CLIENT)
public class BonsaiRenderer extends TextureAtlasSprite {

    private String baseIconName;
    private BufferedImage Image;

    public BonsaiRenderer(String iconName, BufferedImage Image, TextureAtlasSprite Original) {
        super(iconName);
        this.Image = Image;
        copyFrom(Original);
        // this.load(null,null);
    }

    @Override
    public boolean hasCustomLoader(IResourceManager manager, ResourceLocation location) {
        return true;
    }

    @Override
    public boolean load(IResourceManager manager, ResourceLocation location) {
        BufferedImage[] bufferedImage = new BufferedImage[1];
        bufferedImage[0] = Image;
        loadSprite(bufferedImage, null, false);
        Cropspp.cpplogger.error("Load");
        return false;
    }

    @Override
    public void loadSprite(BufferedImage[] mipmapImages, AnimationMetadataSection metadata,
            boolean useAnisotropicFiltering) {
        // Making MipMaps
        int width = mipmapImages[0].getWidth();
        int height = mipmapImages[0].getHeight();
        this.width = width;
        this.height = height;
        int[][] mipmapLevels = new int[mipmapImages.length][];
        BufferedImage bufferedimage = mipmapImages[0];

        mipmapLevels[0] = new int[bufferedimage.getWidth() * bufferedimage.getHeight()];
        bufferedimage.getRGB(
                0,
                0,
                bufferedimage.getWidth(),
                bufferedimage.getHeight(),
                mipmapLevels[0],
                0,
                bufferedimage.getWidth());

        framesTextureData.add(mipmapLevels);
    }

    @Override
    public boolean hasAnimationMetadata() {
        return false;
    }
}
