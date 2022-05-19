package com.anand.mylibrary

import android.app.Service
import android.content.Intent
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.IBinder
import android.util.Log
import android.util.MutableFloat
import androidx.lifecycle.MutableLiveData
import java.util.*
import kotlin.collections.HashMap


class MyService : Service() {


    var sensorManager :SensorManager? = null
    var sensor: Sensor?=null


   var data:String = ""


        private val rotationSensor: SensorEventListener = object : SensorEventListener {
            override fun onSensorChanged(event: SensorEvent) {

                if (event.sensor.type == Sensor.TYPE_ROTATION_VECTOR) {

                   data=Arrays.toString(event.values)


                }
            }


        override fun onAccuracyChanged(sensor: Sensor, accuracy: Int) {

        }
    }

    val binder: IRemoteService.Stub= object :IRemoteService.Stub()
    {
        override fun getData(): String {
            return "Testing message from  service"
        }

    }

    override fun onUnbind(intent: Intent?): Boolean {
        sensorManager?.unregisterListener(rotationSensor)
        return super.onUnbind(intent)

    }

    override fun onCreate() {
        super.onCreate()

        sensorManager=getSystemService(SENSOR_SERVICE) as SensorManager
        sensor =sensorManager?.getDefaultSensor(Sensor.TYPE_ROTATION_VECTOR)
        sensorManager?.registerListener(rotationSensor,sensor, 8000)
    }


    override fun onBind(intent: Intent): IBinder {
        return binder
    }
}