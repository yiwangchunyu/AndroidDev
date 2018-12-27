package wang.yiwangchunyu.androidfinal.bean;

import java.util.ArrayList;

public class StatusTimeLineResponseDemo {
    private ArrayList<StatusDemo> statuses;
    private int total_number;

    public ArrayList<StatusDemo> getStatuses() {
        return statuses;
    }

    public void setStatuses(ArrayList<StatusDemo> statuses) {
        this.statuses = statuses;
    }

    public int getTotal_number() {
        return total_number;
    }

    public void setTotal_number(int total_number) {
        this.total_number = total_number;
    }
}
