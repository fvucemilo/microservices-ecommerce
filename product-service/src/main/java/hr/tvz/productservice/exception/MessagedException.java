package hr.tvz.productservice.exception;

import lombok.Data;

import java.util.Map;

/**
 * A class representing custom message exception.
 */
@Data
public class MessagedException extends RuntimeException {

    private static final long serialVersionUID = 2359199311011385094L;

    private final ExceptionType exceptionType;
    private final String additionalExceptionDescription;
    private final Map<String, String> additionalParameters;
    private final boolean isFatal;

    /**
     * @param exceptionType
     * @param additionalExceptionDescription
     * @param isFatal
     * @param additionalParameters
     * @param cause
     */
    public MessagedException(ExceptionType exceptionType, String additionalExceptionDescription, boolean isFatal, Map<String, String> additionalParameters, Throwable cause) {
        super(exceptionType.toString(), cause);
        this.exceptionType = exceptionType;
        this.isFatal = isFatal;
        this.additionalParameters = additionalParameters;
        this.additionalExceptionDescription = additionalExceptionDescription;
    }

    /**
     * @param exceptionType
     */
    public MessagedException(ExceptionType exceptionType) {
        this(exceptionType, null, false, null, null);
    }

    /**
     * @param exceptionType
     * @param cause
     */
    public MessagedException(ExceptionType exceptionType, Throwable cause) {
        this(exceptionType, null, false, null, cause);
    }

    /**
     * @param exceptionType
     * @param isFatal
     */
    public MessagedException(ExceptionType exceptionType, boolean isFatal) {
        this(exceptionType, null, isFatal, null, null);
    }

    /**
     * @param exceptionType
     * @param additionalExceptionDescription
     */
    public MessagedException(ExceptionType exceptionType, String additionalExceptionDescription) {
        this(exceptionType, additionalExceptionDescription, false, null, null);
    }

    /**
     * @param exceptionType
     * @param additionalExceptionDescription
     * @param isFatal
     */
    public MessagedException(ExceptionType exceptionType, String additionalExceptionDescription, boolean isFatal) {
        this(exceptionType, additionalExceptionDescription, isFatal, null, null);
    }

    /**
     * @param exceptionType
     * @param additionalExceptionDescription
     * @param isFatal
     * @param additionalParameters
     */
    public MessagedException(ExceptionType exceptionType, String additionalExceptionDescription, boolean isFatal, Map<String, String> additionalParameters) {
        this(exceptionType, additionalExceptionDescription, isFatal, additionalParameters, null);
    }

    /**
     * @param exceptionType
     * @param additionalParameters
     */
    public MessagedException(ExceptionType exceptionType, Map<String, String> additionalParameters) {
        this(exceptionType, null, false, additionalParameters, null);
    }

    /**
     * @param exceptionType
     * @param isFatal
     * @param additionalParameters
     */
    public MessagedException(ExceptionType exceptionType, boolean isFatal, Map<String, String> additionalParameters) {
        this(exceptionType, null, isFatal, additionalParameters, null);
    }

}
