package com.rexi.cola.search.status.model;

/**
 * Created by moi on 05/02/2017.
 */
public class Status {

    public final boolean status;

    public final String code;

    public final String message;

    public Status(boolean status, String code)
    {
        this(status, code, null);
    }

    public Status(boolean status, String code, String message)
    {
        this.status = status;
        this.code   = code;
        this.message = message;
    }

    public boolean getStatus()
    {
        return this.status;
    }

    public String getCode()
    {
        return this.code;
    }

    public String getMessage()
    {
        return this.message;
    }
}