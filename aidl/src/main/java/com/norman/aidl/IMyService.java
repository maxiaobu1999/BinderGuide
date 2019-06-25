/*
 * This file is auto-generated.  DO NOT MODIFY.
 * Original file: /Users/v_maqinglong/Documents/AndroidProject/BinderGuide/aidl/src/main/aidl/com/norman/aidl/IAidlService.aidl
 */
package com.norman.aidl;

/**
 * 姓名：马庆龙 on 2019-06-25 15:05
 * 功能：系统生成的文件
 */
public interface IMyService extends android.os.IInterface {
    /**
     * binder实体
     */
    public static abstract class Stub extends android.os.Binder implements com.norman.aidl.IMyService {
        /**
         * 标示
         * 1、用于区分本地or跨进程
         * 2、跨进程中区分service
         */
        private static final java.lang.String DESCRIPTOR = "com.norman.aidl.IMyService";

        /**
         * 构造
         * 保存this和DESCRIPTOR为binder中成员变量
         */
        public Stub() {
            this.attachInterface(this, DESCRIPTOR);
        }

        /**
         * IBinder转IMyService
         * 本地返回实体
         * 跨进程返回代理，即proxy
         */
        public static com.norman.aidl.IMyService asInterface(android.os.IBinder obj) {
            if ((obj == null)) {
                return null;
            }
            //binderProxy返回null；Binder DESCRIPTOR相同则 返回mOwner
            android.os.IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
            if (((iin != null) && (iin instanceof com.norman.aidl.IMyService))) {
                //本地通进程
                return ((com.norman.aidl.IMyService) iin);
            }
            //跨进程
            return new com.norman.aidl.IMyService.Stub.Proxy(obj);
        }

        @Override
        public android.os.IBinder asBinder() {
            return this;
        }


        /**
         * 接收client调用，与transact()对应
         * @param code
         * @param data 客户端传来的参数副本
         * @param reply 返回值，由客户端传入，由服务端复制
         * @param flags
         * @return
         * @throws android.os.RemoteException
         */
        @Override
        public boolean onTransact(int code, android.os.Parcel data, android.os.Parcel reply, int flags)
                throws android.os.RemoteException {
            java.lang.String descriptor = DESCRIPTOR;
            switch (code) {
                // TODO: 2019-06-25
                case INTERFACE_TRANSACTION: {
                    reply.writeString(descriptor);
                    return true;
                }
                case TRANSACTION_getData: {
                    //调用getData()
                    data.enforceInterface(descriptor);
                    //_arg0参数
                    com.norman.aidl.MyData _arg0;
                    if ((0 != data.readInt())) {
                        //从内核空间取数据，反序列化参数
                        _arg0 = com.norman.aidl.MyData.CREATOR.createFromParcel(data);
                    } else {
                        _arg0 = null;
                    }
                    //调用发生位置
                    com.norman.aidl.MyData _result = this.getData(_arg0);
                    //表示没有异常
                    reply.writeNoException();
                    if ((_result != null)) {
                        reply.writeInt(1);
                        //写入内核空间，PARCELABLE_WRITE_RETURN_VALUE表示此数据为方法的返回值
                        _result.writeToParcel(reply, android.os.Parcelable.PARCELABLE_WRITE_RETURN_VALUE);
                    } else {
                        reply.writeInt(0);
                    }
                    return true;
                }
                default: {
                    return super.onTransact(code, data, reply, flags);
                }
            }
        }

        /** binder 代理  与BinderProxy对应*/
        private static class Proxy implements com.norman.aidl.IMyService {
            /** BinderProxy对象 */
            private android.os.IBinder mRemote;

            Proxy(android.os.IBinder remote) {
                mRemote = remote;
            }

            @Override
            public android.os.IBinder asBinder() {
                return mRemote;
            }

            public java.lang.String getInterfaceDescriptor() {
                return DESCRIPTOR;
            }

            @Override
            public com.norman.aidl.MyData getData(com.norman.aidl.MyData data) throws android.os.RemoteException {
                //创建Parcel，用于向内核空间写入数据
                android.os.Parcel _data = android.os.Parcel.obtain();
                android.os.Parcel _reply = android.os.Parcel.obtain();
                //getData的返回值
                com.norman.aidl.MyData _result;
                try {
                    //给参数添加表示
                    _data.writeInterfaceToken(DESCRIPTOR);
                    if ((data != null)) {
                        _data.writeInt(1);
                        //参数写入内核空间，唯一拷贝发生位置
                        data.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    //通过binder 调用service 调用结束前线程阻塞
                    mRemote.transact(Stub.TRANSACTION_getData, _data, _reply, 0);
                    //检查异常，service端异常也会抛到client中处理
                    _reply.readException();
                    if ((0 != _reply.readInt())) {
                        //从内核拷贝返回值到用户空间，返回值的唯一一次拷贝
                        _result = com.norman.aidl.MyData.CREATOR.createFromParcel(_reply);
                    } else {
                        _result = null;
                    }
                } finally {
                    //parcel使用后需要释放
                    _reply.recycle();
                    _data.recycle();
                }
                return _result;
            }
        }

        /** 标识，区分方法 */
        static final int TRANSACTION_getData = (android.os.IBinder.FIRST_CALL_TRANSACTION + 0);
    }

    /** 跨进程被调用的方法 */
    public com.norman.aidl.MyData getData(com.norman.aidl.MyData data) throws android.os.RemoteException;
}
