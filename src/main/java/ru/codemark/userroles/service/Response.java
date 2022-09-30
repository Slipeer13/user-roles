package ru.codemark.userroles.service;

import lombok.Data;

import java.util.List;

@Data
public class Response {

    private Boolean success;

    private List<String> errors;

}
