package sg.edu.rp.c346.taskmanager;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView lvTasks;
    Button btnAdd;
    ArrayList<Task> al;
    ArrayAdapter<Task> aa;
    int requestCode1 = 123;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lvTasks = (ListView)findViewById(R.id.listViewTask);
        btnAdd = (Button)findViewById(R.id.buttonAddTask);

        al = new ArrayList<Task>();

        DBHelper dbh = new DBHelper(this);
        ArrayList<String> data = dbh.getTaskContent();
        dbh.close();

        final ArrayList<Task> tasks = dbh.getAllTasks();

        aa = new TaskAdapter(this, R.layout.row, tasks);
        lvTasks.setAdapter(aa);

        for (int i=0; i<data.size(); i++) {
            Log.d("Database content", i + ". " + tasks.get(i));
            aa = new TaskAdapter(MainActivity.this,R.layout.row, al);

        }

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, addActivity.class);
                startActivityForResult(i, requestCode1);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Only handle when 2nd activity closed normally
        //  and data contains something
        if (resultCode == RESULT_OK) {
            if (data != null) {
                // Get data passed back from 2nd activity
                String confirm = data.getStringExtra("confirm");
                String statement = "";
                // If it is activity started by clicking 				//  Superman, create corresponding String
                if (requestCode == requestCode1) {
                    statement = confirm;
                    DBHelper dbh = new DBHelper(MainActivity.this);
                    al.clear();
                    al.addAll(dbh.getAllTasks());
                    dbh.close();
                    lvTasks.setAdapter(aa);
                    aa.notifyDataSetChanged();
                }

                Toast.makeText(MainActivity.this, statement,
                        Toast.LENGTH_LONG).show();
            }

        }


    }
}
