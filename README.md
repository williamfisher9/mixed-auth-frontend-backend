# Mixed Authentication Frontend and Backend

@Valid throws MethodArgumentNotValidException if any of the fields violates the constraints.

@NotNull: an empty value is legal but should not be null.
@NotEmpty: must not be null, and its size/length must be greater than zero.
@NotBlank: must not be null, and its trimmed length must be greater than zero.

If a list is annotated with @NotEmpty then it must have at least one element.
If a list is annotated with @NotNull then it can be empty.

MethodArgumentNotValidException can be handled using @RestControllerAdvice @ExceptionHandler by reading exc.getBindingResult().FieldErrors():

```ControllerExceptionsHandler.java
Map<String, String> errors = new HashMap<>();

for(FieldError error : exc.getBindingResult().getFieldErrors()){
    errors.put(error.getField(), error.getDefaultMessage());
}
```