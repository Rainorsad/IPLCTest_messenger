package com.example.wb.iplctest;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @InjectView(R.id.edit)
    EditText edit;
    @InjectView(R.id.bt)
    Button bt;
    @InjectView(R.id.bt_intent)
    Button btIntent;

    private Messenger mService;

    private ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            mService = new Messenger(service);
            Message msg = Message.obtain(null,1);
            Bundle bundle = new Bundle();
            bundle.putString("msg","Hellow");
            msg.setData(bundle);
            msg.replyTo = mGetMEssenger;
            try {
                mService.send(msg);
                Log.d("测试","启动访问");
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);
        Intent it = new Intent(this, MessageService.class);
        bindService(it,connection, Context.BIND_AUTO_CREATE);
    }

    @Override
    protected void onDestroy() {
        unbindService(connection);
        super.onDestroy();
    }

    @OnClick({R.id.bt, R.id.bt_intent})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bt:

                break;
            case R.id.bt_intent:

                break;
        }
    }

    private Messenger mGetMEssenger = new Messenger(new MessengerHandler());
    private static class MessengerHandler extends Handler{
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case 2:
                    Log.d("测试",msg.getData().getString("data"));
                    break;
                default:
                    super.handleMessage(msg);
            }
        }
    }
}
