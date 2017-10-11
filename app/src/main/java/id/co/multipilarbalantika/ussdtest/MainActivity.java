package id.co.multipilarbalantika.ussdtest;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    public static String TAG = MainActivity.class.getSimpleName();
    public static TextView mResponseUssdText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (ActivityCompat.checkSelfPermission(MainActivity.this, Manifest.permission.SYSTEM_ALERT_WINDOW) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.SYSTEM_ALERT_WINDOW}, 1);
        }

        Button mCekLastTransactionButton = (Button) findViewById(R.id.cek_last_transaction_button);
        mCekLastTransactionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ussdCall("*627*200*100*1");
            }
        });

        Button mPlnPraBayarTransactionButton = (Button) findViewById(R.id.pln_pra_transaction_button);
        mPlnPraBayarTransactionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ussdCall("*627*200*101*123456789!2!123456");
            }
        });

        Button mPlnPascaBayarTransactionButton = (Button) findViewById(R.id.pln_pasca_transaction_button);
        mPlnPascaBayarTransactionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ussdCall("*627*200*102*123456789!123456");
            }
        });

        mResponseUssdText = (TextView) findViewById(R.id.response_ussd_text);
    }

    public void ussdCall(String num) {
        if (ActivityCompat.checkSelfPermission(MainActivity.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.CALL_PHONE}, 1);
        }
        String encodedHash = Uri.encode("#");
        String ussdCode = "tel:" + num;
        startActivity(new Intent(Intent.ACTION_CALL, Uri.parse(ussdCode + encodedHash)));
        mResponseUssdText.setText("Calling " + ussdCode + "#");
    }
}
