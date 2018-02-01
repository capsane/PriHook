#include <jni.h>
#include <string>
#include <map>
#include "HelloWorldManager.h"

using namespace std;
using namespace android;

extern "C"
jint Java_com_capsane_hellojni_MainActivity_setFromJNI(JNIEnv *env, jobject /* this */, jstring resourceType, jint accessFlag) {
    HelloWorldManager mHelloWorldManager;
    const char *str = env->GetStringUTFChars(resourceType, 0);
    return mHelloWorldManager.setAccessFlag(str, accessFlag);
}

extern "C"
jint Java_com_capsane_hellojni_MainActivity_getFromJNI(JNIEnv *env, jobject /* this */, jstring resourceType) {
    HelloWorldManager mHelloWorldManager;
    // 将Java String转换为C字符串
    const char *str;
    str = env->GetStringUTFChars(resourceType, 0);
    jint accessFlag = mHelloWorldManager.check(str);
    return accessFlag;
}

extern "C"
jstring Java_com_capsane_hellojni_MainActivity_stringFromJNI(
        JNIEnv *env,
        jobject /* this */) {
    string hello = "Hello from C++";
    return env->NewStringUTF(hello.c_str());
}


