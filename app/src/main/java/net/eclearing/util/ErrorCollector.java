package net.eclearing.util;

import net.eclearing.util.CustomErrorType;
import net.eclearing.util.CustomError;
import java.util.ArrayDeque;

public class ErrorCollector {
    private ArrayDeque<String> errors;
    
    public ErrorCollector(int size) {
	this.errors = new ArrayDeque<String>(size);
    }
    
    public ErrorCollector(ArrayDeque<String> errors) {
	this.errors = errors;
    }

    public void addError(CustomError newError) {
	if (this.errors == null) {
	    System.out.println("Errors is null!");
	    return;
	}
	this.errors.add(newError.toString());
    }

    public void displayError() {
	for (String error : errors) {
	    System.out.println(error.toString());
	}
    }
}
