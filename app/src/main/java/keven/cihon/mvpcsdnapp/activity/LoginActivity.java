package keven.cihon.mvpcsdnapp.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import org.json.JSONObject;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.PlatformActionListener;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.tencent.qq.QQ;
import keven.cihon.mvpcsdnapp.R;
import keven.cihon.mvpcsdnapp.cn.sharesdk.onekeyshare.OnekeyShare;
import keven.cihon.mvpcsdnapp.utils.LogUtils;
import keven.cihon.mvpcsdnapp.utils.PrefUtils;

public class LoginActivity extends AppCompatActivity {

    @BindView(R.id.bt_share)
    Button mBtShare;
    @BindView(R.id.bt_login)
    Button mBtLogin;
    @BindView(R.id.bt_login_remove)
    Button mBtLoginRemove;
    private Platform mQq;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

//        showShare();
        loginThree();
    }

    private void showShare() {
        OnekeyShare oks = new OnekeyShare();
        //关闭sso授权
        oks.disableSSOWhenAuthorize();

        // 分享时Notification的图标和文字  2.5.9以后的版本不     调用此方法
        //oks.setNotification(R.drawable.ic_launcher, getString(R.string.app_name));
        // title标题，印象笔记、邮箱、信息、微信、人人网和QQ空间使用
        oks.setTitle("分享");
        // titleUrl是标题的网络链接，仅在人人网和QQ空间使用
        oks.setTitleUrl("http://sharesdk.cn");
        // text是分享文本，所有平台都需要这个字段
        oks.setText("我是分享文本");
        // imagePath是图片的本地路径，Linked-In以外的平台都支持此参数
        oks.setImagePath("/sdcard/test.jpg");//确保SDcard下面存在此张图片
        // url仅在微信（包括好友和朋友圈）中使用
        oks.setUrl("http://sharesdk.cn");
        // comment是我对这条分享的评论，仅在人人网和QQ空间使用
        oks.setComment("我是测试评论文本");
        // site是分享此内容的网站名称，仅在QQ空间使用
        oks.setSite(getString(R.string.app_name));
        // siteUrl是分享此内容的网站地址，仅在QQ空间使用
        oks.setSiteUrl("http://sharesdk.cn");

        // 启动分享GUI
        oks.show(this);
    }

    private void login() {
        mQq.showUser(null);//授权并获取用户信息
    }

    private void loginThree() {
        mQq = ShareSDK.getPlatform(QQ.NAME);
//回调信息，可以在这里获取基本的授权返回的信息，但是注意如果做提示和UI操作要传到主线程handler里去执行
        mQq.setPlatformActionListener(new PlatformActionListener() {

            @Override
            public void onError(Platform arg0, int arg1, Throwable arg2) {
                // TODO Auto-generated method stub
                arg2.printStackTrace();
            }

            @Override
            public void onComplete(Platform arg0, int arg1, HashMap<String, Object> arg2) {
                // TODO Auto-generated method stub
                //输出所有授权信息
                String s = arg0.getDb().exportData();
                LogUtils.e("信息--" + s);
                try {
                    JSONObject jsonObject = new JSONObject(s);
                    String icon = jsonObject.optString("icon");
                    String nicknam = jsonObject.optString("nickname");
                    PrefUtils.setString(LoginActivity.this, "icon", icon);
                    PrefUtils.setString(LoginActivity.this, "nickname", nicknam);

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onCancel(Platform arg0, int arg1) {
                // TODO Auto-generated method stub

            }
        });
//authorize与showUser单独调用一个即可
//        qq.authorize();//单独授权,OnComplete返回的hashmap是空的

//移除授权
//weibo.removeAccount(true);
    }

    @OnClick({R.id.bt_share, R.id.bt_login})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.bt_share:
                showShare();
                break;
            case R.id.bt_login:
                login();
                break;
        }
    }

    @OnClick(R.id.bt_login_remove)
    public void onViewClicked() {
        mQq.removeAccount(true);
    }
}
