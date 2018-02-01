LOCAL_PATH:= $(call my-dir)
include $(CLEAR_VARS)

LOCAL_SRC_FILES:= \
	system_init.cpp

base = $(LOCAL_PATH)/../../..
native = $(LOCAL_PATH)/../../../../native

LOCAL_C_INCLUDES := \
	$(native)/services/sensorservice \
	$(native)/services/surfaceflinger \
	$(JNI_H_INCLUDE) \
	$(native)/include/helloworld \
	external/stlport/stlport \
	bionic 

LOCAL_STATIC_LIBRARIES := libstlport_static 

LOCAL_SHARED_LIBRARIES := \
	libandroid_runtime \
	libsensorservice \
	libsurfaceflinger \
	libhelloworld \
    libinput \
	libutils \
	libbinder \
	libcutils

LOCAL_MODULE:= libsystem_server

include $(BUILD_SHARED_LIBRARY)
