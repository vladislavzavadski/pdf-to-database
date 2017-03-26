package by.bsuir.componentsearcher.service.exception;

/**
 * Created by vladislav on 26.03.17.
 */
public class TooMuchResultException extends Throwable {
    private int resultCount;

    public int getResultCount() {
        return resultCount;
    }

    public TooMuchResultException(int resultCount){
        this.resultCount = resultCount;
    }
}
