package com.mdxx.qqbh.DataBean;

import java.util.List;

/**
 * Created by DDstar on 2016/9/18.
 */
public class BinboBean {

    /**
     * code : 1
     * msg : 查询成功
     * fflist : ["用户ID1000****,获得16积分","用户ID1000****,获得15积分","用户ID1000****,获得46积分","用户ID1000****,获得24积分","用户ID1000****,获得27积分","用户ID1000****,获得40积分","用户ID1000****,获得44积分","用户ID1000****,获得48积分","用户ID1000****,获得44积分","用户ID1000****,获得42积分","用户ID1000****,获得32积分"]
     */

    private int code;
    private String msg;
    private List<String> fflist;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<String> getFflist() {
        return fflist;
    }

    public void setFflist(List<String> fflist) {
        this.fflist = fflist;
    }
}
