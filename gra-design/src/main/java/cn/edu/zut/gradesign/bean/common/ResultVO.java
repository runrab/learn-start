package cn.edu.zut.gradesign.bean.common;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 * @author o
 */
@Getter
@Setter
@Data
public class ResultVO {
    private int code;
    private Object data;
    private String msg;
}
