package BusinessLayer;

public class Response {
    boolean ok;
    String msg;
    public Response(boolean ok, String msg) {
        this.ok = ok;
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }

    public boolean isOk() {
        return ok;
    }
}
