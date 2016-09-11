package org.ddstar.ballbox.DataBean;

/**
 * Created by Administrator on 2016/9/10 0010.
 */
public class MainUserMsgBean {

    /**
     * code : 1
     * msg : 查询成功
     * fflist : {"xslb":1,"isqd":0,"isqq":0,"qqshare_num":0,"money":"5","userid":"10003"}
     */

    private int code;
    private String msg;
    /**
     * xslb : 1
     * isqd : 0
     * isqq : 0
     * qqshare_num : 0
     * money : 5
     * userid : 10003
     */

    private FflistBean fflist;

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

    public FflistBean getFflist() {
        return fflist;
    }

    public void setFflist(FflistBean fflist) {
        this.fflist = fflist;
    }

    public static class FflistBean {
        private int xslb;
        private int isqd;
        private int isqq;
        private int qqshare_num;
        private String money;
        private String userid;

        public int getXslb() {
            return xslb;
        }

        public void setXslb(int xslb) {
            this.xslb = xslb;
        }

        public int getIsqd() {
            return isqd;
        }

        public void setIsqd(int isqd) {
            this.isqd = isqd;
        }

        public int getIsqq() {
            return isqq;
        }

        public void setIsqq(int isqq) {
            this.isqq = isqq;
        }

        public int getQqshare_num() {
            return qqshare_num;
        }

        public void setQqshare_num(int qqshare_num) {
            this.qqshare_num = qqshare_num;
        }

        public String getMoney() {
            return money;
        }

        public void setMoney(String money) {
            this.money = money;
        }

        public String getUserid() {
            return userid;
        }

        public void setUserid(String userid) {
            this.userid = userid;
        }
    }
}
