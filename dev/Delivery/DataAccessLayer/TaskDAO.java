package Delivery.DataAccessLayer;

public class TaskDAO {
    private static TaskDAO instance = null;

    public TaskDAO getInstance(){
        if (instance == null)
            instance = new TaskDAO();
        return instance;
    }
}
