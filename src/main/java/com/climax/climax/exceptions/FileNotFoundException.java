package com.climax.climax.exceptions;

public class FileNotFoundException extends RuntimeException{

    public FileNotFoundException(String message){
        super(message);
    }
    public FileNotFoundException(){
        super("this File is not valide");
    }
}
