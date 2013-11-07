package tyn.emu;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.client.Minecraft;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraftforge.common.MinecraftForge;
import cpw.mods.fml.common.Mod;
//import cpw.mods.fml.common.Mod.Init;
import cpw.mods.fml.common.Mod.Instance;
//import cpw.mods.fml.common.Mod.PostInit;
//import cpw.mods.fml.common.Mod.PreInit; 
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;

@Mod(modid="Emu", name="EMU", version="0.0.0")
@NetworkMod(clientSideRequired=true, serverSideRequired=false)
public class Emu {
	/** BLOCK & ITEM DECLARATIONS */
	//## Wood Slat ID: 498
	public final static Block woodSlat = new WoodSlat(498, false).setTextureName("planks");
	private static final String[] woodSlatNames = { 
		"Oak Slat", "Spruce Slat", "Birch Slat", "Jungle Slat"};
	//## Wood Pole ID: 500
	public final static Block woodPole = new WoodPole(500).setTextureName("planks");
	private static final String[] woodPoleNames = { 
		"Oak Pole", "Spruce Pole", "Birch Pole", "Jungle Pole"};

    // The instance of your mod that Forge uses.
    @Instance("Emu")
    public static Emu instance;
    
    // Says where the client and server 'proxy' code is loaded.
    @SidedProxy(clientSide="tyn.emu.client.ClientProxy", serverSide="tyn.emu.CommonProxy")
    public static CommonProxy proxy;
    
  /*  @EventHandler
    public void preInit(FMLPreInitializationEvent event) {
            // Stub Method
    } */
    
    @EventHandler
    public void load(FMLInitializationEvent event) {
            proxy.registerRenderers();
            // Wood Slats (vertical slabs)
        	LanguageRegistry.addName(woodSlat, "Wood Slat");
        	MinecraftForge.setBlockHarvestLevel(woodSlat, "axe", 0);
        	GameRegistry.registerBlock(woodSlat, WoodItemSlat.class, "woodSlat");
        	for (int ix = 0; ix < 4; ix++){
        		ItemStack woodPoleStack = new ItemStack(woodSlat, 1, ix);
        		LanguageRegistry.addName(woodPoleStack, woodSlatNames[ix]);
        	}
            
            // Wood Poles
        	LanguageRegistry.addName(woodPole, "Wood Pole");
        	MinecraftForge.setBlockHarvestLevel(woodPole, "axe", 0);
        	GameRegistry.registerBlock(woodPole, WoodItemPole.class, "woodPole");
        	for (int ix = 0; ix < 4; ix++){
        		ItemStack woodPoleStack = new ItemStack(woodPole, 1, ix);
        		LanguageRegistry.addName(woodPoleStack, woodPoleNames[ix]);
        	}
            
        	// Rotten Flesh into Leather
        	ItemStack fleshStack = new ItemStack(Item.rottenFlesh);
        	ItemStack leatherStack = new ItemStack(Item.leather);
        	GameRegistry.addRecipe(leatherStack, "xx", "xx", 'x', fleshStack);
        	// Gravel into Flint
        	GameRegistry.addSmelting(Block.gravel.blockID, new ItemStack(Item.flint), 0.0f);
        	// Dirt, Sand, and Water into Clay Blocks
    		ItemStack dirtStack = new ItemStack(Block.dirt);
    	    ItemStack sandStack = new ItemStack(Block.sand);
    	    ItemStack waterBucket = new ItemStack(Item.bucketWater);
    	    GameRegistry.addRecipe(new ItemStack(Block.blockClay, 8), "xyx", "yzy", "xyx",
    	            'x', dirtStack, 'y', sandStack, 'z', waterBucket);
    	    // Wool block into Strings
    	    for(int tmp=0; tmp<16; tmp++){
    	    	GameRegistry.addShapelessRecipe(new ItemStack(Item.silk, 4), new ItemStack(Block.cloth, 1, tmp));
    	    }
    	    // Leather & Iron into Chain Armor
    	    ItemStack ingotStack = new ItemStack(Item.ingotIron);
    	    GameRegistry.addRecipe(new ItemStack(Item.helmetChain),
    	    		"yxy", "x x", 'x', leatherStack, 'y', ingotStack);
    	    GameRegistry.addRecipe(new ItemStack(Item.plateChain),
    	    		"x x", "yxy", "xyx", 'x', leatherStack, 'y', ingotStack);
    	    GameRegistry.addRecipe(new ItemStack(Item.legsChain),
    	    		"yxy", "x x", "x x", 'x', leatherStack, 'y', ingotStack);
    	    GameRegistry.addRecipe(new ItemStack(Item.bootsChain),
    	    		"y x", "x x", 'x', leatherStack, 'y', ingotStack);
    }
    
/*    @EventHandler
    public void postInit(FMLPostInitializationEvent event) {
            // Stub Method
    } */
}
