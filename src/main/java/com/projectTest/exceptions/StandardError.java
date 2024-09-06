package com.projectTest.exceptions;

import java.time.LocalDateTime;

public record StandardError (LocalDateTime timestamp, Integer status, String error){

}
