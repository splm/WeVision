package me.splm.wevisiondemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

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
    private LinearLayout target_tv_container;
    private ListView animation_list_lv;
    private TextView target_tv;
    private Button stop_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    private void init(){
        //init views
        target_container=(LinearLayout) findViewById(R.id.target_container);
        target_tv_container=(LinearLayout) findViewById(R.id.target_tv_container);
        animation_list_lv=(ListView) findViewById(R.id.animation_list_lv);
        target_tv=(TextView) findViewById(R.id.target_tv);
        stop_btn=(Button) findViewById(R.id.stop_btn);
        //init data
        String[] names={"FadeInLeft","FadeInRight","FlipInX","FlipOutX","FlipInY","FlipOutY","DropOut",
                "Shake","ShakeHarder","BouncingIn","BouncingInDown","BouncingInLeft",
                "BouncingInRight","BouncingInUp","ScaleIn","ScaleOut","Flash","TakeOff"};
        Vision[] visions={Vision.FadeInLeft, Vision.FadeInRight,Vision.FlipInX, Vision.FlipOutX, Vision.FlipInY, Vision.FlipOutY,
                Vision.DropOut, Vision.Shake, Vision.ShakeHarder, Vision.BouncingIn, Vision.BouncingInDown,
                Vision.BouncingInLeft, Vision.BouncingInRight, Vision.BouncingInUp, Vision.ScaleIn, Vision.ScaleOut, Vision.Flash, Vision.TakeOff};
        for(int i=0;i<names.length;i++){
            SampleAnimModel model=new SampleAnimModel();
            model.setName(names[i]);
            model.setVision(visions[i]);
            list.add(model);
        }
        SampleAdapter adapter=new SampleAdapter(this, list, R.layout.activity_sample_animation_item);
        animation_list_lv.setAdapter(adapter);
        /*******************************************/
        final WeVisionEngine.WeVisionStatus status=WeVisionEngine.use(Vision.FadeInLeft).duration(5000).addListener(new AbsBaseAnimation.WeVisionAnimatorListener() {
            @Override
            public void onStart(Animator animator) {
                Log.e(TAG,"onstart");
            }

            @Override
            public void onEnd() {
                Log.e(TAG,"onend");
            }

        }).playOn(target_tv_container);

        stop_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this,"--"+status.isRunning(),Toast.LENGTH_SHORT).show();
            }
        });

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
