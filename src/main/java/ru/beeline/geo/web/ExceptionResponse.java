package ru.beeline.geo.web;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
public class ExceptionResponse {
    @Getter
    @Setter
    private String errorMessage;
}
