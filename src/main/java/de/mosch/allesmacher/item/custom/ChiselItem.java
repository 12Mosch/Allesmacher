package de.mosch.allesmacher.item.custom;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;

import java.util.Map;

public class ChiselItem extends Item {
    private static final Map<Block, Block> CHISEL_MAP =
            Map.ofEntries(
                    Map.entry(Blocks.STONE, Blocks.STONE_BRICKS),
                    Map.entry(Blocks.STONE_BRICKS, Blocks.CHISELED_STONE_BRICKS),
                    Map.entry(Blocks.CHISELED_STONE_BRICKS, Blocks.CRACKED_STONE_BRICKS),
                    Map.entry(Blocks.CRACKED_STONE_BRICKS, Blocks.STONE),
                    Map.entry(Blocks.END_STONE, Blocks.END_STONE_BRICKS),
                    Map.entry(Blocks.END_STONE_BRICKS, Blocks.END_STONE),
                    Map.entry(Blocks.NETHERRACK, Blocks.CHISELED_NETHER_BRICKS),
                    Map.entry(Blocks.CHISELED_NETHER_BRICKS, Blocks.NETHER_BRICKS),
                    Map.entry(Blocks.NETHER_BRICKS, Blocks.CRACKED_NETHER_BRICKS),
                    Map.entry(Blocks.CRACKED_NETHER_BRICKS, Blocks.NETHERRACK),
                    Map.entry(Blocks.MOSSY_COBBLESTONE, Blocks.MOSSY_STONE_BRICKS),
                    Map.entry(Blocks.MOSSY_STONE_BRICKS, Blocks.MOSSY_COBBLESTONE),
                    Map.entry(Blocks.DEEPSLATE, Blocks.DEEPSLATE_BRICKS),
                    Map.entry(Blocks.DEEPSLATE_BRICKS, Blocks.CHISELED_DEEPSLATE),
                    Map.entry(Blocks.CHISELED_DEEPSLATE, Blocks.POLISHED_DEEPSLATE),
                    Map.entry(Blocks.POLISHED_DEEPSLATE, Blocks.CRACKED_DEEPSLATE_BRICKS),
                    Map.entry(Blocks.CRACKED_DEEPSLATE_BRICKS, Blocks.DEEPSLATE_TILES),
                    Map.entry(Blocks.DEEPSLATE_TILES, Blocks.CRACKED_DEEPSLATE_TILES),
                    Map.entry(Blocks.CRACKED_DEEPSLATE_TILES, Blocks.DEEPSLATE),
                    Map.entry(Blocks.TUFF, Blocks.CHISELED_TUFF),
                    Map.entry(Blocks.CHISELED_TUFF, Blocks.POLISHED_TUFF),
                    Map.entry(Blocks.POLISHED_TUFF, Blocks.TUFF_BRICKS),
                    Map.entry(Blocks.TUFF_BRICKS, Blocks.CHISELED_TUFF_BRICKS),
                    Map.entry(Blocks.CHISELED_TUFF_BRICKS, Blocks.TUFF),
                    Map.entry(Blocks.PACKED_MUD, Blocks.MUD_BRICKS),
                    Map.entry(Blocks.MUD_BRICKS, Blocks.PACKED_MUD),
                    Map.entry(Blocks.PRISMARINE, Blocks.PRISMARINE_BRICKS),
                    Map.entry(Blocks.PRISMARINE_BRICKS, Blocks.PRISMARINE),
                    Map.entry(Blocks.BLACKSTONE, Blocks.CHISELED_POLISHED_BLACKSTONE),
                    Map.entry(Blocks.CHISELED_POLISHED_BLACKSTONE, Blocks.POLISHED_BLACKSTONE),
                    Map.entry(Blocks.POLISHED_BLACKSTONE, Blocks.POLISHED_BLACKSTONE_BRICKS),
                    Map.entry(Blocks.POLISHED_BLACKSTONE_BRICKS, Blocks.CRACKED_POLISHED_BLACKSTONE_BRICKS),
                    Map.entry(Blocks.CRACKED_POLISHED_BLACKSTONE_BRICKS, Blocks.BLACKSTONE),
                    Map.entry(Blocks.QUARTZ_BLOCK, Blocks.CHISELED_QUARTZ_BLOCK),
                    Map.entry(Blocks.CHISELED_QUARTZ_BLOCK, Blocks.QUARTZ_BRICKS),
                    Map.entry(Blocks.QUARTZ_BRICKS, Blocks.QUARTZ_PILLAR),
                    Map.entry(Blocks.QUARTZ_PILLAR, Blocks.SMOOTH_QUARTZ),
                    Map.entry(Blocks.SMOOTH_QUARTZ, Blocks.QUARTZ_BLOCK)
        );

    public ChiselItem(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResult useOn(UseOnContext context) {
        Level level = context.getLevel();
        Block clickedBlock = level.getBlockState(context.getClickedPos()).getBlock();

        if(CHISEL_MAP.containsKey(clickedBlock)) {
            if(!level.isClientSide()) {
              level.setBlockAndUpdate(context.getClickedPos(), CHISEL_MAP.get(clickedBlock).defaultBlockState());

              context.getItemInHand().hurtAndBreak(1, ((ServerLevel) level), context.getPlayer(),
                      item -> context.getPlayer().onEquippedItemBroken(item, EquipmentSlot.MAINHAND));

              level.playSound(null, context.getClickedPos(), SoundEvents.GRINDSTONE_USE, SoundSource.BLOCKS);
            }
        }

        return InteractionResult.SUCCESS;
    }
}
