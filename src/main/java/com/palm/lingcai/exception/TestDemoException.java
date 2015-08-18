package com.palm.lingcai.exception;

/**
 * 交易相关异常
 */
public class TestDemoException extends Exception {
	
    private static final long serialVersionUID = 1L;

    /**
     * message key
     */
    private String code;

    /**
     * message params
     */
    private Object[] values;

    public TestDemoException() {
    }

    public TestDemoException(String s) {
        super(s);
    }

    public TestDemoException(String s, String code) {
        super(s);
        this.code = code;
    }

    public TestDemoException(String s, Throwable throwable, String code, Object[] values) {
        super(s, throwable);
        this.code = code;
        this.values = values;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Object[] getValues() {
        return values;
    }

    public void setValues(Object[] values) {
        this.values = values;
    }
}
