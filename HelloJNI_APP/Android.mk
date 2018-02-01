LOCAL_PATH:= $(call my-dir)
include $(CLEAR_VARS)
LOCAL_MODULE_TAGS := eng
LOCAL_MODULE_TAGS := optional

LOCAL_SRC_FILES := $(call all-java-files-under,src)
LOCAL_PACKAGE_NAME := HelloJNI

LOCAL_RESOURCE_DIR := \
        $(LOCAL_PATH)/res

# 指定需要打包到apk的so库
# LOCAL_JNI_SHARED_LIBRARIES := libsimp \

LOCAL_PROGUARD_ENABLED := disabled

# Tell it to build an APK
include $(BUILD_PACKAGE)
include $(call all-makefiles-under,$(LOCAL_PATH))
