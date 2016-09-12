package com.mdxx.qqbh.DataBean;

/**
 * Created by DDstar on 2016/9/5.
 */
public class MainTabMenuBean {
    int menuPhotoRes;
    String workName;
    String workMsg;
    int type;
    int isEnable;


    public MainTabMenuBean(int menuPhotoRes, String workName, String workMsg, int type) {
        this.menuPhotoRes = menuPhotoRes;
        this.workName = workName;
        this.workMsg = workMsg;
        this.type = type;
    }

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

    public boolean isEnable() {
        if (isEnable == 0){
            return  true;
        }else {
            return  false;
        }
    }

    public void setEnable(int enable) {
        isEnable = enable;
    }

    public void setType(int type) {
        this.type = type;
    }
}
