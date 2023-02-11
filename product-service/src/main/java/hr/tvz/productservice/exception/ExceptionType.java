package hr.tvz.productservice.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;
import org.springframework.http.HttpStatus;

/**
 * An enum representing exception types.
 */
@AllArgsConstructor
@ToString
@Getter
public enum ExceptionType {

    /* 400 Bad Request */
    BAD_CONVERSION("BAD_CONVERSION", "error.badConversion", HttpStatus.BAD_REQUEST),
    BAD_REQUEST("BAD_REQUEST", "error.badRequest", HttpStatus.BAD_REQUEST),

    /* 403 Forbidden */
    ACCESS_DENIED("ACCESS_DENIED", "error.accessDenied", HttpStatus.FORBIDDEN),

    /* 404 Not Found */
    NOT_FOUND("NOT_FOUND", "error.notFound", HttpStatus.NOT_FOUND),

    /* 500 Internal Server Error */
    INTERNAL_SERVER_ERROR("INTERNAL_SERVER_ERROR", "error.internalServerError", HttpStatus.INTERNAL_SERVER_ERROR);

    private String messageCode;
    private String description;
    private HttpStatus httpStatus;

}
