package com.github.genehyde.ai_blocks.block;

import com.github.genehyde.ai_blocks.entity.ProgrammableModEntity;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import net.minecraft.block.Block;
import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.World;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.Map;

/**
 * Simplified prototype for the concept of having blocks that can add behaviors to mobs
 */
public class SimpleAiBlock extends Block {
    private static final Logger LOGGER = LogManager.getLogger();

    public SimpleAiBlock(Properties properties) {
        super(properties);
    }

    public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit) {
        if (player.canUseCommandBlock()) {
//            player.openCommandBlock((CommandBlockTileEntity) tileentity);
            List<ProgrammableModEntity> list = worldIn.getEntitiesWithinAABB(ProgrammableModEntity.class, (new AxisAlignedBB(pos)).grow(8.0D, 6.0D, 8.0D));
            for (ProgrammableModEntity entity: list){
                if (entity.hasCustomName() && entity.getCustomName().getString().equals("JEFF")) {
                   Map<Integer, List<String>> goalMap = Maps.newHashMap();
                    goalMap.put(1, Lists.newArrayList("net.minecraft.entity.ai.goal.NearestAttackableTargetGoal this net.minecraft.entity.player.PlayerEntity.class true"));
                    goalMap.put(8, Lists.newArrayList("net.minecraft.entity.ai.goal.LookAtGoal this net.minecraft.entity.player.PlayerEntity.class 8.0F"));
                    goalMap.put(8, Lists.newArrayList("net.minecraft.entity.ai.goal.LookRandomlyGoal this"));
                    entity.setProgrammableTargets(goalMap);
               }
            }
            return ActionResultType.SUCCESS;
        } else {
            return ActionResultType.PASS;
        }
    }

    /**
     * @deprecated
     */
    public BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.MODEL;
    }
}
