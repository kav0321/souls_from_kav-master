package net.kav.soul.entity.ai;

import net.kav.soul.client.gui.ExampleGui;
import net.kav.soul.client.gui.ExampleScreen;
import net.kav.soul.entity.custom.RaccoonEntity;
import net.kav.soul.item.Moditems;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.TargetPredicate;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.ai.goal.TemptGoal;
import net.minecraft.entity.ai.pathing.EntityNavigation;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.mob.PathAwareEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.recipe.Ingredient;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import org.jetbrains.annotations.Nullable;

import java.util.EnumSet;

public class StopAndLookatPlayerGoal extends Goal {


    private static final TargetPredicate TEMPTING_ENTITY_PREDICATE = TargetPredicate.createNonAttackable().setBaseMaxDistance(10.0).ignoreVisibility();
    private final TargetPredicate predicate;
    protected final PathAwareEntity mob;
    private final double speed;
    private double lastPlayerX;
    private double lastPlayerY;
    private double lastPlayerZ;
    private double lastPlayerPitch;
    private double lastPlayerYaw;
    @Nullable
    protected PlayerEntity closestPlayer;
    private int cooldown;
    private boolean active;
    private final Ingredient food;
    private final boolean canBeScared;

    public StopAndLookatPlayerGoal(PathAwareEntity entity, double speed, Ingredient food, boolean canBeScared) {
        this.mob = entity;
        this.speed = speed;
        this.food = food;
        this.canBeScared = canBeScared;
        this.setControls(EnumSet.of(Control.MOVE, Control.LOOK));
        this.predicate = TEMPTING_ENTITY_PREDICATE.copy().setPredicate(this::isTemptedBy);
    }

    public boolean canStart() {
        if (this.cooldown > 0) {
            --this.cooldown;
            return false;
        }

            else
            {
                this.closestPlayer = this.mob.world.getClosestPlayer(this.predicate, this.mob);
                return this.closestPlayer != null;
            }

    }


    private boolean isTemptedBy(LivingEntity entity) {
       return this.food.test(entity.getMainHandStack());
    }

    public boolean shouldContinue() {
        if (this.canBeScared()) {
            if (this.mob.squaredDistanceTo(this.closestPlayer) < 36.0) {
                if (this.closestPlayer.squaredDistanceTo(this.lastPlayerX, this.lastPlayerY, this.lastPlayerZ) > 0.010000000000000002) {
                    return false;
                }

                if (Math.abs((double)this.closestPlayer.getPitch() - this.lastPlayerPitch) > 5.0 || Math.abs((double)this.closestPlayer.getYaw() - this.lastPlayerYaw) > 5.0) {
                    return false;
                }
            } else {
                this.lastPlayerX = this.closestPlayer.getX();
                this.lastPlayerY = this.closestPlayer.getY();
                this.lastPlayerZ = this.closestPlayer.getZ();
            }

            this.lastPlayerPitch = (double)this.closestPlayer.getPitch();
            this.lastPlayerYaw = (double)this.closestPlayer.getYaw();
        }

        return this.canStart();
    }

    protected boolean canBeScared() {
        return this.canBeScared;
    }

    public void start() {
        this.lastPlayerX = this.closestPlayer.getX();
        this.lastPlayerY = this.closestPlayer.getY();
        this.lastPlayerZ = this.closestPlayer.getZ();
        this.active = true;
    }

    public void stop() {
        this.closestPlayer = null;
        this.mob.getNavigation().stop();
        this.cooldown = toGoalTicks(100);
        this.active = false;
    }

    public void tick() {
        this.mob.getLookControl().lookAt(this.closestPlayer, (float)(this.mob.getMaxHeadRotation() + 20), (float)this.mob.getMaxLookPitchChange());
        if (this.mob.squaredDistanceTo(this.closestPlayer) < 6.25) {
            this.mob.getNavigation().stop();
        } else {
            this.mob.getNavigation().startMovingTo(this.closestPlayer, this.speed);
        }

    }

    public boolean isActive() {
        return this.active;
    }
}
