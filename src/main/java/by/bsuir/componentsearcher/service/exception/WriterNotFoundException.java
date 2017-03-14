package by.bsuir.componentsearcher.service.exception;


/**
 * Created by vladislav on 15.03.17.
 */
public class WriterNotFoundException extends Exception {
    public WriterNotFoundException() {
    }

    public WriterNotFoundException(String message) {
        super(message);
    }

    public WriterNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public WriterNotFoundException(Throwable cause) {
        super(cause);
    }

    public WriterNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
