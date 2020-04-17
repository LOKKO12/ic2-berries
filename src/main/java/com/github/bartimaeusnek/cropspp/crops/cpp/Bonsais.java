package com.github.bartimaeusnek.cropspp.crops.cpp;

import com.github.bartimaeusnek.cropspp.ConfigValues;
import com.github.bartimaeusnek.cropspp.abstracts.BasicCrop;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import gregtech.api.objects.XSTR;
import ic2.api.crops.Crops;
import ic2.api.crops.ICropTile;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;

import java.util.Arrays;
import java.util.List;

public enum Bonsais {

    OAK("Oak",1,new String[]{"Tree", "Bonsai", "Leavy","Food"},"Notch",new ItemStack(Blocks.sapling),new ItemStack[]{new ItemStack(Blocks.log,Bonsais.LOGSPERHARVEST),new ItemStack(Blocks.sapling),new ItemStack(Items.apple)}, new int[]{100,50,10},null,null,"Can drop apples"),
    SPRUCE("Spruce",1,new String[]{"Tree", "Bonsai", "Leavy"},"Notch",new ItemStack(Blocks.sapling,1,1),new ItemStack[]{new ItemStack(Blocks.log,Bonsais.LOGSPERHARVEST,1),new ItemStack(Blocks.sapling,1,1)}, new int[]{80,30},null,null),
    BIRCH("Birch",1,new String[]{"Tree", "Bonsai", "Leavy"},"Notch",new ItemStack(Blocks.sapling,1,2),new ItemStack[]{new ItemStack(Blocks.log,Bonsais.LOGSPERHARVEST,2),new ItemStack(Blocks.sapling,1,2)}, new int[]{80,30},null,null),
    JUNGLE("Jungle",1,new String[]{"Tree", "Bonsai", "Leavy"},"Notch",new ItemStack(Blocks.sapling,1,3),new ItemStack[]{new ItemStack(Blocks.log,Bonsais.LOGSPERHARVEST,3),new ItemStack(Blocks.sapling,1,3)}, new int[]{80,30},null,null),
    ACACIA("Acacia",1,new String[]{"Tree", "Bonsai", "Leavy"},"Notch",new ItemStack(Blocks.sapling,1,4),new ItemStack[]{new ItemStack(Blocks.log2,Bonsais.LOGSPERHARVEST),new ItemStack(Blocks.sapling,1,4)}, new int[]{80,30},null,null),
    DARKOAK("Dark Oak",1,new String[]{"Tree", "Bonsai", "Leavy","Dark"},"Notch",new ItemStack(Blocks.sapling,1,5),new ItemStack[]{new ItemStack(Blocks.log2,Bonsais.LOGSPERHARVEST,1),new ItemStack(Blocks.sapling,1,5)}, new int[]{80,30},null,null),
    ;

    private final static int LOGSPERHARVEST = 10;

    public InternalVanillaBonsais getBonsais() {
        return bonsais;
    }

    public ItemStack getSeed() {
        return seed;
    }

    final InternalVanillaBonsais bonsais;
    final ItemStack seed;
    final boolean load;


    Bonsais(String name, int tier, String[] attributes, String discoveredBy, ItemStack seed,ItemStack[] gain,int[] chances,String[] pathToSprites, String modID, String... additionalDescriton) {
        bonsais = new InternalVanillaBonsais(name, tier, attributes, discoveredBy, gain, chances, pathToSprites, additionalDescriton);
        this.seed = seed;
        load = modID == null || Loader.isModLoaded(modID);
    }

    public static void registerAllBonais(){
        for (Bonsais bonsai : Bonsais.values()) {
            if (bonsai.load) {
                Crops.instance.registerCrop(bonsai.getBonsais());
                if (bonsai.getSeed() != null) {
                    Crops.instance.registerBaseSeed(bonsai.getSeed(), bonsai.getBonsais(), 1, 1, 1, 1);
                }
            }
        }
    }

    static class InternalVanillaBonsais extends BasicCrop {

        String name;
        int tier;
        String[] attributes;
        String discoveredBy;
        ItemStack[] gain;
        String[] additionalDescriton;
        int[] chanches;
        String[] pathToSprites;

        public InternalVanillaBonsais(String name, int tier, String[] attributes, String discoveredBy, ItemStack[] gain, int[] chanches, String[] pathToSprites, String[] additionalDescriton) {
            this.name = name;
            this.tier = tier;
            this.attributes = attributes;
            this.discoveredBy = discoveredBy;
            this.gain = gain;
            this.additionalDescriton = additionalDescriton;
            this.chanches = chanches;
            this.pathToSprites=pathToSprites;
        }

        @Override
        public String name() {
            return name + " Bonsai";
        }

        @Override
        public int tier() {
            return tier;
        }

        @Override
        public int stat(int i) {
            return i == 3 ? 1 : 0;
        }

        @Override
        public String[] attributes() {
            return attributes;
        }

        @Override
        public int maxSize() {
            return 3;
        }

        @Override
        public String discoveredBy() {
            return discoveredBy;
        }

        @Override
        public boolean canGrow(ICropTile iCropTile) {
            return iCropTile.getSize()<maxSize();
        }

        @Override
        public ItemStack getGain(ICropTile iCropTile) {
            int max = 0;
            for (int chanch : chanches) {
                max=Math.max(chanch,max);
            }
            XSTR rand = new XSTR();
            int roll = rand.nextInt(max);
            for (int i = chanches.length - 1; i >= 0; i--) {
                if (chanches[i] >= roll) {
                    ItemStack ret = gain[i].copy();
                    int moreorless = rand.nextInt(4);
                    if (rand.nextBoolean())
                        ret.stackSize+=moreorless;
                    else{
                        if (rand.nextBoolean())
                            ret.stackSize-=moreorless;
                        if (ret.stackSize < 0)
                            ret = null;
                    }
                    return ret;
                }
            }
          return null;
        }

        @SideOnly(Side.CLIENT)
        public void registerSprites(IIconRegister iconRegister) {
            textures = new IIcon[maxSize()];
            if (pathToSprites == null || textures.length != pathToSprites.length) {
                for (int i = 1; i <= textures.length; i++) {
                    // ic2:crop/blockCrop.NAME.n is the legacy name for backwards compatiblity
                    textures[i - 1] = iconRegister.registerIcon("bpp:crop/blockCrop." + name + "." + i);
                }
            }else{
                for (int i = 0; i < pathToSprites.length; i++) {
                    textures[i] = iconRegister.registerIcon(pathToSprites[i]);
                }
            }
        }

        @Override
        public int growthDuration(ICropTile crop) {
            return ConfigValues.debug ? 1 : super.growthDuration(crop)*3;
        }

        @Override
        public List<String> getCropInformation() {
            return additionalDescriton != null ? Arrays.asList(additionalDescriton) : null;
        }

        public ItemStack getDisplayItem() {
            return new ItemStack(Blocks.sapling);
        }
    }
}
