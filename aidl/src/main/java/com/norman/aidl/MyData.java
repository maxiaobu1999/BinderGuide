package com.norman.aidl;

import android.os.Parcel;
import android.os.Parcelable;

public class MyData implements Parcelable {
    private String dataString;
    private int dataInt;

    public MyData() {

    }

    protected MyData(Parcel in) {
        readFromParcel(in);
    }

    public static final Creator<MyData> CREATOR = new Creator<MyData>() {
        @Override
        public MyData createFromParcel(Parcel in) {
            return new MyData(in);
        }

        @Override
        public MyData[] newArray(int size) {
            return new MyData[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    /**
     * 将数据写入到Parcel
     **/
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(dataString);
        dest.writeInt(dataInt);
    }

    /**
     * 从Parcel中读取数据
     **/
    public void readFromParcel(Parcel in) {
        dataString = in.readString();
        dataInt = in.readInt();
    }


    public int getDataInt() {
        return dataInt;
    }

    public void setDataInt(int dataInt) {
        this.dataInt = dataInt;
    }

    public String getDataString() {
        return dataString;
    }

    public void setDataString(String dataString) {
        this.dataString = dataString;
    }

    @Override
    public String toString() {
        return "dataString = " + dataString + ", dataInt=" + dataInt;
    }
}
