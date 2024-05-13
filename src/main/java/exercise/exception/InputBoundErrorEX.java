package exercise.exception;

public class InputBoundErrorEX extends Exception {
    private String message;

    public InputBoundErrorEX(String message){
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
