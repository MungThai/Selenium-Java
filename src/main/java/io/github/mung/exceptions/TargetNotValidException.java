package io.github.mung.exceptions;

public class TargetNotValidException extends IllegalStateException {

    public TargetNotValidException(String target) {
        super(String.format("Target %s not supported. User either local or grid", target));
    }
}
