package com.hellmail.hellmail.Dominio;

/**
 * Created by Hellmail on 11-Nov-17.
 */

public interface CallBackInteractor<T> {

    void success(T data);

    void error(String error);
}
