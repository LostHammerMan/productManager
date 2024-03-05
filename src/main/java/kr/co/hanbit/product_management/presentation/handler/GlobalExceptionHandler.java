package kr.co.hanbit.product_management.presentation.handler;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Path;
import kr.co.hanbit.product_management.domain.exception.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    // ConstraintViolationException : 도메인 객체에 대한 유효성 검사 실패

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ErrorMessage> handleConstraintViolationException(ConstraintViolationException ex){
        log.info("GlobalExceptionHandler called...");
        // 예외에 대한 처리
        Set<ConstraintViolation<?>> constraintViolations = ex.getConstraintViolations();

        List<String> errors = constraintViolations.stream()
                .map(
                        constraintViolation -> extractField(constraintViolation.getPropertyPath()) + ", " +
                                constraintViolation.getMessage()
                ).toList();


        ErrorMessage errorMessage = new ErrorMessage(errors);
        return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ErrorMessage> handleEntityNotFoundException(EntityNotFoundException ex){
        List<String> errors = new ArrayList<>();

        errors.add(ex.getMessage());

        ErrorMessage errorMessage = new ErrorMessage(errors);

        return new ResponseEntity<>(errorMessage, HttpStatus.NOT_FOUND);
    }


    private String extractField(Path path){
        String[] splitArray = path.toString().split("[.]");
        int lastIndex = splitArray.length -1;
        return splitArray[lastIndex];
    }
}
