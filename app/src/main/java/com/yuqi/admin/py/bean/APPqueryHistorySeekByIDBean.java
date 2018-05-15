package com.yuqi.admin.py.bean;

import java.util.List;

/**
 * Created by Administrator on 2018/1/11.
 * 历史搜索记录
 *
 */
public class APPqueryHistorySeekByIDBean {


    /**
     * object : {"seekValues":["净化器"],"HotSearch":["晨光","大闸蟹","净化器","眼镜","花"]}
     * state : 200
     */

    private ObjectBean object;
    private String state;

    public ObjectBean getObject() {
        return object;
    }

    public void setObject(ObjectBean object) {
        this.object = object;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public static class ObjectBean {
        private List<String> seekValues;
        private List<String> HotSearch;

        public List<String> getSeekValues() {
            return seekValues;
        }

        public void setSeekValues(List<String> seekValues) {
            this.seekValues = seekValues;
        }

        public List<String> getHotSearch() {
            return HotSearch;
        }

        public void setHotSearch(List<String> hotSearch) {
            HotSearch = hotSearch;
        }
    }
}
