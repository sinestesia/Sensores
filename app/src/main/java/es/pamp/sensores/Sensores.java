package es.pamp.sensores;

import android.app.Activity;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;

import static android.content.Context.SENSOR_SERVICE;

/**
 * Created by cice on 19/4/17.
 */

public class Sensores implements SensorEventListener {

    private MainActivity mainActivity;
    private float valor = 0;
    private float valor2 = 0;
    private float diferencia = 0;
    private boolean inicializado = false;

    public void setInicializado(boolean inicializado) {
        this.inicializado = inicializado;
    }

    @Override
    public void onSensorChanged(SensorEvent event) {

        if (!inicializado) {
            valor = event.values[2];
            valor2 = valor;
            inicializado = true;
        }

        if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER){
            valor = event.values[2];

            diferencia = Math.abs(valor - valor2);
            if (diferencia > 3) {
                mainActivity.colorear();
            }
            valor2 = valor;
        }
        mainActivity.escribirInfo(event.values[0], event.values[1],event.values[2]);

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    public void setMainActivity(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
    }
}
