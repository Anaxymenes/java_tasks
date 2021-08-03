package dev.anaxymenes.springboot.error;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.Arrays;
import java.util.List;

@ControllerAdvice
public class ErrorControllerHandler {
    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<Object> handleNullPointerException(NullPointerException ex, WebRequest request){
        ErrorResponse response = new ErrorResponse(ex.getMessage());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(RecordNotFound.class)
    public ResponseEntity<Object> handleRecordNotFoundException(RecordNotFound ex, WebRequest request){
        ErrorResponse response = new ErrorResponse(ex.getMessage());
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(UnsupportedOperationException.class)
    public ResponseEntity<Object> handleUnsupportedOperationException(UnsupportedOperationException ex, WebRequest request){
        ErrorResponse response = new ErrorResponse(ex.getMessage(), Arrays.asList(
                "Class: " + ex.getStackTrace()[0].getClassName(),
                "Method: " + ex.getStackTrace()[0].getMethodName()
        ));
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }


    static class ErrorResponse{
        private String message;
        private List<String> details;

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public List<String> getDetails() {
            return details;
        }

        public void setDetails(List<String> details) {
            this.details = details;
        }

        public ErrorResponse(String message) {
            this.message = message;
        }

        public ErrorResponse(String message, List<String> details) {
            this.message = message;
            this.details = details;
        }
    }
}
