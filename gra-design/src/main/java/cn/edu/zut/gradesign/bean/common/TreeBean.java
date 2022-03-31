package cn.edu.zut.gradesign.bean.common;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;

/**
 * @author o
 */
@Getter
@Setter
@Data
public class TreeBean {
    private String label;
    private Integer  id;
    private Map<String, Object> children;
}
