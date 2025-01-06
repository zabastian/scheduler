glbal exceptionhandler 적용했습니다

json데이터로 반환받기 위해서는 기존 apiresponse보다는 새로 하나 만드는게 나을거 같아 apierrorresponse를 만들었습니다.

@ExceptionHandler(ValidateException.class)
    public ResponseEntity<ApiErrorResponse> handleValidateException(ValidateException ex) {
        ApiErrorResponse errorResponse = new ApiErrorResponse(
                ex.getErrorCode(),
                ex.getMessage()
        );
        return new ResponseEntity<>(errorResponse,HttpStatus.BAD_REQUEST);
    }

  errorResponse의 json 데이터와 상태코드를 받도록 만들었습니다.

  User에서 valid가 이미 적용이 되어있어서 유효성 검사는 기존코드를 유지했습니다.
  
  public record LoginRequest(
    @NotBlank
    @Email
    String email,
    @NotBlank
    String password
) {

}
