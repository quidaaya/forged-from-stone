package com.quidaaya.forged_from_stone.item;

import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;

public class FfsExtensibleLogItem extends Item {
    private final int tintColor;

    public FfsExtensibleLogItem(Properties properties, int tintColor) {
        super(properties);
        this.tintColor = tintColor;
    }

    public int getTintColor() {
        return this.tintColor;
    }

    @Override
    public InteractionResult useOn(UseOnContext pContext) {
//        BlockPos pos = pContext.getClickedPos();
//        Level level = pContext.getLevel();
        Player player = pContext.getPlayer();
        ItemStack item = pContext.getItemInHand();

        item.consume(1, player);

        return InteractionResult.CONSUME;
    }
}
