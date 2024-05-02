package com.danielxavier.dsnewcatalog.controllers.exceptions;

import java.time.Instant;

public record RStandardError(Instant timestamp, Integer status, String error, String message, String path) {
}
