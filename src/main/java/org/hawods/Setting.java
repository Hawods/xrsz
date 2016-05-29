package org.hawods;

import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;

/**
 * Created by hawods.pan on 2016/5/26 0026.
 */
public class Setting implements Serializable {
    /**
     * 缓存名称
     */
    public static final String CACHE_NAME = "setting";

    public enum Locale {

        zh_CN,

        zh_TW,

        en_US
    }

    private String siteName;

    private String siteUrl;

    private String logo;

    private String address;

    private String phone;

    private String email;

    private String certtext;

    private Boolean isSiteEnabled;

    private String siteCloseMessage;

    private Integer largeProductImageWidth;

    private Integer largeProductImageHeight;

    private Integer mediumProductImageWidth;

    private Integer mediumProductImageHeight;

    private Integer thumbnailProductImageWidth;

    private Integer thumbnailProductImageHeight;

    private String defaultLargeProductImage;

    private String defaultMediumProductImage;

    private String defaultThumbnailProductImage;

    private Integer watermarkAlpha;

    private String watermarkImage;

    private Integer uploadMaxSize;

    private String uploadImageExtension;

    private String uploadMediaExtension;

    private String uploadFileExtension;

    private String imageUploadPath;

    private String mediaUploadPath;

    private String fileUploadPath;

    private Boolean isDevelopmentEnabled;

    private String cookiePath;

    private String cookieDomain;

    private Setting.Locale locale;

    private String theme;

    public String getSiteName() {
        return siteName;
    }

    public void setSiteName(String siteName) {
        this.siteName = siteName;
    }

    public String getSiteUrl() {
        return siteUrl;
    }

    public void setSiteUrl(String siteUrl) {
        this.siteUrl = StringUtils.removeEnd(siteUrl, "/");
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCerttext() {
        return certtext;
    }

    public void setCerttext(String certtext) {
        this.certtext = certtext;
    }

    public Boolean getIsSiteEnabled() {
        return isSiteEnabled;
    }

    public void setIsSiteEnabled(Boolean isSiteEnabled) {
        this.isSiteEnabled = isSiteEnabled;
    }

    public String getSiteCloseMessage() {
        return siteCloseMessage;
    }

    public void setSiteCloseMessage(String siteCloseMessage) {
        this.siteCloseMessage = siteCloseMessage;
    }

    public Integer getLargeProductImageWidth() {
        return largeProductImageWidth;
    }

    public void setLargeProductImageWidth(Integer largeProductImageWidth) {
        this.largeProductImageWidth = largeProductImageWidth;
    }

    public Integer getLargeProductImageHeight() {
        return largeProductImageHeight;
    }

    public void setLargeProductImageHeight(Integer largeProductImageHeight) {
        this.largeProductImageHeight = largeProductImageHeight;
    }

    public Integer getMediumProductImageWidth() {
        return mediumProductImageWidth;
    }

    public void setMediumProductImageWidth(Integer mediumProductImageWidth) {
        this.mediumProductImageWidth = mediumProductImageWidth;
    }

    public Integer getMediumProductImageHeight() {
        return mediumProductImageHeight;
    }

    public void setMediumProductImageHeight(Integer mediumProductImageHeight) {
        this.mediumProductImageHeight = mediumProductImageHeight;
    }

    public Integer getThumbnailProductImageWidth() {
        return thumbnailProductImageWidth;
    }

    public void setThumbnailProductImageWidth(Integer thumbnailProductImageWidth) {
        this.thumbnailProductImageWidth = thumbnailProductImageWidth;
    }

    public Integer getThumbnailProductImageHeight() {
        return thumbnailProductImageHeight;
    }

    public void setThumbnailProductImageHeight(Integer thumbnailProductImageHeight) {
        this.thumbnailProductImageHeight = thumbnailProductImageHeight;
    }

    public String getDefaultLargeProductImage() {
        return defaultLargeProductImage;
    }

    public void setDefaultLargeProductImage(String defaultLargeProductImage) {
        this.defaultLargeProductImage = defaultLargeProductImage;
    }

    public String getDefaultMediumProductImage() {
        return defaultMediumProductImage;
    }

    public void setDefaultMediumProductImage(String defaultMediumProductImage) {
        this.defaultMediumProductImage = defaultMediumProductImage;
    }

    public String getDefaultThumbnailProductImage() {
        return defaultThumbnailProductImage;
    }

    public void setDefaultThumbnailProductImage(String defaultThumbnailProductImage) {
        this.defaultThumbnailProductImage = defaultThumbnailProductImage;
    }

    public Integer getWatermarkAlpha() {
        return watermarkAlpha;
    }

    public void setWatermarkAlpha(Integer watermarkAlpha) {
        this.watermarkAlpha = watermarkAlpha;
    }

    public String getWatermarkImage() {
        return watermarkImage;
    }

    public void setWatermarkImage(String watermarkImage) {
        this.watermarkImage = watermarkImage;
    }

    public Integer getUploadMaxSize() {
        return uploadMaxSize;
    }

    public void setUploadMaxSize(Integer uploadMaxSize) {
        this.uploadMaxSize = uploadMaxSize;
    }

    public String getUploadImageExtension() {
        return uploadImageExtension;
    }

    public void setUploadImageExtension(String uploadImageExtension) {
        if (uploadImageExtension != null) {
            uploadImageExtension = uploadImageExtension.replaceAll("[,\\s]*,[,\\s]*", ",").replaceAll("^,|,$", "").toLowerCase();
        }
        this.uploadImageExtension = uploadImageExtension;
    }

    public String getUploadMediaExtension() {
        return uploadMediaExtension;
    }

    public void setUploadMediaExtension(String uploadMediaExtension) {
        if (uploadMediaExtension != null) {
            uploadMediaExtension = uploadMediaExtension.replaceAll("[,\\s]*,[,\\s]*", ",").replaceAll("^,|,$", "").toLowerCase();
        }
        this.uploadMediaExtension = uploadMediaExtension;
    }

    public String getUploadFileExtension() {
        return uploadFileExtension;
    }

    public void setUploadFileExtension(String uploadFileExtension) {
        if (uploadFileExtension != null) {
            uploadFileExtension = uploadFileExtension.replaceAll("[,\\s]*,[,\\s]*", ",").replaceAll("^,|,$", "").toLowerCase();
        }
        this.uploadFileExtension = uploadFileExtension;
    }

    public String getImageUploadPath() {
        return imageUploadPath;
    }

    public void setImageUploadPath(String imageUploadPath) {
        if (imageUploadPath != null) {
            if (!imageUploadPath.startsWith("/")) {
                imageUploadPath = "/" + imageUploadPath;
            }
            if (!imageUploadPath.endsWith("/")) {
                imageUploadPath += "/";
            }
        }
        this.imageUploadPath = imageUploadPath;
    }

    public String getMediaUploadPath() {
        return mediaUploadPath;
    }

    public void setMediaUploadPath(String mediaUploadPath) {
        if (mediaUploadPath != null) {
            if (!mediaUploadPath.startsWith("/")) {
                mediaUploadPath = "/" + mediaUploadPath;
            }
            if (!mediaUploadPath.endsWith("/")) {
                mediaUploadPath += "/";
            }
        }
        this.mediaUploadPath = mediaUploadPath;
    }

    public String getFileUploadPath() {
        return fileUploadPath;
    }

    public void setFileUploadPath(String fileUploadPath) {
        if (fileUploadPath != null) {
            if (!fileUploadPath.startsWith("/")) {
                fileUploadPath = "/" + fileUploadPath;
            }
            if (!fileUploadPath.endsWith("/")) {
                fileUploadPath += "/";
            }
        }
        this.fileUploadPath = fileUploadPath;
    }

    public Boolean getIsDevelopmentEnabled() {
        return isDevelopmentEnabled;
    }

    public void setIsDevelopmentEnabled(Boolean isDevelopmentEnabled) {
        this.isDevelopmentEnabled = isDevelopmentEnabled;
    }

    public String getCookiePath() {
        return cookiePath;
    }

    public void setCookiePath(String cookiePath) {
        if (cookiePath != null && !cookiePath.endsWith("/")) {
            cookiePath += "/";
        }
        this.cookiePath = cookiePath;
    }

    public String getCookieDomain() {
        return cookieDomain;
    }

    public void setCookieDomain(String cookieDomain) {
        this.cookieDomain = cookieDomain;
    }

    public Setting.Locale getLocale() {
        return locale;
    }

    public void setLocale(Setting.Locale locale) {
        this.locale = locale;
    }

    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }
}
