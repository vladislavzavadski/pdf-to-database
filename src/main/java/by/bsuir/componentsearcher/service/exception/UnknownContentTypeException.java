package by.bsuir.componentsearcher.service.exception;

/**
 * Created by ulza1116 on 3/13/2017.
 */
public class UnknownContentTypeException extends Exception {

    public UnknownContentTypeException(String message) {
        super(message);
    }

    public UnknownContentTypeException(String message, Throwable cause) {
        super(message, cause);
    }

    public UnknownContentTypeException(Throwable cause) {
        super(cause);
    }

    public UnknownContentTypeException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
