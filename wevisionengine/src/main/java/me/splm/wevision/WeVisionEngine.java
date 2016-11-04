package me.splm.wevision;

import android.view.View;

import com.nineoldandroids.animation.Animator;

import java.util.ArrayList;
import java.util.List;


public class WeVisionEngine {
	
	public static WeAnimationComposer use(Vision v){
		return new WeAnimationComposer(v);
	}
	
	public static final class WeAnimationStatus{
		
		private AbsBaseAnimation mAnimation;
		
		public WeAnimationStatus(AbsBaseAnimation animation){
			this.mAnimation=animation;
		}
		
		public void stop(){
			mAnimation.stop();
		}
		
		public boolean isRunning(){
			return mAnimation.isRunning();
		}
		
		public boolean isStarted(){
			return mAnimation.isStarted();
		}
	}
	
	public static final class WeAnimationComposer{
		
		private AbsBaseAnimation mAnimation;
		private long mDuration=AbsBaseAnimation.DURATION;
		private List<Animator.AnimatorListener> callbacks=new ArrayList<>();
		private AbsBaseAnimation.WeVisionAnimatorListener mListener;
		
		public WeAnimationComposer(Vision v){
			this.mAnimation=v.getAnimator();
		}
		public WeAnimationComposer duration(long timemills){
			this.mDuration=timemills;
			return this;
		}
		
		public WeAnimationComposer addListener(AbsBaseAnimation.WeVisionAnimatorListener listener){
			//callbacks.add(listener);
			this.mListener=listener;
			return this;
		}
		
		public WeAnimationStatus playOn(View target){
			mAnimation.duration(mDuration);
			mAnimation.setView(target);
			//
			/*if(!callbacks.isEmpty()){
				for(Animator.AnimatorListener listener:callbacks){
					mAnimation.addAnimatorListener(listener);
				}
			}*/
			mAnimation.start();
			mAnimation.addListener(mListener);
			return new WeAnimationStatus(mAnimation);
		}
		
		public interface AnimatorCallBack{
			void call(Animator animator);
		}
		
		protected WeAnimationComposer onStart(final AnimatorCallBack callback){
			callbacks.add(new EasyAnimationListener(){
				@Override
				public void onAnimationStart(Animator animator) {
					callback.call(animator);
				}
			});
			return this;
		}
		
		protected WeAnimationComposer onCancel(final AnimatorCallBack callback){
			callbacks.add(new EasyAnimationListener(){
				@Override
				public void onAnimationCancel(Animator animator) {
					callback.call(animator);
				}
			});
			return this;
		}
		
		protected WeAnimationComposer onEnd(final AnimatorCallBack callback){
			callbacks.add(new EasyAnimationListener(){
				@Override
				public void onAnimationEnd(Animator animator) {
					callback.call(animator);
				}
			});
			return this;
		} 
	}
	
	public static class EasyAnimationListener implements Animator.AnimatorListener{

		@Override
		public void onAnimationCancel(Animator animator) {}

		@Override
		public void onAnimationEnd(Animator animator) {}

		@Override
		public void onAnimationRepeat(Animator animator) {}

		@Override
		public void onAnimationStart(Animator animator) {}
	}
}
