package com.example.AddressBook.exeption;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.util.Collections;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class GlobalExceptionHandlerTest {

    @InjectMocks
    private GlobalExceptionHandler globalExceptionHandler;

    @Mock
    private MethodArgumentNotValidException methodArgumentNotValidException;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testHandleNoSuchCustomerExistsException() {
        NoSuchCustomerExistsException ex = new NoSuchCustomerExistsException("Customer not found");
        ErrorResponse response = globalExceptionHandler.handleException(ex);

        assertEquals(404, response.getStatusCode());
        assertEquals(ex.getMessage(), response.getMessage());
    }

    @Test
    public void testHandleContactAlreadyExistsException() {
        ContactAlreadyExistsException ex = new ContactAlreadyExistsException("Contact already exists");
        ErrorResponse response = globalExceptionHandler.handleException(ex);

        assertEquals(208, response.getStatusCode());
        assertEquals(ex.getMessage(), response.getMessage());
    }


    @Test
    public void testHandleNoHandlerFoundException() {
        NoHandlerFoundException ex = new NoHandlerFoundException("GET", "/url", null);
        ErrorResponse response = globalExceptionHandler.handle404Request(ex);

        assertEquals(400, response.getStatusCode());
        assertEquals(ex.getMessage(), response.getMessage());
    }
}
