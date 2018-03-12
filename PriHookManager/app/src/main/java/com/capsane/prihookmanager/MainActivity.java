package com.capsane.prihookmanager;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    private static final String[] resources = {"TAKE_PICTURE","START_RECORDING","START_PREVIEW",
            "START_RECORD","STOP_RECORD","START_AUDIO","STOP_AUDIO","CONTROL_VOLUME",
            "SENSOR_TYPE_ACCELEROMETER","SENSOR_TYPE_GYROSCOPE","SENSOR_TYPE_PRESSURE",
            "SENSOR_TYPE_TEMPERATURE","SENSOR_TYPE_PROXIMITY","SENSOR_TYPE_LIGHT",
            "SENSOR_TYPE_GRAVITY","SENSOR_TYPE_MAGNETIC_FIELD","SENSOR_TYPE_ORIENTATION",
            "SENSOR_TYPE_LINEAR_ACCELERATION", "SENSOR_TYPE_ROTATION_VECTOR",
            "SENSOR_TYPE_RELATIVE_HUMIDITY", "SENSOR_TYPE_AMBIENT_TEMPERATURE"};

    private static List<Resource> resourceList;


    static {
        try {
            System.loadLibrary("prihook");
            System.out.println("libprihook.so loaded.");
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(TAG, "load libprihook.so failed!");
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
        ResourceAdapter resourceAdapter = new ResourceAdapter(MainActivity.this, R.layout.rsource_item, resourceList);
        ListView listView = findViewById(R.id.list_view);
        listView.setAdapter(resourceAdapter);

    }

    private void init() {
        resourceList = new ArrayList<>();
        for (String resourceType : resources) {
            Resource resource = new Resource(resourceType, getFromJNI(resourceType));
            resourceList.add(resource);
        }
    }


    /**
     * A native method that is implemented by the 'native-lib' native library,
     * which is packaged with this application.
     */
    public native int setFromJNI(String resourceType, int accessFlag);
    public native int getFromJNI(String resourceType);


    /*内部类*/
    class ResourceAdapter extends ArrayAdapter<Resource> {

        private int resourceId;

        public ResourceAdapter(Context context, int textViewResourceId, List<Resource> resources) {
            super(context, textViewResourceId, resources);
            resourceId = textViewResourceId;
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            final Resource resource = getItem(position);  // 获取当前项的实例

            View view = LayoutInflater.from(getContext()).inflate(resourceId, parent, false);
            Button buttonAllow = view.findViewById(R.id.button_allow);
            Button buttonDisable = view.findViewById(R.id.button_disable);
            TextView name = view.findViewById(R.id.resource_name);
            final TextView flag = view.findViewById(R.id.resource_flag);

            name.setText(resource.getResourceType());
            String string = resource.getAccessFlag() == 0 ? "Allow" : "Disallow";
            flag.setText(string);
            if (resource.getAccessFlag() != 0) {
                flag.setTextColor(Color.RED);
            }

            buttonAllow.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view1) {
                    if (resource.getAccessFlag() == 0)
                        return;

                    if (setFromJNI(resource.getResourceType(), 0) == 0) {
                        flag.setText("Allow");
                        resource.setAccessFlag(0);
                        flag.setTextColor(Color.BLUE);
                    }

                    notifyDataSetChanged();
                }
            });

            buttonDisable.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view1) {
                    if (resource.getAccessFlag() == 1)
                        return;

                    if (setFromJNI(resource.getResourceType(), 1) == 0) {
                        flag.setText("Disallow");
                        resource.setAccessFlag(1);
                        flag.setTextColor(Color.RED);
                    }

                    notifyDataSetChanged();
                }
            });

            return view;
        }
    }



}



