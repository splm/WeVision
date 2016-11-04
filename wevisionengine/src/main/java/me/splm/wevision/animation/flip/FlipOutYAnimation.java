package me.splm.wevision.animation.flip;

import android.view.View;

import com.nineoldandroids.animation.AnimatorSet;
import com.nineoldandroids.animation.ObjectAnimator;

import me.splm.wevision.AbsBaseAnimation;

public class FlipOutYAnimation extends AbsBaseAnimation {

	@Override
	protected void animate(AnimatorSet set, View target, int index) {
		set.playSequentially(
				ObjectAnimator.ofFloat(target, "rotationY", 0, -15,90),
                ObjectAnimator.ofFloat(target, "alpha", 1, 0)
        );
	}

}
