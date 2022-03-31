package cn.edu.zut.gradesign.utils;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author o
 */
public class ExcelUtils {
    public ExcelUtils() {
    }

    public static Workbook getWorkbook(HttpServletRequest request, String filePath) throws Exception {
        String realPath = request.getSession().getServletContext().getRealPath(filePath);
        File file = new File(realPath);
        FileInputStream inputStream = new FileInputStream(file);

        Object var6;
        try {
            Workbook workbook = null;
            if (file.getName().toLowerCase().endsWith("xlsx")) {
                workbook = new XSSFWorkbook(inputStream);
            } else if (file.getName().toLowerCase().endsWith("xls")) {
                workbook = new HSSFWorkbook(inputStream);
            }

            if (workbook == null) {
                throw new FileProcessException("未检测到Excel，请检查Excel文件格式！");
            }

            var6 = workbook;
        } catch (IOException var10) {
            throw new FileProcessException("文件打开异常，请检查Excel文件格式！");
        } finally {
            inputStream.close();
        }

        return (Workbook) var6;
    }

    public static void downloadExcel(HttpServletResponse response, HttpServletRequest request, Workbook workbook,
                                     String fileName) {
        try {
            String userAgent = request.getHeader("User-Agent");
            if (!userAgent.contains("MSIE") && !userAgent.contains("Trident")) {
                fileName = new String(fileName.getBytes("UTF-8"), "ISO-8859-1");
            } else {
                fileName = URLEncoder.encode(fileName, "UTF-8");
            }

            response.setHeader("Content-disposition", String.format("attachment; filename=\"%s\"", fileName));
            response.setContentType("application/vnd.ms-excel;charset=utf-8");
            response.setCharacterEncoding("UTF-8");
            OutputStream os = new BufferedOutputStream(response.getOutputStream());
            workbook.write(os);
            os.close();
        } catch (Exception var6) {
            var6.printStackTrace();
        }

    }

    // 分批处理
    public static List<Row> getExcelRows(MultipartFile file) throws Exception {
        if (file.getSize() == 0L) {
            throw new FileProcessException("未获取到文件，请正确上传Excel文件！");
        } else {
            List<Row> rowList = new ArrayList();
            Object workbook = null;

            try {
                if (file.getOriginalFilename().toLowerCase().endsWith("xlsx")) {
                    workbook = new XSSFWorkbook(file.getInputStream());
                } else if (file.getOriginalFilename().toLowerCase().endsWith("xls")) {
                    workbook = new HSSFWorkbook(file.getInputStream());
                }
            } catch (IOException var14) {
                throw new FileProcessException("文件打开异常，请检查Excel文件格式！");
            }

            if (workbook == null) {
                throw new FileProcessException("未检测到Excel，请检查Excel文件格式！");
            } else {
                for (int sheetNum = 0; sheetNum < ((Workbook) workbook).getNumberOfSheets(); ++sheetNum) {
                    if(sheetNum==0){//只取第一个sheet页数据
                        Sheet sheet = ((Workbook) workbook).getSheetAt(sheetNum);
                        if (sheet != null) {
                            Row fristRow = sheet.getRow(0);
                            if (fristRow == null) {
                                throw new ExcelImportRowNullException("第  1 行为标题行，不能为空!");
                            }

                            String[] titleCell = new String[fristRow.getLastCellNum()];

                            for (int cellNum = 0; cellNum < fristRow.getLastCellNum(); ++cellNum) {
                                Cell cell = fristRow.getCell(cellNum);
                                if (cell == null) {
                                    throw new ExcelImportRowNullException(
                                            "第  1 行为标题行，且每列必须有值。第 " + (cellNum + 1) + " 列不能为空");
                                }

                                titleCell[cellNum] = cell.getStringCellValue();
                            }

                            List<List<String>> dataRowmsg = new ArrayList();

                            for (int rowNum = 1; rowNum <= sheet.getLastRowNum(); ++rowNum) {
                                Row row = sheet.getRow(rowNum);
                                if (row == null) {
                                    throw new ExcelImportRowNullException("第 " + (rowNum + 1) + " 行不能为空");
                                }

                                List<String> dataCellmsg = new ArrayList();

                                for (int cellNum = 0; cellNum < fristRow.getLastCellNum(); ++cellNum) {
                                    Cell cell = row.getCell(cellNum);
                                    if (cell == null && titleCell[cellNum].endsWith("*")) {
                                        String msg = "第 " + (rowNum + 1) + " 行，第 " + (cellNum + 1) + " 列不能为空";
                                        dataCellmsg.add(msg);
                                    }
                                }

                                if (dataCellmsg.size() > 0) {
                                    dataRowmsg.add(dataCellmsg);
                                } else {
                                    rowList.add(row);
                                }
                            }

                            if (dataRowmsg.size() > 0) {
                                throw new ExcelImportCellNullException(dataRowmsg);
                            }
                        }
                    }
                }

                return rowList;
            }
        }
    }

    public static Sheet getFirstSheet(File file) throws Exception {
        Object workbook = null;
        Sheet sheet = null;
        FileInputStream fi = new FileInputStream(file);
        if (file.getName().toLowerCase().endsWith("xlsx")) {
            workbook = new XSSFWorkbook(fi);
        } else if (file.getName().toLowerCase().endsWith("xls")) {
            workbook = new HSSFWorkbook(fi);
        }
        if (workbook == null) {
        } else {
            sheet = ((Workbook) workbook).getSheetAt(0);
        }
        return sheet;
    }

    public static List<Row> getExcelRows(Sheet sheet, int index, int step) throws Exception {
        List<Row> all = new ArrayList<Row>();
        int startValue = index * step;
        int endVale = (index + 1) * step;
        if (index == sheet.getLastRowNum() / step - 1) {
            endVale = sheet.getLastRowNum() / step - 1;
        }
        for (int j = startValue; j < endVale; j++) {
            Row row = sheet.getRow(j);
            all.add(row);
        }
        return all;

    }
}
