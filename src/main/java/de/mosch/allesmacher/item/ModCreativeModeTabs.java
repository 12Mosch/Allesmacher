package de.mosch.allesmacher.item;

import de.mosch.allesmacher.AllesMacher;
import de.mosch.allesmacher.block.ModBlocks;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModCreativeModeTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TAB =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, AllesMacher.MOD_ID);

    public static final Supplier<CreativeModeTab> ALLESMACHER_TAB = CREATIVE_MODE_TAB.register("allesmacher_tab",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(ModItems.ENDERITE_INGOT.get())).title(Component.translatable("creative.allesmacher.allesmacher_tab"))
                    .displayItems((itemDisplayParameters, output) -> {
                        output.accept(ModBlocks.ENDERITE_BLOCK);
                        output.accept(ModBlocks.ENDERITE_ORE);
                        output.accept(ModItems.ENDERITE_INGOT);
                        output.accept(ModItems.ENDERITE_SCRAP);
                        output.accept(ModItems.CHISEL);
                    }).build());

    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TAB.register(eventBus);
    }
}
