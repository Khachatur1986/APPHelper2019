package com.example.apphelper2019.ex.looper_handler;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.apphelper2019.R;

/**
 * https://youtu.be/Yo3VT-fZr68?list=PL6nth5sRD25hVezlyqlBO9dafKMc5fAU2
 * https://blog.mindorks.com/android-core-looper-handler-and-handlerthread-bd54d69fe91a
 */
public class Main28Activity extends AppCompatActivity {
    private static final String TAG = "Main28Activity"; //logt
    private TextView tv_handler_looper;

    /**
     * Handler used for communicate with main thread
     * from another thread we send message and in the main thread we set that message.text to textView
     */
    private Handler handler = new Handler(Looper.getMainLooper()){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            tv_handler_looper.setText((String) msg.obj);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main28);
        tv_handler_looper = findViewById(R.id.tv_handler_looper);

//        SimpleWorker worker = new SimpleWorker();
        Worker worker = new Worker();

        worker.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                Message message = Message.obtain();
                message.obj = "Task one";
                handler.sendMessage(message);
                System.out.println("One");
            }
        });

        worker.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Message message = Message.obtain();
                message.obj = "Task Two";
                handler.sendMessage(message);
                System.out.println("Two");
            }
        });

        worker.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Message message = Message.obtain();
                message.obj = "Task Tree";
                handler.sendMessage(message);
                System.out.println("Tree");
            }
        });
    }
}
