package com.example.Education.validators;

public interface Validator <P, R>{
    R validate(P input);
}
