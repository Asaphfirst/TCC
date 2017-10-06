from picamera.array import PiRGBArray
from picamera import PiCamera
import time
import cv2


camera = PiCamera(resolution=(512,512))
# camera.brightness = 20
camera.framerate = 30
camera.exposure_mode = 'off'

rawCapture = PiRGBArray(camera, size=(512, 512))

print ("brightness %d" % camera.framerate)
print ("brightness %d" % camera.brightness)
print ("sharpness %d" % camera.sharpness)
print ("contrast %d" % camera.contrast)
print ("saturation %d" % camera.saturation)
print ("ISO %d" % camera.ISO)
print ("video_stabilization %s" % camera.video_stabilization)
print ("exposure_compensation %d" % camera.exposure_compensation)
print ("exposure_mode %s" % camera.exposure_mode)
print ("meter_mode %s" % camera.meter_mode)
print ("awb_mode %s" % camera.awb_mode)
print ("image_effect %s" % camera.image_effect)
print ("color_effects %s" % camera.color_effects)


time.sleep(0.1)

camera.start_preview()
camera.start_recording("Video7_512p_30fps_1led_exposure-mode_off.h264")

time.sleep(60)
camera.stop_preview()
camera.stop_recording()


