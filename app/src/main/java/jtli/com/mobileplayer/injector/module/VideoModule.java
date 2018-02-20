package jtli.com.mobileplayer.injector.module;

import com.chad.library.adapter.base.BaseQuickAdapter;

import java.util.ArrayList;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import jtli.com.mobileplayer.adapter.VideoAdapter;

/**
 * Created by Jingtian(Tansent).
 */
@Module
public class VideoModule {

    @Provides
    @Singleton
    public BaseQuickAdapter provideAdapter() {
        return new VideoAdapter(new ArrayList());
    }

}
