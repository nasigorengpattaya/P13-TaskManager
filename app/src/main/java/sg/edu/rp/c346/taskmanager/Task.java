package sg.edu.rp.c346.taskmanager;

/**
 * Created by 15017420 on 25/5/2017.
 */

public class Task {

    private int id;
    private String task;
    private String content;

    public Task(int id, String task, String content) {
        this.id = id;
        this.task = task;
        this.content = content;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
