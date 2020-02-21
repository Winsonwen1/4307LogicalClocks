package ch.ethz.inf.vs.a3.vsowlnetchat;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.net.DatagramSocket;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.util.UUID;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONObject;

import edu.temple.vsowlnetchat.R;

public class MainActivity extends AppCompatActivity {

    byte[] buffer = new byte[1024];
    private int port = 4446;
    private String ipAdd = "10.0.2.2";
    private final int REQUEST_CODE = 1111;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.join).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // for (int i = 0; i < 5; i++) {
                    try {
                        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
                        StrictMode.setThreadPolicy(policy);
                        String uuid = UUID.randomUUID().toString();
                        DatagramSocket socket = new DatagramSocket();
                        DatagramPacket inPacket = new DatagramPacket(buffer, buffer.length);

                        JSONObject json = new JSONObject();
//                        JSONObject header = new JSONObject();
                        JSONObject body = new JSONObject();
                    //    JSONObject bodyMessage = new JSONObject();
                        JSONObject headerMessage = new JSONObject();
                        headerMessage.put("username", ((TextView)findViewById(R.id.editText)).getText().toString());

                        headerMessage.put("uuid",uuid);

                        headerMessage.put("timestamp", "{}");
                        headerMessage.put("type","register");
                        json.put("header",headerMessage);
                        json.put("body",body);




                        buffer = json.toString().getBytes();

//                        json.put("uuid",uuid);

                        socket.setSoTimeout(5);

                        System.out.println("    ======================================2");
                        //socket   = new DatagramSocket();
                        System.out.println("    ======================================3");
                        DatagramPacket packet = new DatagramPacket(buffer, buffer.length, InetAddress.getByName(ipAdd), port);
                        //packet.setData(buffer);
                        System.out.println("    ======================================4");
                        socket.send(packet);
                        socket.receive(inPacket);
                        String response = new String(inPacket.getData(), 0, inPacket.getLength());
                        System.out.println(response);
                        Toast.makeText(getApplicationContext(), " Registered Successfully", Toast.LENGTH_LONG).show();
                        Intent launchIntent = new Intent(MainActivity.this, ChatActivity.class);
                        startActivity(launchIntent);

                        socket.close();
                     //   break;


                    } catch (Exception e) {
                        Toast.makeText(getApplicationContext(), "Can't Connect to Server", Toast.LENGTH_LONG).show();
                        e.printStackTrace();
                    }


                }
           // }
        });

        findViewById(R.id.setting).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent launchIntent = new Intent(MainActivity.this, SettingActivity.class);
                launchIntent.putExtra("ipAdd", ipAdd);
                launchIntent.putExtra("port", String.valueOf(port));
                startActivityForResult(launchIntent, REQUEST_CODE);
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        ipAdd = data.getStringExtra("ipAdd");
        port = Integer.parseInt(data.getStringExtra("port"));

    }
}

