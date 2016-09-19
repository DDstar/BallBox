package com.mdxx.qqbh.DataBean;

/**
 * Created by DDstar on 2016/9/19.
 */
public class ShareDetailBean {

    /**
     * code : 1
     * msg : 获取成功
     * fflist : {"sharetitle":"分享的标题","shareword":"分享的内容","shareurl":"http://www.taoqiuqiu.com"}
     */

    private int code;
    private String msg;
    /**
     * sharetitle : 分享的标题
     * shareword : 分享的内容
     * shareurl : http://www.taoqiuqiu.com
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
        private String sharetitle;
        private String shareword;
        private String shareurl;

        public String getSharetitle() {
            return sharetitle;
        }

        public void setSharetitle(String sharetitle) {
            this.sharetitle = sharetitle;
        }

        public String getShareword() {
            return shareword;
        }

        public void setShareword(String shareword) {
            this.shareword = shareword;
        }

        public String getShareurl() {
            return shareurl;
        }

        public void setShareurl(String shareurl) {
            this.shareurl = shareurl;
        }
    }
}
