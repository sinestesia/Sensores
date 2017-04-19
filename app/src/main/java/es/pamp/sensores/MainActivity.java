package es.pamp.sensores;

import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Layout;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private ConstraintLayout layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        layout = (ConstraintLayout) findViewById(R.id.layout);
        TextView acelerometroTV = (TextView) findViewById(R.id.acelerometro);
        TextView giroscopioTV = (TextView) findViewById(R.id.giroscopio);
        acelerometroTV.setVisibility(View.INVISIBLE);
        giroscopioTV.setVisibility(View.INVISIBLE);

        Button reiniciar =(Button) findViewById(R.id.reiniciarBoton);

        final Sensores sensores = new Sensores();
        sensores.setMainActivity(this);
        SensorManager sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);


        Sensor acelerometro = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER); //el que tiene la tablet
        Sensor giroscopio = sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE);

        if (acelerometro != null) {
            acelerometroTV.setVisibility(View.VISIBLE);
            sensorManager.registerListener(sensores,acelerometro,SensorManager.SENSOR_DELAY_NORMAL);

        }
        if (giroscopio != null) {
            giroscopioTV.setVisibility(View.VISIBLE);
            sensorManager.registerListener(sensores,giroscopio,SensorManager.SENSOR_DELAY_NORMAL);
        }

        reiniciar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                layout.setBackgroundResource(R.color.colorPrimary);
                sensores.setInicializado(false);
            }
        });
    }


    public void reiniciar(){
        layout.setBackgroundResource(R.color.colorPrimary);
    }
    public void colorear(){

        layout.setBackgroundResource(R.color.colorAccent);
        //layout.setBackgroundColor(0x000000);

    }
}
