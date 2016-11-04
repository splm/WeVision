package me.splm.wevision.animation.scaling;

import android.view.View;

import com.daimajia.easing.Glider;
import com.daimajia.easing.Skill;
import com.nineoldandroids.animation.AnimatorSet;
import com.nineoldandroids.animation.ObjectAnimator;

import me.splm.wevision.AbsBaseAnimation;


public class ScaleInAnimation extends AbsBaseAnimation {
	
	@Override
	protected void animate(AnimatorSet set, View view, int index){
		set.playTogether(
				Glider.glide(Skill.BackEaseIn,getDuration(),ObjectAnimator.ofFloat(view,"scaleX",0.3f,1.05f,0.9f,1)),
				Glider.glide(Skill.BackEaseIn,getDuration(),ObjectAnimator.ofFloat(view,"scaleY",0.3f,1.05f,0.9f,1)),
				ObjectAnimator.ofFloat(view,"alpha",0f,1f)
		);
	}
}
