package org.ddstar.ballbox.DataBean;

/**
 * Created by DDstar on 2016/9/5.
 */
public class MainTabMenuBean {
    int menuPhotoRes;
    String workName;
    String workMsg;
    int type;

    public int getMenuPhotoRes() {
        return menuPhotoRes;
    }

    public void setMenuPhotoRes(int menuPhotoRes) {
        this.menuPhotoRes = menuPhotoRes;
    }

    public String getWorkName() {
        return workName;
    }

    public void setWorkName(String workName) {
        this.workName = workName;
    }

    public String getWorkMsg() {
        return workMsg;
    }

    public void setWorkMsg(String workMsg) {
        this.workMsg = workMsg;
    }

    public String getType() {
        switch (type) {
            case 1:
                return "未完成";
            case 2:
                return "赚取积分";
        }
        return "";
    }

    public void setType(int type) {
        this.type = type;
    }
}
