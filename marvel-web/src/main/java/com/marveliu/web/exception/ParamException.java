package com.marveliu.web.exception;

import com.marveliu.web.component.page.Result;

/**
 * @Author Marveliu
 * @Date 2018/9/13 下午11:26
 **/

public class ParamException extends BaseExcetion {

    /**
     * Constructs a new runtime exception with {@code null} as its
     * detail message.  The cause is not initialized, and may subsequently be
     * initialized by a call to {@link #initCause}.
     *
     * @param result
     */
    public ParamException(Result result) {
        super(result);
    }

    /**
     * Constructs a new runtime exception with the specified detail message.
     * The cause is not initialized, and may subsequently be initialized by a
     * call to {@link #initCause}.
     *
     * @param message the detail message. The detail message is saved for
     *                later retrieval by the {@link #getMessage()} method.
     */
    public ParamException(String message) {
        super(message);
    }
}
