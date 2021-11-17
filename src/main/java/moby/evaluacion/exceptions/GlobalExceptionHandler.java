package moby.evaluacion.exceptions;

import moby.evaluacion.models.responses.ResponseMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(CandidatoNotFoundException.class)
    public ResponseEntity<ResponseMessage> handleCandidatoNotFoundException(CandidatoNotFoundException exception) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(ResponseMessage.builder()
                        .message(exception.getMessage()).build());
    }

    @ExceptionHandler(DniDuplicateException.class)
    public ResponseEntity<ResponseMessage> handleDniDuplicateException(DniDuplicateException exception) {
        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(ResponseMessage.builder()
                        .message(exception.getMessage()).build());
    }

    @ExceptionHandler(TecnologiaNotFoundException.class)
    public ResponseEntity<ResponseMessage> handleTecnologiaNotFoundException(TecnologiaNotFoundException exception) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(ResponseMessage.builder()
                        .message(exception.getMessage()).build());
    }

}
