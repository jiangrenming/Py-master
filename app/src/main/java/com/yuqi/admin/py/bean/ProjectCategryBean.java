package com.yuqi.admin.py.bean;

import java.io.Serializable;
import java.util.List;

/**
 *
 * @author jiangrenming
 * @date 2018/5/15
 */

public class ProjectCategryBean implements Serializable{

    private int status;
    private List<ProjectItem> data;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public List<ProjectItem> getData() {
        return data;
    }

    public void setData(List<ProjectItem> data) {
        this.data = data;
    }
}
