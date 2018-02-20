package jtli.com.mobileplayer.fragment;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import jtli.com.mobileplayer.R;
import jtli.com.mobileplayer.activity.SystemVideoPlayerActivity;
import jtli.com.mobileplayer.adapter.VideoAdapter;
import jtli.com.mobileplayer.base.BaseFragment;
import jtli.com.mobileplayer.bean.MediaItem;
import jtli.com.mobileplayer.injector.component.DaggerVideoComponent;
import jtli.com.mobileplayer.injector.module.VideoModule;
import jtli.com.mobileplayer.presenter.VideoPresenter;
import jtli.com.mobileplayer.presenter.impl.VideoPresenterImpl;
import jtli.com.mobileplayer.utils.LogUtil;

/**
 * Created by Jingtian(Tansent).
 */

public class VideoFragment extends BaseFragment<VideoPresenterImpl> implements VideoPresenter.View {

//    private ListView listview;


    @BindView(R.id.rv_Video)
    RecyclerView rvVideo;
//    @BindView(R.id.tv_nomedia)
//    TextView tvNomedia;

    private VideoAdapter videoAdapter;

    /**
     * 装数据集合
     */
    private ArrayList<MediaItem> mediaItems;

//    private Handler handler = new Handler(){
//        @Override
//        public void handleMessage(Message msg) {
//            super.handleMessage(msg);
//            if(mediaItems != null && mediaItems.size() >0){
//                //有数据
//                //设置适配器
////                videoPagerAdapter = new VideoPagerAdapter(context,mediaItems,true);
////                listview.setAdapter(videoPagerAdapter);
//                //把文本隐藏
//                tv_nomedia.setVisibility(View.GONE);
//            }else{
//                //没有数据
//                //文本显示
//                tv_nomedia.setVisibility(View.VISIBLE);
//            }
//
//            //ProgressBar隐藏
//            pb_loading.setVisibility(View.GONE);
//
//
//        }
//    };

    @Override
    protected void initInject() {
        DaggerVideoComponent.builder()
                .videoModule(new VideoModule())
                .build().injectVideo(this);
    }

    @Override
    protected void loadData() {
//        setState(AppConstants.STATE_SUCCESS);
//        LogUtil.e("Video initialized");
//        getDataFromLocal();
        mPresenter.fetchVideoData();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.video_pager;
    }

    @Override
    protected void initView() {
        LogUtil.e("Video initialized initView");
        View view = View.inflate(getContext(), R.layout.video_pager, null);
        //if there is a butterknife bind,
        //must use annotation to get view items
//        listview = (ListView) view.findViewById(R.id.listview);
//        tv_nomedia = (TextView) view.findViewById(R.id.tv_nomedia);
//        pb_loading = (ProgressBar) view.findViewById(R.id.pb_loading);
//        ll_pb_loading = (LinearLayout) view.findViewById(R.id.ll_pb_loading);

//        rvVideo = (RecyclerView) view.findViewById(R.id.rv_Video);
        rvVideo.setLayoutManager(new LinearLayoutManager(getActivity()));
        rvVideo.setAdapter(mAdapter);
        ((VideoAdapter)mAdapter).setOnItemClickListener(new VideoAdapter.OnItemClickListener() {
            @Override
            public void onItemClickListener(String data) {
                Intent intent = new Intent(getContext(), SystemVideoPlayerActivity.class);
                intent.setDataAndType(Uri.parse(data), "video/*");
                getActivity().startActivity(intent);
            }
        });
    }

//    /**
//     * 从本地的sdcard得到数据
//     * //1.遍历sdcard,后缀名
//     * //2.从内容提供者里面获取视频
//     * //3.如果是6.0的系统，动态获取读取sdcard的权限
//     */
//    private void getDataFromLocal() {
//
//        new Thread(){
//            @Override
//            public void run() {
//                super.run();
//
////                isGrantExternalRW((Activity) context);
////                SystemClock.sleep(2000);
//                mediaItems = new ArrayList<>();
//                ContentResolver resolver = getContext().getContentResolver();
//                Uri uri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
//                String[] objs = {
//                        MediaStore.Video.Media.DISPLAY_NAME,//视频文件在sdcard的名称
//                        MediaStore.Video.Media.DURATION,//视频总时长
//                        MediaStore.Video.Media.SIZE,//视频的文件大小
//                        MediaStore.Video.Media.DATA,//视频的绝对地址
//                        MediaStore.Video.Media.ARTIST,//歌曲的演唱者
//
//                };
//                Cursor cursor = resolver.query(uri, objs, null, null, null);
//                if(cursor != null){
//                    while (cursor.moveToNext()){
//
//                        MediaItem mediaItem = new MediaItem();
//
//                        mediaItems.add(mediaItem);//写在上面
//
//                        String name = cursor.getString(0);//视频的名称
//                        mediaItem.setName(name);
//
//                        long duration = cursor.getLong(1);//视频的时长
//                        mediaItem.setDuration(duration);
//
//                        long size = cursor.getLong(2);//视频的文件大小
//                        mediaItem.setSize(size);
//
//                        String data = cursor.getString(3);//视频的播放地址
//                        mediaItem.setData(data);
//
//                        String artist = cursor.getString(4);//艺术家
//                        mediaItem.setArtist(artist);
//
//
//
//                    }
//
//                    cursor.close();
//
//
//                }
//
//
//                //Handler发消息
//                handler.sendEmptyMessage(10);
//
//
//            }
//        }.start();
//
//    }

    /**
     * 解决安卓6.0以上版本不能读取外部存储权限的问题
     *
     * @param activity
     * @return
     */
    public static boolean isGrantExternalRW(Activity activity) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && activity.checkSelfPermission(
                Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {

            activity.requestPermissions(new String[]{
                    Manifest.permission.READ_EXTERNAL_STORAGE,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE
            }, 1);

            return false;
        }

        return true;
    }

    @Override
    public void refreshView(List<MediaItem> mData) {
        if (mData != null && mData.size() > 0) {
//            //有数据
//            //设置适配器
//                videoAdapter = new VideoAdapter(mediaItems);
//                listview.setAdapter((ListAdapter) videoAdapter);

//            VideoAdapter adapter = new VideoAdapter(mData);
//            rvVideo.setAdapter(mAdapter);
            mAdapter.setNewData(mData);
//            mAdapter.addData(mData);
//            //把文本隐藏
//            tvNomedia.setVisibility(View.Gone);
        } else {
//            //没有数据
//            //文本显示
//            tv_nomedia.setVisibility(View.VISIBLE);
        }

        //ProgressBar隐藏
//        pb_loading.setVisibility(View.GONE);

//        ll_pb_loading.setVisibility(View.GONE);
//        ll_pb_loading.invalidate();

//        Activity act = (Activity)getContext();
//        act.runOnUiThread(new Runnable(){
//            @Override
//            public void run() {
//                pb_loading.setVisibility(View.GONE);
//            } });

    }

}
