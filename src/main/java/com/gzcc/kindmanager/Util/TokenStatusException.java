package com.gzcc.kindmanager.Util;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.FORBIDDEN,reason = "token失效!")//403
public class TokenStatusException extends RuntimeException {

}

@ResponseStatus(value = HttpStatus.UNAUTHORIZED,reason = "没有权限!")//401
 class NoauthorityException extends RuntimeException{

    }

