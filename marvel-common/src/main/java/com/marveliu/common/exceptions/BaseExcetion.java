package com.marveliu.common.exceptions;

import com.marveliu.common.common.page.Result;

/**
 * @Author Marveliu
 * @Date 2018/9/13 下午11:28
 **/

public abstract class BaseExcetion extends RuntimeException {

    /**
     * Constructs a new runtime exception with the specified detail message.
     * The cause is not initialized, and may subsequently be initialized by a
     * call to {@link #initCause}.
     *
     * @param message the detail message. The detail message is saved for
     *                later retrieval by the {@link #getMessage()} method.
     */
    public BaseExcetion(String message) {
        super(message);
    }

    /**
     * Constructs a new runtime exception with {@code null} as its
     * detail message.  The cause is not initialized, and may subsequently be
     * initialized by a call to {@link #initCause}.
     */
    public BaseExcetion(Result result) {

    }
}
