package hr.tvz.productservice.model.dto;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.Nullable;

import java.util.Map;

/**
 * A class representing DTO exception message response body.
 */
@Slf4j
@Data
public class MessagedExceptionDto {

    private String exceptionCode;
    private String description;
    private String additionalDescription;
    private String additionalParameters;
    private boolean isFatal;

    /**
     * @param exceptionCode
     * @param exceptionDescription
     * @param additionalExceptionDescription
     * @param additionalParameters
     * @param isFatal
     */
    public MessagedExceptionDto(String exceptionCode,
                                String exceptionDescription,
                                @Nullable String additionalExceptionDescription,
                                @Nullable Map<String, String> additionalParameters,
                                boolean isFatal) {
        super();
        this.exceptionCode = exceptionCode;
        this.description = exceptionDescription;
        this.additionalDescription = additionalExceptionDescription;
        convertMapToJson(additionalParameters);
        this.isFatal = isFatal;
    }

    /**
     * A helper method for mapping a map to JSON representation.
     *
     * @param additionalParameters
     */
    private void convertMapToJson(Map<String, String> additionalParameters) {
        try {
            this.additionalParameters = new ObjectMapper().writeValueAsString(additionalParameters);
        } catch (JsonProcessingException e) {
            log.error("Exception: ", e);
            throw new RuntimeException(e);
        }
    }

}
