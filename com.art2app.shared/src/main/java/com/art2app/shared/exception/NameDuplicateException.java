package com.art2app.shared.exception;

public class NameDuplicateException extends Exception {
	
	public NameDuplicateException(){
        super();
    }
    public NameDuplicateException(String msg){
        super(msg);
    }
}
