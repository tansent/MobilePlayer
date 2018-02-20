package jtli.com.mobileplayer.injector.component;

import javax.inject.Singleton;

import dagger.Component;
import jtli.com.mobileplayer.fragment.VideoFragment;
import jtli.com.mobileplayer.injector.module.VideoModule;

/**
 * Created by Jingtian(Tansent).
 */

@Singleton
@Component(modules = { VideoModule.class})
public interface VideoComponent {
    void injectVideo(VideoFragment videoFragment);
}
