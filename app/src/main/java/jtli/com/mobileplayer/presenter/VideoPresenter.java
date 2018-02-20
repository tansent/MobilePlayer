package jtli.com.mobileplayer.presenter;

import java.util.List;

import jtli.com.mobileplayer.base.BaseView;
import jtli.com.mobileplayer.bean.MediaItem;

/**
 * Created by Jingtian(Tansent).
 */

public interface VideoPresenter {

    interface View extends BaseView<List<MediaItem>> {
    }

    interface Presenter{
        void fetchVideoData();
    }

}
