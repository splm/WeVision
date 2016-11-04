package me.splm.wevision;


import me.splm.wevision.animation.attention.DropOutAnimation;
import me.splm.wevision.animation.attention.FlashAnimation;
import me.splm.wevision.animation.attention.ShakeAnimation;
import me.splm.wevision.animation.attention.ShakeHarderAnimation;
import me.splm.wevision.animation.attention.TakeOffAnimation;
import me.splm.wevision.animation.bounce.BounceInAnimation;
import me.splm.wevision.animation.bounce.BounceInDownAnimation;
import me.splm.wevision.animation.bounce.BounceInLeftAnimation;
import me.splm.wevision.animation.bounce.BounceInRightAnimation;
import me.splm.wevision.animation.bounce.BounceInUpAnimation;
import me.splm.wevision.animation.fadeIn.FadeInLeftAnimation;
import me.splm.wevision.animation.fadeIn.FadeInRightAnimation;
import me.splm.wevision.animation.flip.FlipInXAnimation;
import me.splm.wevision.animation.flip.FlipInYAnimation;
import me.splm.wevision.animation.flip.FlipOutXAnimation;
import me.splm.wevision.animation.flip.FlipOutYAnimation;
import me.splm.wevision.animation.scaling.ScaleInAnimation;
import me.splm.wevision.animation.scaling.ScaleOutAnimation;

public enum Vision {
	
	FadeInLeft(FadeInLeftAnimation.class),
	FadeInRight(FadeInRightAnimation.class),
	
	Flash(FlashAnimation.class),
	TakeOff(TakeOffAnimation.class),
	
	FlipInX(FlipInXAnimation.class),
	FlipOutX(FlipOutXAnimation.class),
	FlipInY(FlipInYAnimation.class),
	FlipOutY(FlipOutYAnimation.class),
	
	DropOut(DropOutAnimation.class),
	Shake(ShakeAnimation.class),
	ShakeHarder(ShakeHarderAnimation.class),
	
	BouncingIn(BounceInAnimation.class),
	BouncingInDown(BounceInDownAnimation.class),
	BouncingInLeft(BounceInLeftAnimation.class),
	BouncingInRight(BounceInRightAnimation.class),
	BouncingInUp(BounceInUpAnimation.class),
	
	ScaleIn(ScaleInAnimation.class),
	ScaleOut(ScaleOutAnimation.class);
	
	private Class<? extends AbsBaseAnimation> animatorClazz;

    private Vision(Class<? extends AbsBaseAnimation> clazz) {
        animatorClazz = clazz;
    }
    
    public AbsBaseAnimation getAnimator() {
        try {
            return (AbsBaseAnimation) animatorClazz.newInstance();
        } catch (Exception e) {
            throw new Error("Could not init WeVision instance!");
        }
    }
}
