package clouddev.mall;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;
import clouddev.com.czy.app.appInit;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toast.makeText(appInit.getApplication(),"闷声大发财",Toast.LENGTH_LONG).show();
    }
}
