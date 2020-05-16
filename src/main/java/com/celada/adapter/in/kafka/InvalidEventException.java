package com.celada.adapter.in.kafka;

public class InvalidEventException extends RuntimeException {

  public InvalidEventException(String message) {
    super("Error reading event: " + message);
  }
}
