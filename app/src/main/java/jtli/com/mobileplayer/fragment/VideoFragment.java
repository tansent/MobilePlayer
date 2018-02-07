package jtli.com.mobileplayer.fragment;

import jtli.com.mobileplayer.R;
import jtli.com.mobileplayer.app.AppConstants;
import jtli.com.mobileplayer.base.BaseFragment;
import jtli.com.mobileplayer.utils.LogUtil;

/**
 * Created by Jingtian(Tansent).
 */

public class VideoFragment extends BaseFragment {
    @Override
    protected void initInject() {
        LogUtil.e("Video initialized initInject");
    }

    @Override
    protected void loadData() {
        setState(AppConstants.STATE_SUCCESS);
        LogUtil.e("Video initialized");
    }

    @Override
    protected int getLayoutId() {
        return R.layout.video_pager;
    }

    @Override
    protected void initView() {
        LogUtil.e("Video initialized initView");
    }
}
