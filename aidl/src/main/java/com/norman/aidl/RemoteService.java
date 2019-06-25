package com.norman.aidl;

import android.app.ActivityManager;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.os.Parcel;
import android.os.Process;
import android.os.RemoteException;
import android.util.Log;

public class RemoteService extends Service {
    private static final String TAG = "RemoteService+++:";
    private Context mContext;

    MyData mMyData;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = this;
        Log.i(TAG, "[RemoteService] onCreate");
        initMyData();
    }

    @Override
    public IBinder onBind(Intent intent) {
        Log.i(TAG, "[RemoteService] onBind");
        return mBinder;
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.i(TAG, "[RemoteService] onUnbind");
        return super.onUnbind(intent);
    }

    @Override
    public void onDestroy() {
        Log.i(TAG, "[RemoteService] onDestroy");
        super.onDestroy();
    }

    /**
     * 实现IRemoteService.aidl中定义的方法
     */
    private final IAidlService.Stub mBinder = new IAidlService.Stub() {

        @Override
        public MyData getData(MyData data) throws RemoteException {
            Log.i(TAG, "接收参数：pid="+data.getDataInt()+ ", 进程名 = "+data.getDataString());
            Log.i(TAG, "返回结果：pid="+mMyData.getDataInt()+ ", 进程名 = "+mMyData.getDataString());

            return mMyData;
        }

        /**此处可用于权限拦截**/
        @Override
        public boolean onTransact(int code, Parcel data, Parcel reply, int flags) throws RemoteException {
            return super.onTransact(code, data, reply, flags);
        }
    };

    /**
     * 初始化MyData数据
     **/
    private void initMyData() {
        mMyData = new MyData();
        mMyData.setDataInt(Process.myPid());
        String procName = "";
        ActivityManager mActivityManager = (ActivityManager) mContext.getSystemService(Context.ACTIVITY_SERVICE);
        for (ActivityManager.RunningAppProcessInfo appProcess : mActivityManager
                .getRunningAppProcesses()) {
            if (appProcess.pid == Process.myPid()) {
                procName = appProcess.processName;
            }
        }

        mMyData.setDataString( procName);
    }
}
