package net.secknv.scomp.item;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItemFrame;
import net.minecraft.init.Items;
import net.minecraft.item.IItemPropertyGetter;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.*;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;

public class SCCompassOverride {

    public static void override() {

        Items.COMPASS.addPropertyOverride(new ResourceLocation("angle"), new IItemPropertyGetter() {

            @SideOnly(Side.CLIENT)
            double rotation;
            @SideOnly(Side.CLIENT)
            double rota;
            @SideOnly(Side.CLIENT)
            long lastUpdateTick;
            @SideOnly(Side.CLIENT)
            public float apply(ItemStack stack, @Nullable World worldIn, @Nullable EntityLivingBase entityIn) {


                if (entityIn == null && !stack.isOnItemFrame()) {

                    return 0.0F;
                }
                else {

                    //true if compass in player's hand
                    boolean flag = entityIn != null;

                    //entity becomes either the player(EntityLivingBase) or the ItemFrame(EntityItemFrame)
                    Entity entity = flag ? entityIn : stack.getItemFrame();

                    if (worldIn == null) {

                        worldIn = entity.worldObj;
                    }

                    //this is the float returned
                    double d0;

                    if (worldIn.provider.isSurfaceWorld()) {

                        double d1 = flag ? (double)entity.rotationYaw : this.getFrameRotation((EntityItemFrame)entity);
                        d1 = (double)MathHelper.positiveModulo( (float)d1 / 360F, 1.0F );
                        //this adds an offset because of how compass textures are saved. #justmojangthings
                        d0 = 0.5D - d1;
                    }
                    else {

                        d0 = Math.random();
                    }

                    if (flag) {

                        d0 = this.wobble(worldIn, d0);
                    }

                    return MathHelper.positiveModulo((float)d0, 1.0F);
                }
            }


            @SideOnly(Side.CLIENT)
            private double wobble(World worldIn, double num) {

                if (worldIn.getTotalWorldTime() != this.lastUpdateTick) {

                    this.lastUpdateTick = worldIn.getTotalWorldTime();
                    double d0 = num - this.rotation;
                    d0 = (double)MathHelper.positiveModulo( (float)d0 + 0.5F, 1.0F ) - 0.5D;
                    this.rota += d0 * 0.1D;
                    this.rota *= 0.8D;
                    this.rotation += this.rota;
                    this.rotation = (double)MathHelper.positiveModulo( (float)this.rotation, 1.0F );
                }

                return this.rotation;
            }
            @SideOnly(Side.CLIENT)
            private double getFrameRotation(EntityItemFrame entFrame) {

                return (double)MathHelper.clampAngle(180 + entFrame.facingDirection.getHorizontalIndex() * 90);
            }
        });

    }

}
