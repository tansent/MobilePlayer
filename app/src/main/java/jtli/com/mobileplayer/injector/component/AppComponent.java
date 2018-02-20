package jtli.com.mobileplayer.injector.component;

import javax.inject.Singleton;

import dagger.Component;
import jtli.com.mobileplayer.app.App;
import jtli.com.mobileplayer.injector.module.AppModule;

/**
 * Created by Jingtian(Tansent).
 */
@Singleton
@Component(modules = {AppModule.class})
public interface AppComponent {
    App getContext();  // 提供App的Context
}
