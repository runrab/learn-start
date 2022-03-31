package cn.edu.zut.gradesign.utils;

/**
 * @author o
 */
public class FileProcessException extends Exception {
	public FileProcessException(String msg) {
        super(msg);
    }

    public FileProcessException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
