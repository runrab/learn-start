package cn.edu.zut.gradesign.utils;

import java.util.List;

public class ExcelImportCellNullException extends RuntimeException {
    List<List<String>> cellsError;

    public ExcelImportCellNullException(String msg) {
        super(msg);
    }

    public ExcelImportCellNullException(String msg, Throwable cause) {
        super(msg, cause);
    }

    public ExcelImportCellNullException(List<List<String>> cellsError) {
        this.cellsError = cellsError;
    }

    public List<List<String>> getCellsError() {
        return this.cellsError;
    }

    public void setCellsError(List<List<String>> cellsError) {
        this.cellsError = cellsError;
    }
}