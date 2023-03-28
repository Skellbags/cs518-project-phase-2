package edu.unh.cs.cs518.motivate.models;

public class InvalidAuthorException extends Exception {
    public InvalidAuthorException() {
        super("Unable to find any quotes by specified author.");
    }
}
