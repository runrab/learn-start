package cn.edu.zut.gradesign.bean.common;
import com.github.pagehelper.Page;
import java.util.List;
public class Pager<T> {
    private int size; //分页的大小
    private int offset; //分页的起始页
    private long total;
    private List<T> data;
    public int getSize() {
        return size;
    }
    public void setSize(int size) {
        this.size = size;
    }
    public int getOffset() {
        return offset;
    }
    public void setOffset(int offset) {
        this.offset = offset;
    }
    public long getTotal() {
        return total;
    }
    public void setTotal(long total) {
        this.total = total;
    }
    public List<T> getData() {
        return data;
    }
    public void setDatas(List<T> data) {
        this.data = data;
    }
    public Pager(List<T> data) {
        if(data instanceof Page){
            Page<T> page = (Page<T>)data;
            setOffset(page.getPageNum());
            setSize(page.getPageSize());
            setTotal(page.getTotal());
            setDatas(data);
        }
    }
    public Pager() {
    }
}
