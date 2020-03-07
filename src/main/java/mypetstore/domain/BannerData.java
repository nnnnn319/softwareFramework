package mypetstore.domain;

import java.io.Serializable;

public class BannerData implements Serializable {
    private static final long serialVersionUID = 3992469837058393712L;

    private String bannerName;
    private String favCategory;

    public String getBannerName() {
        return bannerName;
    }

    public void setBannerName(String bannerName) {
        this.bannerName = bannerName;
    }

    public String getFavCategory() {
        return favCategory;
    }

    public void setFavCategory(String favCategory) {
        this.favCategory = favCategory;
    }
}
