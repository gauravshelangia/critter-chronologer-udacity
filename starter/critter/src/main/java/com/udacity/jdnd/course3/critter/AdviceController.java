package com.udacity.jdnd.course3.critter;

import com.udacity.jdnd.course3.critter.common.ApiResponseDto;
import com.udacity.jdnd.course3.critter.common.PetException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Locale;

@ControllerAdvice(basePackages = "com.udacity.jdnd.course3.critter", annotations = RestController.class)
public class AdviceController {
    private static Logger LOG = LoggerFactory.getLogger(AdviceController.class);

    @Autowired
    private MessageSource messageSource;


    @ExceptionHandler({PetException.class})
    @ResponseBody
    public ApiResponseDto petExceptionHandler(PetException e, Locale locale) {
        LOG.error("Error : ", e);
        String message = messageSource.getMessage(e.getErrorCode().getMessageKey(), e.getParams(), locale);
        return new ApiResponseDto(message, ApiResponseDto.STATUS_ERROR);
    }

    @ExceptionHandler({Exception.class})
    @ResponseBody
    public ApiResponseDto genericExceptionHandler(Exception e) {
        LOG.error("Error : ", e);
        return new ApiResponseDto("Unknown Error occurred", ApiResponseDto.STATUS_ERROR);
    }
}