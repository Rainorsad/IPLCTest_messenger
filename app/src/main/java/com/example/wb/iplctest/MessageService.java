package com.example.wb.iplctest;

import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Created by Zhangchen on 2017/8/17.
 */

public class MessageService extends Service{

    private static class MessageHandler extends Handler{
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case 1:
                    Log.d("测试","接收到数据"+  msg.getData().getString("msg"));
                    Messenger msgClick = msg.replyTo;
                    Message replayMessage = Message.obtain(null,2);
                    Bundle bundle = new Bundle();
                    bundle.putString("data","I'm fine,thankyou,and you?");
                    replayMessage.setData(bundle);
                    try {
                        msgClick.send(replayMessage);
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                    break;
                default:
                    super.handleMessage(msg);
            }
        }
    }

    private Messenger messenger = new Messenger(new MessageHandler());
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return messenger.getBinder();
    }
}
