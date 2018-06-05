package www.service.captchaservice.global;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import www.service.captchaservice.controller.AccessDeniedException;
import www.service.captchaservice.dao.CaptchaNotFoundException;
import www.service.captchaservice.dao.ClientNotFoundException;
import www.service.captchaservice.model.ErrorMessage;
import www.service.captchaservice.model.Message;
import www.service.captchaservice.model.ParameterNotFoundException;
import www.service.captchaservice.service.BadAnswerException;
import www.service.captchaservice.service.BadTokenException;
import www.service.captchaservice.service.CaptchaExpiredException;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(AccessDeniedException.class)
    private ResponseEntity<Message> handleBadAccessException() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        return new ResponseEntity<>(new ErrorMessage("Доступ запрещен"), headers, HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(ParameterNotFoundException.class)
    private ResponseEntity<Message> handleParameterNotFoundException(ParameterNotFoundException e) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        return new ResponseEntity<>(new ErrorMessage("Не найден параметр " + e.getMessage()), headers, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ClientNotFoundException.class)
    private ResponseEntity<Message> handleClientNotFoundException() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        return new ResponseEntity<>(new ErrorMessage("Пользователь не найден"), headers, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(CaptchaNotFoundException.class)
    private ResponseEntity<Message> handleCaptchaNotFoundException() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        return new ResponseEntity<>(new ErrorMessage("Каптча не найдена"), headers, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(CaptchaExpiredException.class)
    private ResponseEntity<Message> handleCaptchaExpiredException() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        return new ResponseEntity<>(new ErrorMessage("Истекло время ожидания"), headers, HttpStatus.REQUEST_TIMEOUT);
    }

    @ExceptionHandler(BadAnswerException.class)
    private ResponseEntity<Message> handleBadAnswerException() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        return new ResponseEntity<>(new ErrorMessage("Неверный ответ"), headers, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(BadTokenException.class)
    private ResponseEntity<Message> handleBadTokenException() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        return new ResponseEntity<>(new ErrorMessage("Неверный токен"), headers, HttpStatus.BAD_REQUEST);
    }

}
