package com.Online_Quiz_Application_API.ExeptionHandling;

public class Quiz_Not_Found extends RuntimeException {
	
	private int message;
	
	public Quiz_Not_Found(int message) {
		this.message=message;
	}
	@Override
    public String getMessage() {
        return "Quiz not found with id: " + message;
    }
	

}
