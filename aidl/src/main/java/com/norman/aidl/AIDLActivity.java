package com.norman.aidl;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Process;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

public class AIDLActivity extends AppCompatActivity {
    private static final String TAG = "AIDLActivity+++:";
    private Activity mActivity;
    private IAidlService mAidlService;
    private boolean mIsBound;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aidl);
        mActivity = this;

        findViewById(R.id.btn_bind).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mActivity, RemoteService.class);
                mActivity.bindService(intent, mConnection, Context.BIND_AUTO_CREATE);
            }
        });

        findViewById(R.id.btn_unbind).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mActivity.unbindService(mConnection);
            }
        });
    }

    /**
     * 用语监控远程服务连接的状态
     */
    private ServiceConnection mConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {

            mAidlService = IAidlService.Stub.asInterface(service);
            String pidInfo = null;
            try {
                MyData data = new MyData();
                data.setDataInt(Process.myPid());
                String procName = "";
                ActivityManager mActivityManager = (ActivityManager) mActivity.getSystemService(Context.ACTIVITY_SERVICE);
                for (ActivityManager.RunningAppProcessInfo appProcess : mActivityManager
                        .getRunningAppProcesses()) {
                    if (appProcess.pid == Process.myPid()) {
                        procName = appProcess.processName;
                    }
                }
                data.setDataString(procName);
                Log.d(TAG, "发送参数: pid=" + data.getDataInt() + ", 进程名 = " + data.getDataString());
                MyData reply = mAidlService.getData(data);
                pidInfo = "pid=" + reply.getDataInt() +
                        ", 进程名 = " + reply.getDataString();
            } catch (RemoteException e) {
                e.printStackTrace();
            }
            Log.d(TAG, "返回结果： " + pidInfo);
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            Log.d(TAG, "onServiceDisconnected:ComponentName=" + name.getClassName());
            mAidlService = null;
        }
    };

}
