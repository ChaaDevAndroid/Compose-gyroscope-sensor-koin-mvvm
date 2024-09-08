package com.chaaba.composegyroscopesensorkoinmvvm.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.chaaba.composegyroscopesensorkoinmvvm.vm.GyroscopeViewModel
import org.koin.androidx.compose.koinViewModel


@Composable
fun GyroscopeScreen(gyroscopeViewModel: GyroscopeViewModel = koinViewModel()) {
    // Collecting the rotation rate values from the ViewModel
    if (!gyroscopeViewModel.isGyroscopeAvailable) {
        // Display a message indicating the gyroscope isn't available
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.fillMaxSize()
        ) {
            Text(text = "Gyroscope sensor is not available on this device", fontSize = 18.sp)
        }
    } else {
        val rotationRateX by gyroscopeViewModel.rotationRateX.collectAsState()
        val rotationRateY by gyroscopeViewModel.rotationRateY.collectAsState()
        val rotationRateZ by gyroscopeViewModel.rotationRateZ.collectAsState()

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "Rotation Rate (rad/s):", fontSize = 22.sp)

            Spacer(modifier = Modifier.height(16.dp))
            Text(text = "X: $rotationRateX", fontSize = 18.sp)

            Spacer(modifier = Modifier.height(8.dp))
            Text(text = "Y: $rotationRateY", fontSize = 18.sp)

            Spacer(modifier = Modifier.height(8.dp))
            Text(text = "Z: $rotationRateZ", fontSize = 18.sp)
        }
    }
}