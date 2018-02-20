package jtli.com.mobileplayer.presenter.impl;

import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import jtli.com.mobileplayer.base.BasePresenter;
import jtli.com.mobileplayer.base.Callback;
import jtli.com.mobileplayer.bean.MediaItem;
import jtli.com.mobileplayer.presenter.VideoPresenter;
import rx.Observable;

import static com.blankj.utilcode.utils.Utils.getContext;

/**
 * Created by Jingtian(Tansent).
 */

public class VideoPresenterImpl  extends BasePresenter<VideoPresenter.View> implements VideoPresenter.Presenter  {

    @Inject
    public VideoPresenterImpl() {
    }

    @Override
    public void fetchVideoData() {
        invoke(getVideoes(),new Callback<List>(){
            @Override
            public void onResponse(List mediaItems) {
                checkState(mediaItems);
                mView.refreshView(mediaItems);
            }
        });
    }

    private Observable<List> getVideoes() {
        List mediaItems = new ArrayList<>();
        ContentResolver resolver = getContext().getContentResolver();
        Uri uri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
        String[] objs = {
                MediaStore.Video.Media.DISPLAY_NAME,//视频文件在sdcard的名称
                MediaStore.Video.Media.DURATION,//视频总时长
                MediaStore.Video.Media.SIZE,//视频的文件大小
                MediaStore.Video.Media.DATA,//视频的绝对地址
                MediaStore.Video.Media.ARTIST,//歌曲的演唱者

        };
        Cursor cursor = resolver.query(uri, objs, null, null, null);
        if(cursor != null){
            while (cursor.moveToNext()){

                MediaItem mediaItem = new MediaItem();

                mediaItems.add(mediaItem);//写在上面

                String name = cursor.getString(0);//视频的名称
                mediaItem.setName(name);

                long duration = cursor.getLong(1);//视频的时长
                mediaItem.setDuration(duration);

                long size = cursor.getLong(2);//视频的文件大小
                mediaItem.setSize(size);

                String data = cursor.getString(3);//视频的播放地址
                mediaItem.setData(data);

                String artist = cursor.getString(4);//艺术家
                mediaItem.setArtist(artist);

            }
            cursor.close();
        }
        return Observable.just(mediaItems);

    }

}
