package tplusr.loadingview;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private XmlLoadingView xmlLoadingView;
    private CodeLoadingView codeLoadingView;
    private RelativeLayout contentLayout;
    private Button bt1,bt2,bt3,bt4;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();
    }

    /**
     * 初始化
     */
    public void initViews(){

        contentLayout = (RelativeLayout)findViewById(R.id.contentLayout);
        codeLoadingView=new CodeLoadingView(MainActivity.this);
        xmlLoadingView =new XmlLoadingView(MainActivity.this);
        bt1=(Button)findViewById(R.id.bt1);
        bt2=(Button)findViewById(R.id.bt2);
        bt3=(Button)findViewById(R.id.bt3);
        bt4=(Button)findViewById(R.id.bt4);
        bt1.setOnClickListener(this);
        bt2.setOnClickListener(this);
        bt3.setOnClickListener(this);
        bt4.setOnClickListener(this);

        /**
         * 动画消失时要处理的事情
         */
        xmlLoadingView.getDisappearAnimation().setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                bt1.setVisibility(View.VISIBLE);
                bt2.setVisibility(View.VISIBLE);
                bt3.setVisibility(View.VISIBLE);
                bt4.setVisibility(View.VISIBLE);

                contentLayout.removeView(xmlLoadingView);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        codeLoadingView.getDisappearAnimation().setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                bt1.setVisibility(View.VISIBLE);
                bt2.setVisibility(View.VISIBLE);
                bt3.setVisibility(View.VISIBLE);
                bt4.setVisibility(View.VISIBLE);

                contentLayout.removeView(codeLoadingView);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

    }

    /**
     * 模拟延迟加载
     * @param flag
     */
    public void loading(final int flag){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Message msg = new Message();
                msg.what=flag;
                mHandler.sendMessage(msg);
            }
        }).start();
    }

    /**
     * 点击事件
     * @param v
     */
    public void onClick(View v) {
        bt1.setVisibility(View.INVISIBLE);
        bt2.setVisibility(View.INVISIBLE);
        bt3.setVisibility(View.INVISIBLE);
        bt4.setVisibility(View.INVISIBLE);
        switch (v.getId()){
            case R.id.bt1:
                xmlLoadingView.setViewSize(250, 150);
                xmlLoadingView.setViewColor(R.color.colorAccent);
                xmlLoadingView.startAppearAnimation();
                contentLayout.addView(xmlLoadingView);
                loading(1);
                break;
            case R.id.bt2:
                xmlLoadingView.setViewSize(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
                xmlLoadingView.setViewColor(R.color.baseLoadingView_bg);
                //1.开始布局显示动画
                xmlLoadingView.startAppearAnimation();
                //2.增加该控件
                contentLayout.addView(xmlLoadingView);
                loading(2);
                break;
            case R.id.bt3:
                xmlLoadingView.setViewSize(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
                codeLoadingView.startAppearAnimation();
                contentLayout.addView(codeLoadingView);
                loading(3);
                break;
            case R.id.bt4:
                codeLoadingView.setViewSize(250, 200);
                codeLoadingView.startAppearAnimation();
                contentLayout.addView(codeLoadingView);
                loading(4);
                break;

        }
    }
    private Handler mHandler=new Handler(){
        public void handleMessage(Message msg) {
            if (msg.what==1){
                xmlLoadingView.startDisappearAnimation();
            }
            else if (msg.what==2){
                //为refresh布局的按钮增加监听
                xmlLoadingView.setOnClickListener(new View.OnClickListener() {

                    public void onClick(View view) {
                        if (view.getId() == R.id.bt1) {
                            Toast toast = Toast.makeText(MainActivity.this, "选择1", Toast.LENGTH_SHORT);
                            toast.show();
                            //4.开始布局消失动画
                            xmlLoadingView.startDisappearAnimation();

                        } else {
                            Toast toast = Toast.makeText(MainActivity.this, "选择2", Toast.LENGTH_SHORT);
                            toast.show();
                            xmlLoadingView.startDisappearAnimation();
                        }

                    }
                });
                //3.刷新布局
                xmlLoadingView.refreshView();
            }
            else if (msg.what==3){
                codeLoadingView.startDisappearAnimation();
            }
            else {

                codeLoadingView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Toast toast = Toast.makeText(MainActivity.this, "选择", Toast.LENGTH_SHORT);
                        toast.show();
                        codeLoadingView.startDisappearAnimation();

                    }
                });
                codeLoadingView.refreshView();
            }

        }

    };
}
