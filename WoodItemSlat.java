package tyn.emu;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemDye;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;

public class WoodItemSlat extends ItemBlock {

	private final static String[] subNames = {
		"oakslat", "spruceslat", "birchslat", "jungleslat"};

	public WoodItemSlat(int id) {
		super(id);
		// Auto-generated constructor stub
		setHasSubtypes(true);
		setUnlocalizedName("woodSlat");
	}

	@Override
    public int getMetadata(int par1)
    {
        return par1;
    }

	@Override
    public String getUnlocalizedName(ItemStack itemstack)
    {
        return super.getUnlocalizedName() + "." + subNames[itemstack.getItemDamage()];
    }

}
