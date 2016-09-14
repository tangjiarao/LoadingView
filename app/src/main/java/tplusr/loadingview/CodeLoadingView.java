package tplusr.loadingview;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.AnimationDrawable;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import tplusr.library.BaseLoadingView;

/**
 * Created by tangjiarao on 16/7/15.
 *
 * 默认加载view
 */
public class CodeLoadingView extends BaseLoadingView {

    //帧动画
    private AnimationDrawable AniDraw;
    private Context context;

    //loadImageview
    private ImageView loadImageView;

    //加载图标
    private int loadImage=R.drawable.load_animation;


    private Button bt;

    public CodeLoadingView(Context context) {
        super(context);
        this.context =context;
        setInitView();
        setReFreshView();
        setListener();
    }

    /**
     * 设置开始加载时的界面
     */
    public void setInitView(){

        //设置relativeLayout布局
        LayoutParams mParams = new LayoutParams(
                LayoutParams.WRAP_CONTENT,
                LayoutParams.WRAP_CONTENT);

        mParams.addRule(RelativeLayout.CENTER_IN_PARENT);

        //加载过程中的图标
        loadImageView = new ImageView(context);

        //loadImage设置背景
        loadImageView.setBackgroundResource(loadImage);
        AniDraw = (AnimationDrawable) loadImageView.getBackground();

        super.setInitView(loadImageView,mParams);

    }

    /**
     * 获取回调
     * 这里需要监听动画完成，然后停止图片转动。
     */
    public void setListener() {
        super.getDisappearAnimation().setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                AniDraw.stop();
            }

            @Override
            public void onAnimationEnd(Animation animation) {

            }
            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        super.getAppearAnimation().setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                AniDraw.start();
            }

            @Override
            public void onAnimationEnd(Animation animation) {

            }
            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }
    public void setOnClickListener(OnClickListener onClickListener){
        bt.setOnClickListener(onClickListener);
    }

    /**
     * 设置更新的界面
     */
    public void setReFreshView(){

        LayoutParams mParams = new LayoutParams(
                LayoutParams.WRAP_CONTENT,
                LayoutParams.WRAP_CONTENT);

        mParams.addRule(RelativeLayout.CENTER_IN_PARENT);
        bt =new Button(context);
        bt.setText("选择");
        bt.setTextColor(Color.WHITE);
        bt.setBackgroundResource(R.drawable.btn_selector);

        super.setRefreshView(bt, mParams);
    }

}
