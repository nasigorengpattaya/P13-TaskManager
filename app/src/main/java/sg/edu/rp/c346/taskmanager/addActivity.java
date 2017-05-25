package sg.edu.rp.c346.taskmanager;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.icu.util.Calendar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class addActivity extends AppCompatActivity {

    Button btnAdd, btnCancel;
    EditText etName, etDesc, etTime;
    int reqCode = 12345;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        btnAdd = (Button)findViewById(R.id.buttonAdd);
        btnCancel = (Button)findViewById(R.id.buttonCancel);
        etName = (EditText)findViewById(R.id.editTextName);
        etDesc = (EditText)findViewById(R.id.editTextDescription);
        etTime = (EditText)findViewById(R.id.editTextTime);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = etName.getText().toString().trim();
                String desc = etDesc.getText().toString().trim();
                String time = etTime.getText().toString().trim();
                int seconds = Integer.parseInt(time);

                if (name.length() == 0 && desc.length()== 0)return;

                DBHelper dbh = new DBHelper(addActivity.this);
                dbh.insertTask(name, desc);
                dbh.close();
                Intent i = new Intent();
                i.putExtra("confirm", "Data parsed successfully");
                // Set result to RESULT_OK to indicate normal			// response and pass in the intent containing the 		// like
                setResult(RESULT_OK, i);

                Calendar cal = Calendar.getInstance();
                cal.add(Calendar.SECOND, seconds);

                Intent intent = new Intent(addActivity.this,
                        ScheduledNotificationReceiver.class);
                intent.putExtra("name", name);
                intent.putExtra("desc", desc);

                PendingIntent pendingIntent = PendingIntent.getBroadcast(
                        addActivity.this, reqCode,
                        intent, PendingIntent.FLAG_CANCEL_CURRENT);

                AlarmManager am = (AlarmManager)
                        getSystemService(Activity.ALARM_SERVICE);
                am.set(AlarmManager.RTC_WAKEUP, cal.getTimeInMillis(),
                        pendingIntent);

                finish();
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
