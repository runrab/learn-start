package cn.edu.zut.gradesign.utils;

import java.util.List;

/**
 * @author o
 */
public class ExcelImportRowNullException extends Exception {
	List<String> rowsError;

    public ExcelImportRowNullException(String msg) {
        super(msg);
    }

    public ExcelImportRowNullException(String msg, Throwable cause) {
        super(msg, cause);
    }

    public ExcelImportRowNullException(List<String> rowsError) {
        this.rowsError = rowsError;
    }

    public List<String> getRowsError() {
        return this.rowsError;
    }

    public void setRowsError(List<String> rowsError) {
        this.rowsError = rowsError;
    }
}
