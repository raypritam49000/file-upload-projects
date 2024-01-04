package com.rest.api.project.exceptions;

public class FileNotSupportedException extends RuntimeException {

	public FileNotSupportedException() {
	}

	public FileNotSupportedException(String message) {
		super(message);
	}
}
