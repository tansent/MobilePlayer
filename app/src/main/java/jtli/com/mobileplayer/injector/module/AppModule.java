package jtli.com.mobileplayer.injector.module;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import jtli.com.mobileplayer.app.App;

/**
 * Created by Jingtian(Tansent).
 */

@Module
public class AppModule {

    private final App application;

    public AppModule(App application) {
        this.application = application;
    }

    @Provides
    @Singleton
    App provideApplicationContext() {
        return application;
    }
}
