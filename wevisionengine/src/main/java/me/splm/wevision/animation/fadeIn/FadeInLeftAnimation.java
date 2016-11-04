package me.splm.wevision.animation.fadeIn;

import android.view.View;

import com.nineoldandroids.animation.AnimatorSet;
import com.nineoldandroids.animation.ObjectAnimator;

import me.splm.wevision.AbsBaseAnimation;

public class FadeInLeftAnimation extends AbsBaseAnimation {

	@Override
	protected void animate(AnimatorSet set, View target, int index) {
		set.playTogether(
				ObjectAnimator.ofFloat(target, "alpha", 0, 1),
				ObjectAnimator.ofFloat(target, "translationX", -target.getMeasuredWidth(), 0));
	}

}
