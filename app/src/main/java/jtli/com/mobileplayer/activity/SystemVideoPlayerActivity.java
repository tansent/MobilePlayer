package jtli.com.mobileplayer.activity;

import android.app.Activity;
import android.media.MediaPlayer;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.MediaController;
import android.widget.VideoView;

import com.blankj.utilcode.utils.ToastUtils;

import jtli.com.mobileplayer.R;

public class SystemVideoPlayerActivity extends Activity {

    private VideoView videoView;
    private Uri uri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_system_video_player);
        videoView = (VideoView)findViewById(R.id.videoView);

        videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                videoView.start();
                videoView.requestFocus(); //optional
            }
        });

        videoView.setOnErrorListener(new MediaPlayer.OnErrorListener() {
            @Override
            public boolean onError(MediaPlayer mp, int what, int extra) {
                ToastUtils.showShortToast("video play error");
                return false; // a dialog popup
            }
        });

        videoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                ToastUtils.showLongToast("video play complete, uri="+uri.toString());
            }
        });

        uri = getIntent().getData();
        if (uri != null){
            videoView.setVideoURI(uri);
        }

        videoView.setMediaController(new MediaController(this));


        //----------------------------------------------------------
//        VideoView mVv = (VideoView) findViewById(R.id.videoView);
//        //添加播放控制条,还是自定义好点
//        mVv.setMediaController(new MediaController(this));
//
//        //设置视频源播放res/raw中的文件,文件名小写字母,格式: 3gp,mp4等,flv的不一定支持;
//        //        Uri rawUri = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.shuai_dan_ge);
//        uri = getIntent().getData();
//        mVv.setVideoURI( getIntent().getData());
//        // 播放在线视频
//        //        mVideoUri = Uri.parse("http://****/abc.mp4");
//        //        mVv.setVideoPath(mVideoUri.toString());
//
//        mVv.setOnPreparedListener(new MyOnPreparedListener());
//        mVv.setOnErrorListener(new MyOnErrorListener());
//        mVv.setOnCompletionListener(new MyOnCompleteListener());
//
//
//        mVv.start();
//        mVv.requestFocus();
//
//
//    }
//
//    class MyOnPreparedListener implements MediaPlayer.OnPreparedListener{
//        @Override
//        public void onPrepared(MediaPlayer mp) {
//
//        }
//    }
//
//    class MyOnErrorListener implements MediaPlayer.OnErrorListener{
//        @Override
//        public boolean onError(MediaPlayer mp, int what, int extra) {
//            ToastUtils.showShortToast("video play error");
//            return false;
//        }
//    }
//
//    class MyOnCompleteListener implements MediaPlayer.OnCompletionListener{
//        @Override
//        public void onCompletion(MediaPlayer mp) {
//            ToastUtils.showLongToast("video play complete, uri="+uri.toString());
//        }
    }

}
