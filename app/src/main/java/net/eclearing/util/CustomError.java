package net.eclearing.util;

import net.eclearing.util.CustomErrorType;

public class CustomError {
    private CustomErrorType type;
    private String message;

    public CustomError(CustomErrorType type, String message) {
	this.type = type;
	this.message = message;
    }

    public CustomErrorType getType() {
	return this.type;
    }

    public String getMessage() {
	return this.message;
    }

    public String toString() {
	return (this.type + ": " + this.message );
    }
}
