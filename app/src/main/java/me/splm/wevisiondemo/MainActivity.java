package me.splm.wevisiondemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.nineoldandroids.animation.Animator;

import java.util.ArrayList;
import java.util.List;

import me.splm.wevision.AbsBaseAnimation;
import me.splm.wevision.Vision;
import me.splm.wevision.WeVisionEngine;


public class MainActivity extends AppCompatActivity {
    private static final String TAG="WEVISION";
    private List<SampleAnimModel> list=new ArrayList<>();

    private LinearLayout target_container;
    private ListView animation_list_lv;
    private TextView target_tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    private void init(){
        //init views
        target_container=(LinearLayout) findViewById(R.id.target_container);
        animation_list_lv=(ListView) findViewById(R.id.animation_list_lv);
        target_tv=(TextView) findViewById(R.id.target_tv);
        //init data
        String[] names={"FadeInNoDir","FadeOut","FadeInLeft","FadeInRight","FadeInUp","FadeInDown","FlipInX","FlipOutX","FlipInY","FlipOutY","DropOut",
                "Shake","ShakeHarder","BouncingIn","BouncingInDown","BouncingInLeft",
                "BouncingInRight","BouncingInUp","ScaleIn","ScaleOut","Flash","TakeOff"};
        Vision[] visions={Vision.FadeInNoDir,Vision.FadeOut,Vision.FadeInLeft, Vision.FadeInRight,Vision.FadeInUp,Vision.FadeInDown,Vision.FlipInX,
                Vision.FlipOutX, Vision.FlipInY, Vision.FlipOutY,
                Vision.DropOut, Vision.Shake, Vision.ShakeHarder, Vision.BouncingIn, Vision.BouncingInDown,
                Vision.BouncingInLeft, Vision.BouncingInRight, Vision.BouncingInUp, Vision.ScaleIn, Vision.ScaleOut,
                Vision.Flash, Vision.TakeOff};
        for(int i=0;i<names.length;i++){
            SampleAnimModel model=new SampleAnimModel();
            model.setName(names[i]);
            model.setVision(visions[i]);
            list.add(model);
        }
        SampleAdapter adapter=new SampleAdapter(this, list, R.layout.activity_sample_animation_item);
        animation_list_lv.setAdapter(adapter);
        /*******************************************/
        WeVisionEngine.WeVisionStatus status=WeVisionEngine.use(Vision.FadeInLeft).addListener(new AbsBaseAnimation.WeVisionAnimatorListener() {
            @Override
            public void onStart(Animator animator) {
                Log.e(TAG,"The animations of whole views are starting!");
            }

            @Override
            public void onEnd() {
                Log.e(TAG,"All of animations have completed!");
            }

        }).playOn(target_container);
        boolean isRunning=status.isRunning();
        boolean isEnd=status.isEnd();
        Log.e(TAG,"isRunning==="+isRunning+"-----isEnd==="+isEnd);
        status.stop();

        //bind events
        animation_list_lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Vision v=list.get(position).getVision();
                WeVisionEngine.use(v).playOn(target_tv);
            }
        });
    }
}
