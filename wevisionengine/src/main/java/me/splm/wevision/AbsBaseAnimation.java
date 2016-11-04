package me.splm.wevision;

import android.util.Log;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.ViewGroup;

import com.nineoldandroids.animation.Animator;
import com.nineoldandroids.animation.AnimatorSet;
import com.nineoldandroids.view.ViewHelper;

import java.util.LinkedList;
import java.util.Queue;

public abstract class AbsBaseAnimation {
	
	public static final long DURATION=1000;
	public static final long DELAY=100;

	private long mDuration;
	private Queue<AnimatorSet> mAnimatorSetQueue=new LinkedList<>();
	private AnimatorSet mAnimatorSet;
	private int queueCount;

	{
		mAnimatorSet=new AnimatorSet();
	}

	public AbsBaseAnimation duration(long timemills){
		this.mDuration=timemills;
		return this;
	}
	protected abstract void animate(AnimatorSet set,View target,int index);

	/** Measure the target view. */
	private void measureTarget(View target){
		int height=MeasureSpec.makeMeasureSpec(0,MeasureSpec.UNSPECIFIED);
		int width=MeasureSpec.makeMeasureSpec(0,MeasureSpec.UNSPECIFIED);
		target.measure(width,height);
	}
	
	public void setView(View target){//1
		resetEffection(target);
		prepare(target);
	}

	protected void prepare(View target){//2
		treeViews(target);
	}

	private void treeViews(View target){//3
		if(target instanceof ViewGroup){
			ViewGroup group=(ViewGroup)target;
			int count=group.getChildCount();
			if(count>0){
				for (int i = 0; i < count; i++) {
					View child = group.getChildAt(i);
					confirmAnimation(child,i);
				}
			}
			return;
		}else{
			confirmAnimation(target,0);
		}
	}

	/** Make sure to set animations on the target view which has measured.*/
	private void confirmAnimation(View target,int index){//4
		measureTarget(target);
		AnimatorSet set=new AnimatorSet();
		mAnimatorSetQueue.offer(set);
		composeAnimator(set,target,index);
		//animate(target, index);//5
	}

	/** Start the animation of current collection. */
	public void start(){
		int i=0;
		for(AnimatorSet set:mAnimatorSetQueue){
			set.setDuration(mDuration);
			set.setStartDelay(delays(i));
			set.start();//6
			i++;
		}
	}

	private void composeAnimator(AnimatorSet set,View view, int index){
		animate(set,view,index);
	}

	/** reset all animations.
	 * @param  target view
	 * */
	protected void resetEffection(View target){
		ViewHelper.setAlpha(target, 1);
        ViewHelper.setScaleX(target, 1);
        ViewHelper.setScaleY(target, 1);
        ViewHelper.setTranslationX(target, 0);
        ViewHelper.setTranslationY(target, 0);
        ViewHelper.setRotation(target, 0);
        ViewHelper.setRotationY(target, 0);
        ViewHelper.setRotationX(target, 0);
        ViewHelper.setPivotX(target, target.getMeasuredWidth() / 2.0f);
        ViewHelper.setPivotY(target, target.getMeasuredHeight() / 2.0f);
	}
	
	protected long delays(int index){
		return DELAY + index * DELAY;
	}
	
	protected long getDuration(){
		return this.mDuration;
	}

	/******************************************************************/
	public void addAnimatorListener(Animator.AnimatorListener listener){
		mAnimatorSet.addListener(listener);
	}

	public void addListener(final WeVisionAnimatorListener listener){
		final Queue<AnimatorSet> queue=getAnimatorQueue();
		int size=queue.size();
		for(int i=0;i<size;i++){
			queueCount=i;
			AnimatorSet node=queue.poll();
			node.addListener(new Animator.AnimatorListener() {
				@Override
				public void onAnimationStart(Animator animation) {
					listener.onStart(animation);
				}

				@Override
				public void onAnimationEnd(Animator animation) {
					int size=queue.size();
					if(size==0){
						listener.onEnd(animation);
						Log.e("/***********","end");
					}
				}

				@Override
				public void onAnimationCancel(Animator animation) {

				}

				@Override
				public void onAnimationRepeat(Animator animation) {

				}
			});
		}
	}
	
	public void stop(){
		mAnimatorSet.cancel();
	}
	
	public boolean isRunning(){
		return mAnimatorSet.isRunning();
	}
	
	public boolean isStarted(){
		return mAnimatorSet.isStarted();
	}
	
	protected AnimatorSet getAnimationSet(){
		return this.mAnimatorSet;
	}

	protected  Queue<AnimatorSet> getAnimatorQueue(){
		return this.mAnimatorSetQueue;
	}
	public interface WeVisionAnimatorListener{
		void onStart(Animator animator);
		void onEnd(Animator animator);
		void onCancel(Animator animator);
	}
}
