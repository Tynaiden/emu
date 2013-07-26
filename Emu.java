package tyn.emu;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
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

@Mod(modid="Emu", name="EMU", version="0.0.0")
@NetworkMod(clientSideRequired=true, serverSideRequired=false)
public class Emu {

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
    	    ItemStack woolStack = new ItemStack(Block.cloth, 42, -1);
    	    GameRegistry.addShapelessRecipe(new ItemStack(Item.silk, 4), woolStack);
    	    // Wool & Iron into Chain Armor
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
