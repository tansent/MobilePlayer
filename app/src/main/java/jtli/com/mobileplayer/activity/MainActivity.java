package jtli.com.mobileplayer.activity;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.FrameLayout;
import android.widget.RadioGroup;

import java.util.ArrayList;
import java.util.List;

import jtli.com.mobileplayer.R;
import jtli.com.mobileplayer.adapter.HomeFragmentPageAdapter;
import jtli.com.mobileplayer.base.BasePager;
import jtli.com.mobileplayer.fragment.AudioFragment;
import jtli.com.mobileplayer.fragment.NetAudioFragment;
import jtli.com.mobileplayer.fragment.NetVideoFragment;
import jtli.com.mobileplayer.fragment.VideoFragment;
import jtli.com.mobileplayer.pager.AudioPager;
import jtli.com.mobileplayer.pager.NetAudioPager;
import jtli.com.mobileplayer.pager.NetVideoPager;
import jtli.com.mobileplayer.pager.VideoPager;

public class MainActivity extends AppCompatActivity {

    private RadioGroup  rg_bottom_tag;
    private FrameLayout fl_main_content;

    private ViewPager vpContent;

    /**
     * 页面的集合
     */
    private List<Fragment> basePagers;
    /**
     * 选中的位置
     */
    private int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rg_bottom_tag = (RadioGroup) findViewById(R.id.rg_bottom_tag);
        fl_main_content = (FrameLayout) findViewById(R.id.fl_main_content);
        vpContent = (ViewPager) findViewById(R.id.vp_content);

        rg_bottom_tag.check(R.id.rb_video);//默认选中首页


        initView();


    }

    private void initView() {
        //设置RadioGroup的监听
        rg_bottom_tag.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener(){
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.rb_video:
                        position = 0;
                        vpContent.setCurrentItem(0);
                        break;
                    case R.id.rb_audio://音频
                        position = 1;
                        vpContent.setCurrentItem(1);
                        break;
                    case R.id.rb_net_video://网络视频
                        position = 2;
                        vpContent.setCurrentItem(2);
                        break;
                    case R.id.rb_netaudio://网络音频
                        vpContent.setCurrentItem(3);
                        position = 3;
                        break;
                }
            }

        });

        basePagers = new ArrayList<>();
        basePagers.add(new VideoFragment());//添加本地视频页面-0
        basePagers.add(new AudioFragment());//添加本地音乐页面-1
        basePagers.add(new NetVideoFragment());//添加网络视频页面-2
        basePagers.add(new NetAudioFragment());//添加网络音频页面-3
        vpContent.setAdapter(new HomeFragmentPageAdapter(getSupportFragmentManager(), basePagers));
        vpContent.setCurrentItem(0);
        vpContent.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switch (position) {
                    case 0:
                        rg_bottom_tag.check(R.id.rb_video);
                        break;
                    case 1:
                        rg_bottom_tag.check(R.id.rb_audio);
                        break;
                    case 2:
                        rg_bottom_tag.check(R.id.rb_net_video);
                        break;
                    case 3:
                        rg_bottom_tag.check(R.id.rb_netaudio);
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }


//    class MyOnCheckedChangeListener implements RadioGroup.OnCheckedChangeListener {
//
//        @Override
//        public void onCheckedChanged(RadioGroup group, int checkedId) {
//
//            switch (checkedId){
//                default:
//                    position = 0;
//                    break;
//                case R.id.rb_audio://音频
//                    position = 1;
//                    break;
//                case R.id.rb_net_video://网络视频
//                    position = 2;
//                    break;
//                case R.id.rb_netaudio://网络音频
//                    position = 3;
//                    break;
//            }
//
//            setFragment();
//
//        }
//    }
//
//    /**
//     * 把页面添加到Fragment中
//     */
//    private void setFragment() {
//        //1.得到FragmentManger
//        FragmentManager manager = getSupportFragmentManager();
//        //2.开启事务
//        FragmentTransaction ft = manager.beginTransaction();
//        //3.替换
////        Fragment fragment = new Fragment();
////        fragment.onCreateView()
//        MainFragment fragment = new MainFragment();
//        Bundle bundle = new Bundle();
//        bundle.putInt("position",position);
//        bundle.put
//        fragment.setArguments();
//        ft.replace(R.id.fl_main_content,new MainFragment());
//        //4.提交事务
//        ft.commit();
//
//    }

}
