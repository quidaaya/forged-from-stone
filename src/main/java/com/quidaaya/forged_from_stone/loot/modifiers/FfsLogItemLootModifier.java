package com.quidaaya.forged_from_stone.loot.modifiers;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.quidaaya.forged_from_stone.ForgedFromStone;
import com.quidaaya.forged_from_stone.item.FfsItemRegister;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.neoforged.neoforge.common.loot.IGlobalLootModifier;
import net.neoforged.neoforge.common.loot.LootModifier;

public class FfsLogItemLootModifier extends LootModifier {
    public static final MapCodec<FfsLogItemLootModifier> FFS_LOG_LOOT_MODIFIER_MAP_CODEC = RecordCodecBuilder
            .mapCodec(instance ->
                    LootModifier.codecStart(instance).and(instance.group(
                            Codec.INT
                                    .fieldOf("quantity")
                                    .forGetter(e -> e.quantity),
                            BuiltInRegistries.ITEM
                                    .byNameCodec()
                                    .fieldOf("target_tag")
                                    .forGetter(e -> e.targetItem)
                    )).apply(instance, FfsLogItemLootModifier::new)
            );

    public final int quantity;
    public final Item targetItem;


    /**
     * Constructs a LootModifier.
     *
     * @param conditionsIn the ILootConditions that need to be matched before the loot is modified.
     */
    protected FfsLogItemLootModifier(LootItemCondition[] conditionsIn, int quantity, Item targetItem) {
        super(conditionsIn);

        this.targetItem = targetItem;
        this.quantity = quantity;
    }

    @Override
    protected ObjectArrayList<ItemStack> doApply(ObjectArrayList<ItemStack> generatedLoot, LootContext context) {
        try {
            ServerLevel level = context.getLevel();

            boolean removed = generatedLoot.removeIf(itemStack ->
                    BuiltInRegistries.ITEM.getKey(this.targetItem).equals(BuiltInRegistries.ITEM.getKey(itemStack.getItem()))
            );

            if(removed) {
                generatedLoot.add(new ItemStack(FfsItemRegister.BASE_LOG.get(), quantity));
            }

            return generatedLoot;
        } catch (IllegalStateException exception) {
            ForgedFromStone.LOGGER.error("An error occurred when attempting to replace the log", exception);
            return generatedLoot;
        }
    }

    @Override
    public MapCodec<? extends IGlobalLootModifier> codec() {
        return FFS_LOG_LOOT_MODIFIER_MAP_CODEC;
    }
}
