package ust.hk.robottourguidemobileremotecontrol;

import android.Manifest;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    BroadcastReceiver wifiScanReceiver;
    WifiManager wifiManager;
    Button sendLoc;
    Boolean manuallyTriggered=false;
    private static final int LOCATION_PERMISSION=1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED)
        {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, LOCATION_PERMISSION);
        }

        Button f= (Button) findViewById(R.id.fBtn);
        Button l= (Button) findViewById(R.id.lBtn);
        Button b= (Button) findViewById(R.id.bBtn);
        Button r= (Button) findViewById(R.id.rBtn);
        Button s= (Button) findViewById(R.id.sBtn);
        sendLoc= (Button) findViewById(R.id.sendLocBtn);

        f.setOnClickListener(this);
        l.setOnClickListener(this);
        b.setOnClickListener(this);
        r.setOnClickListener(this);
        s.setOnClickListener(this);
        sendLoc.setOnClickListener(this);

        wifiManager = (WifiManager) getSystemService(Context.WIFI_SERVICE);

        wifiScanReceiver= new BroadcastReceiver()
        {
            public void onReceive(Context c, Intent intent)
            {
                if(manuallyTriggered)
                {
                    List<ScanResult> wifiScanList = wifiManager.getScanResults();
                    JSONArray wifiListJson= new JSONArray();
                    for(int i=0;i<wifiScanList.size();i++)
                    {
                        JSONObject jsonObject = new JSONObject();
                        try {
                            jsonObject.put("BSSID", wifiScanList.get(i).BSSID);
                            jsonObject.put("Level", wifiScanList.get(i).level);
                            wifiListJson.put(jsonObject);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                    Log.i("Main Activity",String.valueOf(wifiScanList.size()));
                    Log.i("Main Activity",wifiScanList.toString());
                    Log.i("Main Activity", wifiListJson.toString());
                    ApiClient.sendLocation(wifiListJson.toString()).enqueue(new Callback()
                    {
                        @Override
                        public void onFailure(Call call, final IOException e)
                        {
                            runOnUiThread(new Runnable()
                            {
                                @Override
                                public void run()
                                {
                                    Toast.makeText(MainActivity.this,"Failed to send location!",Toast.LENGTH_SHORT).show();
                                    e.printStackTrace();
                                }
                            });
                        }
                        @Override
                        public void onResponse(Call call, final Response response) throws IOException
                        {
                            runOnUiThread(new Runnable()
                            {
                                @Override
                                public void run()
                                {
                                    try
                                    {
                                        Toast.makeText(MainActivity.this,response.body().string(),Toast.LENGTH_SHORT).show();
                                    } catch (IOException e)
                                    {
                                        e.printStackTrace();
                                    }
                                }
                            });
                        }
                    });
                    manuallyTriggered=false;
                }
            }
        };
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case LOCATION_PERMISSION:
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED)
                {
                    Toast.makeText(MainActivity.this,"Wifi localization enabled!",Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Toast.makeText(MainActivity.this,"Permission denied! Unable to use wifi localization",Toast.LENGTH_SHORT).show();
                    sendLoc.setEnabled(false);
                }
                break;
            default:
                Log.i("Main Activity","Switch case failed");
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.fBtn:
                ApiClient.moveForward().enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, final IOException e) {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(MainActivity.this,"Forward movement failed!",Toast.LENGTH_SHORT).show();
                                e.printStackTrace();
                            }
                        });
                    }

                    @Override
                    public void onResponse(Call call, final Response response) throws IOException {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                try {
                                    Toast.makeText(MainActivity.this,response.body().string(),Toast.LENGTH_SHORT).show();
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            }
                        });
                    }
                });
                break;
            case R.id.lBtn:
                ApiClient.moveLeft().enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, final IOException e) {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(MainActivity.this,"Left movement failed!",Toast.LENGTH_SHORT).show();
                                e.printStackTrace();
                            }
                        });
                    }

                    @Override
                    public void onResponse(Call call, final Response response) throws IOException {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                try {
                                    Toast.makeText(MainActivity.this,response.body().string(),Toast.LENGTH_SHORT).show();
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            }
                        });
                    }
                });
                break;
            case R.id.bBtn:
                ApiClient.moveBack().enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, final IOException e) {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(MainActivity.this,"Back movement failed!",Toast.LENGTH_SHORT).show();
                                e.printStackTrace();
                            }
                        });
                    }

                    @Override
                    public void onResponse(Call call, final Response response) throws IOException {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                try {
                                    Toast.makeText(MainActivity.this,response.body().string(),Toast.LENGTH_SHORT).show();
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            }
                        });
                    }
                });
                break;
            case R.id.rBtn:
                ApiClient.moveRight().enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, final IOException e) {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(MainActivity.this,"Right movement failed!",Toast.LENGTH_SHORT).show();
                                e.printStackTrace();
                            }
                        });
                    }

                    @Override
                    public void onResponse(Call call, final Response response) throws IOException {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                try {
                                    Toast.makeText(MainActivity.this,response.body().string(),Toast.LENGTH_SHORT).show();
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            }
                        });
                    }
                });
                break;
            case R.id.sBtn:
                ApiClient.stop().enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, final IOException e) {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(MainActivity.this,"Stop action failed!",Toast.LENGTH_SHORT).show();
                                e.printStackTrace();
                            }
                        });
                    }

                    @Override
                    public void onResponse(Call call, final Response response) throws IOException {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                try {
                                    Toast.makeText(MainActivity.this,response.body().string(),Toast.LENGTH_SHORT).show();
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            }
                        });
                    }
                });
                break;
            case R.id.sendLocBtn:
                    wifiManager.startScan();
                    manuallyTriggered=true;
                    break;
            default:
                Log.i("MAIN ACTIVITY","SWITCH CASE FAILED!");
        }
    }

    protected void onPause()
    {
        unregisterReceiver(wifiScanReceiver);
        Log.i("Main Activity","receiver unregistered");
        super.onPause();
    }

    protected void onResume()
    {
        registerReceiver(wifiScanReceiver, new IntentFilter(WifiManager.SCAN_RESULTS_AVAILABLE_ACTION));
        Log.i("Main Activity","receiver registered");
        super.onResume();
    }
}
