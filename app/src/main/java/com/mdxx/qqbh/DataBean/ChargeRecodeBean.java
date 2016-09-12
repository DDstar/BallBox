package com.mdxx.qqbh.DataBean;

import java.util.List;

/**
 * Created by Administrator on 2016/9/11 0011.
 */
public class ChargeRecodeBean {

    /**
     * code : 1
     * msg : 查询成功
     * fflist : [{"reason":"摇一摇抽奖","money":"15","status":"已兑换","time":"1473300643"}]
     */

    private String code;
    private String msg;
    /**
     * reason : 摇一摇抽奖
     * money : 15
     * status : 已兑换
     * time : 1473300643
     */

    private List<FflistBean> fflist;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<FflistBean> getFflist() {
        return fflist;
    }

    public void setFflist(List<FflistBean> fflist) {
        this.fflist = fflist;
    }

    public static class FflistBean {
        private String reason;
        private String money;
        private String status;
        private long time;

        public String getReason() {
            return reason;
        }

        public void setReason(String reason) {
            this.reason = reason;
        }

        public String getMoney() {
            return money;
        }

        public void setMoney(String money) {
            this.money = money;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public long getTime() {
            return time;
        }

        public void setTime(long time) {
            this.time = time;
        }
    }
}
