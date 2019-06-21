package me.splm.wevision.animation.fadeIn;

import android.view.View;

import com.nineoldandroids.animation.AnimatorSet;
import com.nineoldandroids.animation.ObjectAnimator;

import me.splm.wevision.AbsBaseAnimation;

public class FadeOutAnimation extends AbsBaseAnimation {

    @Override
    protected void animate(AnimatorSet set, View target, int index) {
        set.playTogether(
                ObjectAnimator.ofFloat(target, "alpha", 1, 0),
                ObjectAnimator.ofFloat(target, "scaleX", 1.05f, 0.7f, 0.5f, 0.3f),
                ObjectAnimator.ofFloat(target, "scaleY", 1.05f, 0.7f, 0.5f, 0.3f)
        );
    }

}
