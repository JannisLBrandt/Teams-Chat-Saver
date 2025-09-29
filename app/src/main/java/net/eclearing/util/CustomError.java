package net.eclearing.util;

import net.eclearing.util.CustomErrorType;
import java.time.LocalDateTime;

public class CustomError {
    private LocalDateTime time;
    private CustomErrorType type;
    private String message;

    public CustomError(CustomErrorType type, String message) {
	this.time = LocalDateTime.now();
	this.type = type;
	this.message = message;
    }

    public LocalDateTime getTime() {
	return this.time;
    }
    
    public CustomErrorType getType() {
	return this.type;
    }

    public String getMessage() {
	return this.message;
    }

    public String toString() {
	return ("[" + this.time + "]" + this.type + ": " + this.message );
    }
}
