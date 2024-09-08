package com.chaaba.composegyroscopesensorkoinmvvm.vm

import android.app.Application
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class GyroscopeViewModel(application: Application) : AndroidViewModel(application = application),SensorEventListener  {
    //sensor manager
    private val sensorManager: SensorManager = application.getSystemService(SensorManager::class.java)
    var isGyroscopeAvailable = false
        private set


    // Mutable state flow for rotation rates
    private val _rotationRateX = MutableStateFlow(0f)
    private val _rotationRateY = MutableStateFlow(0f)
    private val _rotationRateZ = MutableStateFlow(0f)

    // Public immutable flows
    val rotationRateX = _rotationRateX.asStateFlow()
    val rotationRateY = _rotationRateY.asStateFlow()
    val rotationRateZ = _rotationRateZ.asStateFlow()


    init {
        // Start listening to the sensor when the ViewModel is created
        val gyroscopeSensor: Sensor? = sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE)
        if (gyroscopeSensor == null) {
            Log.e("GyroscopeViewModel", "Gyroscope sensor not available")
        } else {
            isGyroscopeAvailable = true
            sensorManager.registerListener(this, gyroscopeSensor, SensorManager.SENSOR_DELAY_NORMAL)
        }
    }

    override fun onSensorChanged(event: SensorEvent) {
        Log.d("GyroscopeViewModel", "X: ${event.values[0]}, Y: ${event.values[1]}, Z: ${event.values[2]}")
        viewModelScope.launch {
            _rotationRateX.emit(event.values[0]) // X-axis rotation rate
            _rotationRateY.emit(event.values[1]) // Y-axis rotation rate
            _rotationRateZ.emit(event.values[2]) // Z-axis rotation rate
        }
    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {
        TODO("Not yet implemented")
    }

    override fun onCleared() {
        super.onCleared()
        // Unregister sensor listener to save battery
        sensorManager.unregisterListener(this)
    }

}