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

public class FfsLogTagLootModifier extends LootModifier {
    public static final MapCodec<FfsLogTagLootModifier> FFS_LOG_LOOT_MODIFIER_MAP_CODEC = RecordCodecBuilder
            .mapCodec(instance ->
                    LootModifier.codecStart(instance).and(instance.group(
                            Codec.INT
                                .fieldOf("quantity")
                                .forGetter(e -> e.quantity),
                            Codec.STRING
                                .fieldOf("target_tag")
                                .forGetter(e -> e.targetTag)
                    )).apply(instance, FfsLogTagLootModifier::new)
            );

    public final int quantity;
    public final String targetTag;
    public final TagKey<Item> itemTagKey;


    /**
     * Constructs a LootModifier.
     *
     * @param conditionsIn the ILootConditions that need to be matched before the loot is modified.
     */
    protected FfsLogTagLootModifier(LootItemCondition[] conditionsIn, int quantity, String targetTag) {
        super(conditionsIn);

        this.targetTag = targetTag;
        this.quantity = quantity;

        String[] keyParts = targetTag.split(":");

        this.itemTagKey = TagKey.create(
            Registries.ITEM,
                ResourceLocation.fromNamespaceAndPath(keyParts[0], keyParts[1])
        );
    }

    @Override
    protected ObjectArrayList<ItemStack> doApply(ObjectArrayList<ItemStack> generatedLoot, LootContext context) {
        try {
            ServerLevel level = context.getLevel();

            boolean vanillaRemoved = generatedLoot.removeIf(itemStack ->
                    level.registryAccess()
                            .lookupOrThrow(BuiltInRegistries.ITEM.key())
                            .getOrThrow(this.itemTagKey)
                            .stream()
                            .anyMatch(item -> item.is(BuiltInRegistries.ITEM.getKey(itemStack.getItem())))
            );

            if(vanillaRemoved) {
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
