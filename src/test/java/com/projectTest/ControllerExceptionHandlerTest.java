package com.projectTest;

import com.projectTest.exceptions.ControllerExceptionHandler;
import com.projectTest.exceptions.ObjectNotFoundException;
import com.projectTest.exceptions.StandardError;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;

public class ControllerExceptionHandlerTest {

    @InjectMocks
    private ControllerExceptionHandler handler;

    void setUp(){
        MockitoAnnotations.openMocks(this);
    }
@Test
    void whenObjectNotFoundExceptionThenReturnAResponseEntity(){
        ResponseEntity<StandardError> response = handler.objectNotFound(
                new ObjectNotFoundException("Objeto nao encontrado"));
    }
}
