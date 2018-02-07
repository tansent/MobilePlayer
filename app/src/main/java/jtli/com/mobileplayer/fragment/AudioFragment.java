package jtli.com.mobileplayer.fragment;

import com.blankj.utilcode.utils.LogUtils;

import jtli.com.mobileplayer.R;
import jtli.com.mobileplayer.app.AppConstants;
import jtli.com.mobileplayer.base.BaseFragment;
import jtli.com.mobileplayer.utils.LogUtil;

/**
 * Created by Jingtian(Tansent).
 */

public class AudioFragment extends BaseFragment {

    @Override
    protected void initInject() {

    }

    @Override
    protected void loadData() {
        setState(AppConstants.STATE_SUCCESS);
        LogUtil.e("Audio initialized");
    }

    @Override
    protected int getLayoutId() {
        return R.layout.video_pager;
    }

    @Override
    protected void initView() {

    }
}
