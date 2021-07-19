package io.simpolor.web.exception;

import lombok.Getter;

@Getter
public enum ExceptionType {

    /** 1000 */
    NOT_FOUND(1000, "존재하지 않습니다.");

    /** 2000 */

    /** 3000 */

    /** 4000 */

    /** 5000 */

    private Integer code;
    private String message;

    ExceptionType(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

}
