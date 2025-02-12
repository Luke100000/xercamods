package xerca.xercamod.common.item;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import xerca.xercamod.common.Config;
import xerca.xercamod.common.XercaMod;
import xerca.xercamod.common.block.BlockPizza;

import javax.annotation.Nullable;
import java.util.List;

import static xerca.xercamod.common.block.BlockPizza.isAllEmpty;
import static xerca.xercamod.common.block.BlockPizza.postfix;

public class ItemPizza extends BlockConditionedItem {
    private final BlockPizza.Ingredient slot1;
    private final BlockPizza.Ingredient slot2;
    private final BlockPizza.Ingredient slot3;

    public ItemPizza(Block blockIn, BlockPizza.Ingredient slot1, BlockPizza.Ingredient slot2, BlockPizza.Ingredient slot3) {
        super(blockIn, isAllEmpty(slot1, slot2, slot3) ? new Item.Properties().tab(CreativeModeTab.TAB_FOOD) : new Item.Properties(),  Config::isFoodEnabled);
        this.slot1 = slot1;
        this.slot2 = slot2;
        this.slot3 = slot3;
        this.setRegistryName("pizza" + postfix(slot1, slot2, slot3));
    }

    @OnlyIn(Dist.CLIENT)
    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level worldIn, List<Component> tooltip, TooltipFlag flagIn) {
        addPizzaIngredientToTooltip(tooltip, slot1);
        addPizzaIngredientToTooltip(tooltip, slot2);
        addPizzaIngredientToTooltip(tooltip, slot3);
    }

    static void addPizzaIngredientToTooltip(List<Component> tooltip, BlockPizza.Ingredient ingredient){
        if(!ingredient.equals(BlockPizza.Ingredient.EMPTY)) {
            tooltip.add(new TranslatableComponent(XercaMod.MODID + ".ingredient." + ingredient.name().toLowerCase()).withStyle(ChatFormatting.GRAY));
        }
    }

    @Override
    public Component getName(ItemStack stack) {
        if(slot1.equals(BlockPizza.Ingredient.EMPTY) && slot2.equals(BlockPizza.Ingredient.EMPTY) && slot3.equals(BlockPizza.Ingredient.EMPTY))
            return new TranslatableComponent(XercaMod.MODID + ".pizza_plain");
        return new TranslatableComponent(XercaMod.MODID + ".pizza");
    }
}
