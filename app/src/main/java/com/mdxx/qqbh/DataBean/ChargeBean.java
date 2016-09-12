package com.mdxx.qqbh.DataBean;

/**
 * Created by Administrator on 2016/9/11 0011.
 */
public class ChargeBean {

    /**
     * code : 1
     * msg : 查找成功
     * fflist : http://www.duiba.com.cn/autoLogin/autologin?uid=10003&credits=6&appKey=3wJui3CPHyfpz5F5FH5ACQwPkQAp&sign=c5f8c5ce3dae617f2140ca6641bb98f6&timestamp=1473589054926
     */

    private int code;
    private String msg;
    private String fflist;

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

    public String getFflist() {
        return fflist;
    }

    public void setFflist(String fflist) {
        this.fflist = fflist;
    }
}
