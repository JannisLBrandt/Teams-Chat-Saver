package net.eclearing.util;

import net.eclearing.util.CustomError;
import java.util.ArrayDeque;

public class CustomErrorCollector {
    private static CustomErrorCollector INSTANCE = new CustomErrorCollector();
    private static ArrayDeque<CustomError> errorList = new ArrayDeque<CustomError>();
    
    private CustomErrorCollector() {}

    public static CustomErrorCollector getInstance() {
	return INSTANCE;
    }

    public static ArrayDeque<CustomError> getErrorList() {
	return errorList;
}
    
    public static void addError(CustomError newError) {
	errorList.addFirst(newError);
    }

    public static void displayErrors() {
	errorList.forEach(System.out::println);
    }
}
