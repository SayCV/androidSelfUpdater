package com.example.SelfUpdater;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MyActivity extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        final TextView version = (TextView) findViewById(R.id.version);

        String versionName = getVersionName();
        version.setText(versionName);
        Log.i("SelfUpdater", versionName);


    }

    private String getVersionName() {
        try {
            return this.getPackageManager().getPackageInfo(this.getPackageName(), 0).versionName;
        } catch (Exception exception) {
            return null;
        }
    }

    public void installApk(View view) {

        PendingIntent mAlarmSender = PendingIntent.getActivity(this,
                0, new Intent(MyActivity.this, MyActivity.class), 0);

        long firstTime = SystemClock.elapsedRealtime();
        AlarmManager am = (AlarmManager)getSystemService(ALARM_SERVICE);
        am.set(AlarmManager.ELAPSED_REALTIME_WAKEUP,
                firstTime+ 5*1000, mAlarmSender);

        final EditText apkFilePath = (EditText) findViewById(R.id.apk);
        Intent intent = new Intent();
        intent.setClass(this, Installer.class);
        intent.putExtra("apkFilePath", apkFilePath.getText().toString());
        this.startService(intent);
    }
}
