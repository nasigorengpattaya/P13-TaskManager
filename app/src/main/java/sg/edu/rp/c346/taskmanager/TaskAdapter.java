package sg.edu.rp.c346.taskmanager;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by 15017420 on 25/5/2017.
 */

public class TaskAdapter extends ArrayAdapter<Task>{

    Context context;
    ArrayList<Task> tasks;
    int resource;
    TextView tvId, tvDesc, tvTask;
    ImageView iv1, iv2, iv3, iv4, iv5;

    public TaskAdapter(Context context, int resource, ArrayList<Task> tasks) {
        super(context, resource, tasks);
        this.context = context;
        this.tasks = tasks;
        this.resource = resource;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View rowView = inflater.inflate(resource, parent, false);

        //Match the UI components with Java variables
        tvId = (TextView) rowView.findViewById(R.id.textViewId);
        tvTask = (TextView) rowView.findViewById(R.id.textViewTask);
        tvDesc = (TextView) rowView.findViewById(R.id.textViewDesc);

        Task task = tasks.get(position);

        int id = task.getId();
        String strTask = task.getTask();
        String content = task.getContent();

        tvId.setText(String.valueOf(id));
        tvTask.setText(strTask);
        tvDesc.setText(content);

        return rowView;
    }
}
