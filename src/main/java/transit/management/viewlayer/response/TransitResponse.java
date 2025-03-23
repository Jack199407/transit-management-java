package transit.management.viewlayer.response;

public class TransitResponse<T> {
    private boolean success;
    private String error;
    private T data;

    // 成功时使用
    public TransitResponse(boolean success, T data) {
        this.success = success;
        this.data = data;
        this.error = null;
    }

    // 失败时使用
    public TransitResponse(boolean success, String error) {
        this.success = success;
        this.error = error;
        this.data = null;
    }

    public boolean isSuccess() {
        return success;
    }

    public String getError() {
        return error;
    }

    public T getData() {
        return data;
    }
}
