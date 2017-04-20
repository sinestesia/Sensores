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

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {
    private ConstraintLayout layout;
    private Sensores sensores;
    TextView xTV;
    TextView yTV;
    TextView zTV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        layout = (ConstraintLayout) findViewById(R.id.layout);
        TextView acelerometroTV = (TextView) findViewById(R.id.acelerometro);
        TextView giroscopioTV = (TextView) findViewById(R.id.giroscopio);
        xTV = (TextView) findViewById(R.id.xTV);
        yTV = (TextView) findViewById(R.id.yTV);
        zTV = (TextView) findViewById(R.id.zTV);
        acelerometroTV.setVisibility(View.INVISIBLE);
        giroscopioTV.setVisibility(View.INVISIBLE);

        Button reiniciar =(Button) findViewById(R.id.reiniciarBoton);

        sensores = new Sensores();
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
                layout.setBackgroundColor(0x000000);
                //layout.setBackgroundResource(R.color.colorPrimaryDark);
                sensores.setInicializado(false);
            }
        });
    }

    public void colorear(){

        layout.setBackgroundResource(R.color.colorAccent);
        //layout.setBackgroundColor(0x000000);

    }
    public void escribirInfo(float x, float y, float z){
        xTV.setText("x = " + Float.toString(x));
        yTV.setText("y = " + Float.toString(y));
        zTV.setText("z = " + Float.toString(z));
    }
}
