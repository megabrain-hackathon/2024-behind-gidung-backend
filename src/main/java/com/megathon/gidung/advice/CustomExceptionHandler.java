package com.megathon.gidung.advice;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.multipart.support.MissingServletRequestPartException;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.nio.file.AccessDeniedException;
import java.util.Objects;

@Slf4j
@RestControllerAdvice
public class CustomExceptionHandler {
    @Getter
    public class RestResponse {

        private final boolean success;

        private final String message;

        private String detail;

        private final String code;

        public RestResponse(boolean success, String message, String code) {
            this.success = success;
            this.message = message;
            this.code = code;
        }

        public RestResponse(boolean success, String message, Integer code) {
            this.success = success;
            this.message = message;
            this.code = String.valueOf(code);
        }

        public RestResponse(boolean success, String message, String detail, String code) {
            this.success = success;
            this.message = message;
            this.detail = detail;
            this.code = code;
        }

        @Override
        public String toString() {
            return "success=" + success +
                    ", message='" + message + '\'' +
                    ", detail='" + detail + '\'' +
                    ", code='" + code;
        }
    }
    private RestResponse makeErrorResponse(BindingResult bindingResult) {
        String code = "";
        String description = "";
        String detail = "";

        //에러가 있다면
        if (bindingResult.hasErrors()) {
            //DTO에 설정한 message값을 가져온다.
            detail = bindingResult.getFieldError().getDefaultMessage();

            //DTO에 유효성체크를 걸어놓은 어노테이션명을 가져온다.
            String bindResultCode = bindingResult.getFieldError().getCode();

            switch (Objects.requireNonNull(bindResultCode)) {
                case "NotNull":
                    code = ErrorCode.NOT_NULL.getCode();
                    description = ErrorCode.NOT_NULL.getDescription();
                    break;
                case "NotBlank":
                    code = ErrorCode.NOT_BLANK.getCode();
                    description = ErrorCode.NOT_NULL.getDescription();
                    break;
                case "Pattern":
                    code = ErrorCode.PATTERN.getCode();
                    description = ErrorCode.PATTERN.getDescription();
                    break;
                case "Email":
                    code = ErrorCode.EMAIL.getCode();
                    description = ErrorCode.EMAIL.getDescription();
                    break;
            }
        }
        return new RestResponse(false, description, detail, code);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity handleRuntimeException(RuntimeException e) {
        log.info(String.valueOf(e.getCause()));
        log.info(printStackTrage(e));
        RestResponse response = new RestResponse(false, e.getMessage(), HttpStatus.BAD_REQUEST.value());
        return new ResponseEntity(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity handleAccessDeniedException(AccessDeniedException e) {
        RestResponse response = new RestResponse(false, e.getMessage(), HttpStatus.FORBIDDEN.value());
        return new ResponseEntity(response, HttpStatus.FORBIDDEN);
    }
    @ExceptionHandler({NullPointerException.class})
    public ResponseEntity handleInternal(final NullPointerException e) {
        RestResponse response = new RestResponse(false, e.getLocalizedMessage(),
                HttpStatus.INTERNAL_SERVER_ERROR.value());
        return new ResponseEntity(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler({MethodArgumentNotValidException.class})
    public ResponseEntity handleMethodArgumentNotValidException(final MethodArgumentNotValidException e) {
        RestResponse response = makeErrorResponse(e.getBindingResult());
        return new ResponseEntity(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({MissingServletRequestPartException.class})
    public ResponseEntity handleMissingServletRequestPartException(final MissingServletRequestPartException e) {
        RestResponse response = new RestResponse(false, e.getMessage(), HttpStatus.BAD_REQUEST.value());
        return new ResponseEntity(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(IOException.class)
    public ResponseEntity handleIOException(IOException e) {
        RestResponse response = new RestResponse(false, e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value());
        return new ResponseEntity(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity handleException(Exception e) {
        RestResponse response = new RestResponse(false, e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value());
        return new ResponseEntity(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private String printStackTrage(Exception e) {
        StringWriter sw = new StringWriter();
        e.printStackTrace(new PrintWriter(sw));
        return sw.toString();
    }
}
