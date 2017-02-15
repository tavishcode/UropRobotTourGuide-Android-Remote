package ust.hk.robottourguidemobileremotecontrol;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.IOException;

import butterknife.BindView;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    @BindView(R.id.fBtn) Button f;
    @BindView(R.id.lBtn) Button l;
    @BindView(R.id.bBtn) Button b;
    @BindView(R.id.rBtn) Button r;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.fBtn:
                ApiClient.moveForward().enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                        Toast.makeText(MainActivity.this,"Forward movement failed!",Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        Toast.makeText(MainActivity.this,response.body().string(),Toast.LENGTH_SHORT).show();
                    }
                });
                break;
            case R.id.lBtn:
                ApiClient.moveLeft().enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                        Toast.makeText(MainActivity.this,"Left movement failed!",Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        Toast.makeText(MainActivity.this,response.body().string(),Toast.LENGTH_SHORT).show();
                    }
                });
                break;
            case R.id.bBtn:
                ApiClient.moveBack().enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                        Toast.makeText(MainActivity.this,"Back movement failed!",Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        Toast.makeText(MainActivity.this,response.body().string(),Toast.LENGTH_SHORT).show();
                    }
                });
                break;
            case R.id.rBtn:
                ApiClient.moveRight().enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                        Toast.makeText(MainActivity.this,"Right movement failed!",Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        Toast.makeText(MainActivity.this,response.body().string(),Toast.LENGTH_SHORT).show();
                    }
                });
                break;
            default:
                Log.i("MAIN ACTIVITY","SWITCH CASE FAILED!");
        }
    }
}
