package ru.mirea.saidova.mireaproject;


import android.annotation.SuppressLint;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link nav_sensor#newInstance} factory method to
 * create an instance of this fragment.
 */
public class nav_sensor extends Fragment implements SensorEventListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private SensorManager sensorManager;
    private ListView listCountSensor;
    private TextView azimuthTextView;
    private TextView pitchTextView;
    private TextView rollTextView;
    private TextView lightTextView;
    private TextView pressureTextView;

    private Sensor accelerometerSensor, lightSensor, pressureSensor;
    List<Sensor> sensors;





    public nav_sensor() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment nav_sensor.
     */
    // TODO: Rename and change types and number of parameters
    public static nav_sensor newInstance(String param1, String param2) {
        nav_sensor fragment = new nav_sensor();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_nav_sensor,
                container, false);
//        listCountSensor = view.findViewById(R.id.list_view);
        azimuthTextView = view.findViewById(R.id.textViewAzimuth);
        pitchTextView = view.findViewById(R.id.textViewPitch);
        rollTextView = view.findViewById(R.id.textViewRoll);
        lightTextView = view.findViewById(R.id.textViewLight);
        pressureTextView = view.findViewById(R.id.textViewPressure);
        sensorManager = (SensorManager)
                getActivity().getSystemService(Context.SENSOR_SERVICE);
        sensors = sensorManager.getSensorList(Sensor.TYPE_ALL);
        accelerometerSensor = sensorManager
                .getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        lightSensor = sensorManager
                .getDefaultSensor(Sensor.TYPE_LIGHT);
        pressureSensor = sensorManager.getDefaultSensor(Sensor.TYPE_PRESSURE);
// создаем список для отображения в ListView найденных датчиков
//        ArrayList<HashMap<String, Object>> arrayList = new ArrayList<>();
//        HashMap<String, Object> sensorTypeList;
//        for (int i = 0; i < sensors.size(); i++) {
//            sensorTypeList = new HashMap<>();
//            sensorTypeList.put("Name", sensors.get(i).getName());
//            sensorTypeList.put("Value", sensors.get(i).getMaximumRange());
//            arrayList.add(sensorTypeList);
//        }
//// создаем адаптер и устанавливаем тип адаптера - отображение двух полей
//        SimpleAdapter mHistory = new SimpleAdapter(getActivity(), arrayList,
//                android.R.layout.simple_list_item_2,
//                new String[]{"Name", "Value"},
//                new int[]{android.R.id.text1, android.R.id.text2});
//        listCountSensor.setAdapter(mHistory);
        // Inflate the layout for this fragment
        return view;
    }


    @SuppressLint("SetTextI18n")
    @Override
    public void onSensorChanged(SensorEvent event) {
        if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            float valueAzimuth = event.values[0];
            float valuePitch = event.values[1];
            float valueRoll = event.values[2];
            azimuthTextView.setText("Azimuth: " + valueAzimuth);
            pitchTextView.setText("Pitch: " + valuePitch);
            rollTextView.setText("Roll: " + valueRoll);
        }
        if (event.sensor.getType() == Sensor.TYPE_LIGHT) {
            float light = event.values[0];
            lightTextView.setText("Уровень освещенности: " + light);
        }

        if (sensors.contains(Sensor.TYPE_PRESSURE))
        {
            if (event.sensor.getType() == Sensor.TYPE_PRESSURE) {
                float pressure = event.values[0];
                pressureTextView.setText("Давление: " + pressure);
            }

        }
        else
            pressureTextView.setText("Устройство не имеет датчика давления");
    }
    @Override
    public void onResume() {
        super.onResume();
        sensorManager.registerListener(this, accelerometerSensor,
                SensorManager.SENSOR_DELAY_NORMAL);

        sensorManager.registerListener(this, lightSensor,
                SensorManager.SENSOR_DELAY_NORMAL);
        sensorManager.registerListener(this, pressureSensor,
                SensorManager.SENSOR_DELAY_NORMAL);
    }
    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}
