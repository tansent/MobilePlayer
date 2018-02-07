package jtli.com.mobileplayer.fragment;

import jtli.com.mobileplayer.R;
import jtli.com.mobileplayer.app.AppConstants;
import jtli.com.mobileplayer.base.BaseFragment;
import jtli.com.mobileplayer.utils.LogUtil;

/**
 * Created by Jingtian(Tansent).
 */

public class NetVideoFragment extends BaseFragment {

    @Override
    protected void initInject() {

    }

    @Override
    protected void loadData() {
        setState(AppConstants.STATE_SUCCESS);
        LogUtil.e("Net Video initialized");
    }

    @Override
    protected int getLayoutId() {
        return R.layout.netvideo_pager;
    }

    @Override
    protected void initView() {
    }
}
