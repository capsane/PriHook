LOCAL_PATH:= $(call my-dir)
############################################### 编译JNI共享库
include $(CLEAR_VARS)
LOCAL_SRC_FILES:= \
    simp.cpp
# 我艹，这个竟然成了？string的问题
# 这里会根据头文件自动添加对系统库的依赖？
LOCAL_C_INCLUDES := \
	$(JNI_H_INCLUDE) \
	external/stlport/stlport \
	bionic \
	$(LOCAL_PATH)/include \	

LOCAL_STATIC_LIBRARIES := libstlport_static 

LOCAL_MODULE := libsimp
LOCAL_MODULE_TAGS := optional

LOCAL_SHARED_LIBRARIES := \
	libhelloworld \

include $(BUILD_SHARED_LIBRARY)


