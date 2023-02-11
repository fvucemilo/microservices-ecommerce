package hr.tvz.productservice.exception;

import hr.tvz.productservice.model.dto.MessagedExceptionDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.MessageSource;
import org.springframework.core.convert.ConversionFailedException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.nio.file.AccessDeniedException;
import java.util.HashMap;
import java.util.Map;

import static java.util.Locale.getDefault;

/**
 * A class representing AOP advice for global exception handling.
 */
@Slf4j
@RequiredArgsConstructor
@ControllerAdvice
public class GlobalExceptionHandler {

    private final MessageSource messageSource;

    /**
     * Exception handling during conversion from request body to Java object.
     *
     * @param e Exception
     * @return Exception information
     */
    @ExceptionHandler(ConversionFailedException.class)
    public ResponseEntity<MessagedExceptionDto> conversionFailedException(ConversionFailedException e) {
        log.error("ConversionFailedException: ", e);
        ExceptionType exceptionType = ExceptionType.BAD_CONVERSION;
        String message = getMessageByDescription(exceptionType.getDescription());
        MessagedExceptionDto dto = new MessagedExceptionDto(exceptionType.getMessageCode(), message, e.getMessage(), null, false);
        return new ResponseEntity<>(dto, exceptionType.getHttpStatus());
    }

    /**
     * Exception processing when the body or parts of the body are missing in the request.
     * This exception is mostly thrown by controller methods when they cannot find their parameters in the body.
     *
     * @param e Exception
     * @return Exception information
     */
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<MessagedExceptionDto> httpMessageNotReadableException(HttpMessageNotReadableException e) {
        log.error("HttpMessageNotReadableException: ", e);
        ExceptionType exceptionType = ExceptionType.BAD_REQUEST;
        String message = getMessageByDescription(exceptionType.getDescription());
        MessagedExceptionDto dto = new MessagedExceptionDto(exceptionType.getMessageCode(), message, e.getMessage(), null, false);
        return new ResponseEntity<>(dto, exceptionType.getHttpStatus());
    }

    /**
     * Exception handling when the user does not have the right to access the resource or action.
     *
     * @param e Exception
     * @return Exception information
     */
    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<MessagedExceptionDto> accessDeniedException(AccessDeniedException e) {
        log.error("AccessDeniedException: ", e);
        ExceptionType exceptionType = ExceptionType.ACCESS_DENIED;
        String message = getMessageByDescription(exceptionType.getDescription());
        MessagedExceptionDto dto = new MessagedExceptionDto(exceptionType.getDescription(), message, e.getMessage(), null, true);
        return new ResponseEntity<>(dto, exceptionType.getHttpStatus());
    }

    /**
     * Exception handling when validating models/DTOs.
     *
     * @param e Exception
     * @return Exception information
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<MessagedExceptionDto> methodArgumentNotValidException(MethodArgumentNotValidException e) {
        log.error("MethodArgumentNotValidException: ", e);
        Map<String, String> errors = new HashMap<>();
        e.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        ExceptionType exceptionType = ExceptionType.BAD_REQUEST;
        String message = getMessageByDescription(exceptionType.getDescription());
        MessagedExceptionDto dto = new MessagedExceptionDto(exceptionType.getMessageCode(), message, null, errors, false);
        return new ResponseEntity<>(dto, HttpStatus.BAD_REQUEST);
    }

    /**
     * Main Exception Processing.
     *
     * @param e Exception
     * @return Exception information
     */
    @ExceptionHandler(MessagedException.class)
    public ResponseEntity<MessagedExceptionDto> messagedException(MessagedException e) {
        log.error("MessagedException: ", e);
        ExceptionType exceptionType = e.getExceptionType();
        String message = getMessageByDescription(exceptionType.getDescription());
        MessagedExceptionDto dto = new MessagedExceptionDto(exceptionType.getMessageCode(), message, e.getAdditionalExceptionDescription(), null, e.isFatal());
        return new ResponseEntity<>(dto, exceptionType.getHttpStatus());
    }

    /**
     * Handling of all other unexpected exceptions.
     *
     * @param e Exception
     * @return Exception information
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<MessagedExceptionDto> exception(Exception e) {
        log.error("Unexpected error: ", e);
        ExceptionType exceptionType = ExceptionType.INTERNAL_SERVER_ERROR;
        String message = getMessageByDescription(exceptionType.getDescription());
        MessagedExceptionDto dto = new MessagedExceptionDto(exceptionType.getMessageCode(), message, e.getMessage(), null, true);
        return new ResponseEntity<>(dto, exceptionType.getHttpStatus());
    }

    /**
     * Helping method for getting message by description.
     *
     * @param description Message description
     * @return Message
     */
    private String getMessageByDescription(String description) {
        return messageSource.getMessage(description, null, getDefault());
    }

}
