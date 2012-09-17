package org.cniska.phaser.input;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import org.cniska.phaser.core.GameView;
import org.cniska.phaser.core.Updateable;
import org.cniska.phaser.node.Node;

public class OrientationHandler extends Node implements SensorEventListener {

    protected SensorEvent accelerometer;
    protected SensorEvent magneticField;

    /**
     * Creates a new node.
     */
    public OrientationHandler(GameView view) {
        super(view);
    }

    @Override
    public void update(Updateable parent) {
        super.update(parent);

        if (accelerometer != null && magneticField != null) {
            float[] R = new float[4];
            SensorManager.getRotationMatrix(R, null, accelerometer.values.clone(), magneticField.values.clone());
            float[] orientation = new float[3];
            SensorManager.getOrientation(R, orientation);
        }
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        switch (sensorEvent.sensor.getType()) {
            case Sensor.TYPE_ACCELEROMETER:
                accelerometer = sensorEvent;
                break;
            case Sensor.TYPE_MAGNETIC_FIELD:
                magneticField = sensorEvent;
                break;
            default:
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {
    }
}
