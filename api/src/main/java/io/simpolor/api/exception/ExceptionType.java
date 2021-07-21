package io.simpolor.api.exception;

import lombok.Getter;

@Getter
public enum ExceptionType {

    /** 1000 : 프로세스 */

    /** 2000 : 요청 오류 */
    NOT_FOUND(2000, "Not found"),
    WRONG_API_USAGE(2100, "Wrong api usage"),

    /** 3000 : 응답 오류 */

    /** 4000 : 통신 오류 */

    /** 5000 : 시스템 오류 */
    UNKNOWN ( 5000, "Unknown");

    private Integer code;
    private String message;

    ExceptionType(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

}
