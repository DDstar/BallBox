package org.ddstar.ballbox.DataBean;

/**
 * Created by DDstar on 2016/9/8.
 */
public class MainBean {

    /**
     * code : 1
     * msg : 查询成功
     * fflist : {"url":"http://www.baidu.com","img":"static/items/m_57cfa6366ba37.jpg","isshow":"1"}
     */

    private int code;
    private String msg;
    /**
     * url : http://www.baidu.com
     * img : static/items/m_57cfa6366ba37.jpg
     * isshow : 1
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
        private String url;
        private String img;
        private String isshow;

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getImg() {
            return img;
        }

        public void setImg(String img) {
            this.img = img;
        }

        public String getIsshow() {
            return isshow;
        }

        public void setIsshow(String isshow) {
            this.isshow = isshow;
        }
    }
}
