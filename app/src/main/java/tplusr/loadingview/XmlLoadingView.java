package tplusr.loadingview;

import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.ImageView;

import tplusr.library.BaseLoadingView;

/**
 * Created by tangjiarao on 16/9/1.
 * 使用xml布局文件设置LoadingView样式
 */
public class XmlLoadingView extends BaseLoadingView {

    //帧动画
    private AnimationDrawable AniDraw;
    private Context context;

    //loadImageview
    private ImageView loadImageView;

    LayoutInflater flater ;

    private Button b1 , b2;

    public XmlLoadingView(Context context) {
        super(context);
        this.context=context;
        flater= LayoutInflater.from(context);
        setInitView();
        setReFreshView();
        setListener();
    }

    /**
     * 设置开始加载时的界面
     */
    private void setInitView(){

        View view = flater.inflate(R.layout.init_view, null);
        loadImageView=(ImageView)view.findViewById(R.id.loadimage);
        loadImageView.setBackgroundResource(R.drawable.load_animation);
        AniDraw = (AnimationDrawable) loadImageView.getBackground();

        super.setInitView(view, null);
    }

    /**
     * 设置更新的界面
     */
    private void setReFreshView(){

        View view = flater.inflate(R.layout.refresh_view, null);
        super.setRefreshView(view, null);
        b1 =(Button)view.findViewById(R.id.bt1);
        b2 =(Button)view.findViewById(R.id.bt2);
    }

    /**
     * 获取回调
     */
    public void setListener(){
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
        b1.setOnClickListener(onClickListener);
        b2.setOnClickListener(onClickListener);
    }
}
