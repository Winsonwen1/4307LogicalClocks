package ch.ethz.inf.vs.a3.vsowlnetchat;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


import java.util.regex.Matcher;
import java.util.regex.Pattern;

import edu.temple.vsowlnetchat.R;

public class SettingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        Intent intent = getIntent();

        ((EditText)findViewById(R.id.editText2)).setText( intent.getStringExtra("ipAdd"));
        ((EditText)findViewById(R.id.editText3)).setText( intent.getStringExtra("port"));


        findViewById(R.id.submit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("    ======================================7");
                String ipAdd = ((TextView)findViewById(R.id.editText2)).getText().toString();
                System.out.println("    ======================================7");
                if(!isIP(ipAdd)){
                    System.out.println("    ======================================999");
                    Toast.makeText(getApplicationContext(), "IP Address is not correctly", Toast.LENGTH_LONG).show();
                    System.out.println("    ======================================8888");
                    return;
                }
                System.out.println("    ======================================7");
                String port = ((TextView)findViewById(R.id.editText3)).getText().toString();
                if(!isNumeric(port)){
                    Toast.makeText(getApplicationContext(), "Port number is not correctly", Toast.LENGTH_LONG).show();
                    return;
                }

                Intent resultIntent = new Intent();
                resultIntent.putExtra("ipAdd", ipAdd);
                resultIntent.putExtra("port", port);
                setResult(RESULT_OK, resultIntent);
                finish();




            }
        });

    }

    public static boolean isIP(String addr)
    {
        if(addr.length() < 7 || addr.length() > 15 || "".equals(addr))

        {
            return false;
        }
        String ip="(2[5][0-5]|2[0-4]\\d|1\\d{2}|\\d{1,2})\\.(25[0-5]|2[0-4]\\d|1\\d{2}|\\d{1,2})\\.(25[0-5]|2[0-4]\\d|1\\d{2}|\\d{1,2})\\.(25[0-5]|2[0-4]\\d|1\\d{2}|\\d{1,2})";
        Pattern pattern = Pattern.compile(ip);
        Matcher matcher = pattern.matcher(addr);
        return matcher.matches();

    }

    public static boolean isNumeric(String str) {
        for (int i = 0; i < str.length(); i++) {
            if (!Character.isDigit(str.charAt(i))) {
                return false;
            }

        }
        return true;
    }


}