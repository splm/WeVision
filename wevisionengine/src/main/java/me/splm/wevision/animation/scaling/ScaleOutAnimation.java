package me.splm.wevision.animation.scaling;

import android.view.View;

import com.nineoldandroids.animation.AnimatorSet;
import com.nineoldandroids.animation.ObjectAnimator;

import me.splm.wevision.AbsBaseAnimation;


public class ScaleOutAnimation extends AbsBaseAnimation {

	@Override
	protected void animate(AnimatorSet set, View view, int index) {
		set.playTogether(
				ObjectAnimator.ofFloat(view,"scaleX",1f,1.05f,0.9f,0.3f,0),
				ObjectAnimator.ofFloat(view,"scaleY",1f,1.05f,0.9f,0.3f,0),
				ObjectAnimator.ofFloat(view,"alpha",1,0)
		);
	}
}
