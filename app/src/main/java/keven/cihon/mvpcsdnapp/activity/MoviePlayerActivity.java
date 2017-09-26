//package keven.cihon.mvpcsdnapp.activity;
//
//import android.os.Bundle;
//import android.support.v7.app.AppCompatActivity;
//import android.view.MotionEvent;
//import android.view.SurfaceHolder;
//import android.view.SurfaceView;
//import android.view.View;
//import android.widget.Button;
//import android.widget.LinearLayout;
//
//import java.io.IOException;
//
//import butterknife.BindView;
//import butterknife.ButterKnife;
//import butterknife.OnClick;
//import keven.cihon.mvpcsdnapp.R;
//import tv.danmaku.ijk.media.player.IMediaPlayer;
//import tv.danmaku.ijk.media.player.IjkMediaPlayer;
//
//public class MoviePlayerActivity extends AppCompatActivity implements SurfaceHolder.Callback, IMediaPlayer.OnPreparedListener, IMediaPlayer.OnCompletionListener, IMediaPlayer.OnBufferingUpdateListener {
//
//    @BindView(R.id.surfaceview)
//    SurfaceView mSurfaceview;
//    @BindView(R.id.bt_stop)
//    Button mBtStop;
//    @BindView(R.id.bt_play)
//    Button mBtPlay;
//    @BindView(R.id.linearlayout)
//    LinearLayout mLinearlayout;
//    private IjkMediaPlayer mediaPlayer;
//    private SurfaceHolder mHolder;
//    private boolean f=true;
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_movie_player);
//        ButterKnife.bind(this);
//
//        mHolder = mSurfaceview.getHolder();
//        mHolder.addCallback(this);
//        mediaPlayer =new IjkMediaPlayer();
//
//        try {
////            mediaPlayer.setDataSource("rtmp://203.207.99.19:1935/live/CCTV5");
//            mediaPlayer.setDataSource("http://9890.vod.myqcloud.com/9890_4e292f9a3dd011e6b4078980237cc3d3.f20.mp4");
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
////mediaPlayer准备工作
//        mediaPlayer.setOnPreparedListener(this);
//        //MediaPlayer完成
//        mediaPlayer.setOnCompletionListener(this);
//
//        mediaPlayer.setOnBufferingUpdateListener(this);//当前加载进度的监听
//
////当触摸surfaceview时显示或消失底部按钮
//        mSurfaceview.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//
//                if (event.getAction()==(MotionEvent.ACTION_DOWN))
//                {
//                    if (f==true)
//                    {
//                        f=false;
//                        mLinearlayout.setVisibility(View.VISIBLE);
//                    }else {
//                        f=true;
//                        mLinearlayout.setVisibility(View.GONE);
//                    }
//                }
//
//                return true;
//            }
//        });
//
//
//    }
//
//    @OnClick({R.id.bt_stop, R.id.bt_play})
//    public void onViewClicked(View view) {
//        switch (view.getId()) {
//            case R.id.bt_stop:
//                mediaPlayer.pause();
//                break;
//            case R.id.bt_play:
//                mediaPlayer.start();
//                break;
//        }
//    }
//
//    @Override
//    public void surfaceCreated(SurfaceHolder holder) {
//        //连接ijkPlayer 和surfaceHOLDER
//        mediaPlayer.setDisplay(holder);
//        //开启异步准备
//        mediaPlayer.prepareAsync();
//    }
//
//    @Override
//    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
//
//    }
//
//    @Override
//    public void surfaceDestroyed(SurfaceHolder holder) {
//
//    }
//
//    @Override
//    public void onBufferingUpdate(IMediaPlayer iMediaPlayer, int i) {
//
//    }
//
//    @Override
//    public void onCompletion(IMediaPlayer iMediaPlayer) {
//        iMediaPlayer.seekTo(0);
//        iMediaPlayer.start();
//    }
//
//    @Override
//    public void onPrepared(IMediaPlayer iMediaPlayer) {
//        iMediaPlayer.start();
//    }
//
//    @Override
//    protected void onDestroy() {
//        super.onDestroy();
//        mediaPlayer.stop();
//        mediaPlayer.release();
//
//    }
//}
