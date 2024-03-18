package com.tunebaker.farm.exception;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
//@RequiredArgsConstructor
@ToString
@Accessors(chain = true, fluent = true)
public class AppErrorInfo {
    private int status;
    private String exception;
    private String message;
    private Date date;
}
