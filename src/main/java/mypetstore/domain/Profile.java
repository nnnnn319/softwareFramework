package mypetstore.domain;

import java.io.Serializable;

public class Profile implements Serializable {
    private static final long serialVersionUID = 3992469837058393712L;

    private String userId;
    private String langPref;
    private String favCategory;
    private int myListOpt;
    private int bannerOpt;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getLangPref() {
        return langPref;
    }

    public void setLangPref(String langPref) {
        this.langPref = langPref;
    }

    public String getFavCategory() {
        return favCategory;
    }

    public void setFavCategory(String favCategory) {
        this.favCategory = favCategory;
    }

    public int getMyListOpt() {
        return myListOpt;
    }

    public void setMyListOpt(int myListOpt) {
        this.myListOpt = myListOpt;
    }

    public int getBannerOpt() {
        return bannerOpt;
    }

    public void setBannerOpt(int bannerOpt) {
        this.bannerOpt = bannerOpt;
    }
}
