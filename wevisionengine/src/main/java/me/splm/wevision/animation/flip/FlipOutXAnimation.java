package me.splm.wevision.animation.flip;

import android.view.View;

import com.daimajia.easing.Glider;
import com.daimajia.easing.Skill;
import com.nineoldandroids.animation.AnimatorSet;
import com.nineoldandroids.animation.ObjectAnimator;

import me.splm.wevision.AbsBaseAnimation;

public class FlipOutXAnimation extends AbsBaseAnimation {

	@Override
	protected void animate(AnimatorSet set, View target, int index) {
		set.playSequentially(
				Glider.glide(Skill.BackEaseIn,getDuration(),ObjectAnimator.ofFloat(target, "rotationX", 0, 90)),
                ObjectAnimator.ofFloat(target, "alpha", 1, 0)
        );
	}

}
