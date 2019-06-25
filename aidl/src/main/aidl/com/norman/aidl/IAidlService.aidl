// IAidlService.aidl
package com.norman.aidl;
// Declare any non-default types here with import statements
import com.norman.aidl.MyData;

interface IAidlService {
    MyData getData(in MyData data);
}
