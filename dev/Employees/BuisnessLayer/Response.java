package Employees.BuisnessLayer;

public class Response {
    private String ErrorMessage;
    public boolean ErrorOccured;

    public Response(){ErrorOccured = false;}

    public Response(String errorMessage) {
        ErrorMessage = errorMessage;
        ErrorOccured = errorMessage != null;
    }

    public boolean isErrorOccured() {
        return ErrorOccured;
    }

    public String getErrorMessage() {
        return ErrorMessage;
    }
}