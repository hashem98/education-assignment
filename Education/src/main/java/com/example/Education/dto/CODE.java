package com.example.Education.dto;

public enum CODE {
    OK(200),
    NOT_FOUND(404),
    INTERNAL_SERVER_ERROR(500),
    BAD_REQUEST(400),
    UNAUTHORIZED(401),
    FORBIDDEN(403);
    final int id;
    CODE(int id) {
        this.id = id;
    }
    public int getId() {
        return id;
    }
}
