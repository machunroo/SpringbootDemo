package com.example.core.config.exception;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.servlet.http.HttpServletRequest;

/**
 * 공통에러처리
 */
@Slf4j
@ControllerAdvice
public class GlobalExceptionConfig {

    public static final String DEFAULT_ERROR_VIEW = "error";

    /**
     * 존재하지 않는 화면 호출시 처리
     * @param ex
     * @param model
     * @return
     */
    @ExceptionHandler(NoHandlerFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public Model handle404(HttpServletRequest req, Exception ex, Model model) {
        log.error("[404] 페이지를 찾을 수 없음");

        model.addAttribute("msg", "페이지를 찾을 수 없음");
//        model.addAttribute("exception", ExceptionUtils.getStackTrace(ex));
        return model;
    }

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Model handleException(HttpServletRequest req, Model model) {
        log.error("오류가 발생 하였습니다.........., statusCode:               ");
        model.addAttribute("msg","오류발생");
//        model.addAttribute("exception", ExceptionUtils.getStackTrace(ex));
        return model;
    }

}
