# PriHook
Hooks in AOSP Service

## Three resources hooks

### Audio
AudioFlinger.cpp<br>
startInput, startOutput, stopInput, stopOutput<br>

### Camera
CameraClient.cpp<br>
startPreviewMode, takePicture, startRecording<br>

### Sensor
SensorService.cpp<br>
status_t SensorService::SensorEventConnection::sendEvents<br>

## Start our sevice at boot time
### system_init.cpp

