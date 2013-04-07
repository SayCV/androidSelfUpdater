package com.example.SelfUpdater;

import android.app.IntentService;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.text.TextUtils;
import android.util.Log;

public class Installer extends IntentService {

    public Installer() {
        super("Installer");
        setIntentRedelivery(true);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        Log.i("SelfUpdater", "onStartCommand");
        String apk = intent.getStringExtra("apkFilePath");

        Log.i("SelfUpdater", apk);

        if (apk == null) {
            return;
        }

        if (TextUtils.isEmpty(apk)) {
            return;
        }

        Log.i("SelfUpdater", "install apk " + apk);

        try {

            ApplicationManager applicationManager = new ApplicationManager(this);
            applicationManager.installPackage(apk);
        } catch (Exception exception) {
            Log.e("SelfUpdater", exception.getMessage(), exception);
        }
    }
}
