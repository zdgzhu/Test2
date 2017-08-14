package com.example.updatemaster;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

import com.example.updatemaster.updater.Updater;
import com.example.updatemaster.updater.UpdaterConfig;

public class MainActivity extends AppCompatActivity {
    private static final String APK_URL = "http://releases.b0.upaiyun.com/hoolay.apk";

    private EditText editText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_layout);
        editText = (EditText) findViewById(R.id.et_download);
        editText.setText(APK_URL);
        //如果没有停用,先去停用,然后点击下载按钮. 测试用户关闭下载服务
        //UpdaterUtils.showDownloadSetting(this);
    }

    public void download(View view) {
        String url = editText.getText().toString();
        if (TextUtils.isEmpty(url)) {
            url = APK_URL;
        }
        UpdaterConfig config = new UpdaterConfig.Builder(this)
                .setTitle("视珍宝下载")
                .setDescription("版本更新")
                .setFileUrl(url)
                .setCanMediaScanner(true) //能否被 MediaScanner 扫描
                .build();
        Updater.getInstance().download(config);

    }

}
