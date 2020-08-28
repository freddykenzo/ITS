package za.co.discovery.assignment.model.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Getter
public enum ResponseCode {

    /**
     * DEFAULT RESPONSE CODES
     */
    OK(Status.SUCCESS, 0, null, HttpStatus.OK),

    CREATED(Status.SUCCESS, 0, null, HttpStatus.CREATED),
    
    NOT_FOUND(Status.ERROR, 404, "The requested resource cannot be found", HttpStatus.NOT_FOUND),
    
    PLANET_NOT_FOUND(Status.ERROR, 404, "The planet cannot be found", HttpStatus.NOT_FOUND),

    ;

    private final String status;

    private final int code;

    private final String message;

    private final HttpStatus httpStatus;

    private static class Status {

        public static final String SUCCESS = "Success";

        public static final String ERROR = "Error";

    }
}
