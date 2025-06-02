package BookInventoryManage.example.inventory.Utils;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class ControllerAdvisor {

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<String> handleTypeMismatch(MethodArgumentTypeMismatchException ex) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body("Invalid value for parameter '" + ex.getName());
    }

    //    không tìm thấy tài nguyên
    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<Map<String, String>> handleResponseStatusException(ResponseStatusException ex) {
        Map<String, String> error = new HashMap<>();
        error.put("error", ex.getReason());
        return new ResponseEntity<>(error, ex.getStatusCode());
    }

    //    Validation input
    @ExceptionHandler(MethodArgumentNotValidException.class)
    protected ResponseEntity handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        return new ResponseEntity(e.getDetailMessageArguments(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    //    sai data khi gửi ben json
    @ExceptionHandler(HttpMessageNotReadableException.class)
    protected ResponseEntity hadleHttpMessageNotReadableException(HttpMessageNotReadableException e) {
        return new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    //   thường là sai đường dẫn, sai tên
    @ExceptionHandler(NoResourceFoundException.class)
    protected ResponseEntity hadleNoResourceFoundException(NoResourceFoundException e) {
        return new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    //    đặt sai tên property, không truyền đủ data
    @ExceptionHandler(NullPointerException.class)
    protected ResponseEntity hadleNullPointerException(NullPointerException e) {
        return new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    //    thieu PathVariable
    @ExceptionHandler(MissingPathVariableException.class)
    protected ResponseEntity hadleMissingPathVariableException(MissingPathVariableException e) {
        return new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }


}
