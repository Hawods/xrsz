package org.hawods.service.impl;

import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.hawods.Setting;
import org.hawods.TemplateConfig;
import org.hawods.service.StaticService;
import org.hawods.util.SystemUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.web.context.ServletContextAware;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import javax.annotation.Resource;
import javax.servlet.ServletContext;
import java.io.*;
import java.util.*;

/**
 * Created by hawods.pan on 2016/5/30 0030.
 */
@Service("staticServiceImpl")
public class StaticServiceImpl implements StaticService, ServletContextAware {

    private static final Integer SITEMAP_MAX_SIZE = 10000;

    private ServletContext servletContext;

    @Resource(name = "freeMarkerConfigurer")
    private FreeMarkerConfigurer freeMarkerConfigurer;

    public void setServletContext(ServletContext servletContext) {
        this.servletContext = servletContext;
    }

    @Transactional(readOnly = true)
    public int generate(String templatePath, String staticPath, Map<String, Object> model) {
        Assert.hasText(templatePath);
        Assert.hasText(staticPath);

        Writer writer = null;
        try {
            Template template = freeMarkerConfigurer.getConfiguration().getTemplate(templatePath);
            File staticFile = new File(servletContext.getRealPath(staticPath));
            File staticDir = staticFile.getParentFile();
            if (staticDir != null) {
                staticDir.mkdirs();
            }
            writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(staticFile), "UTF-8"));
            template.process(model, writer);
            writer.flush();
            return 1;
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage(), e);
        } catch (TemplateException e) {
            throw new RuntimeException(e.getMessage(), e);
        } finally {
            IOUtils.closeQuietly(writer);
        }
    }


    @Transactional(readOnly = true)
    public int generateIndex() {
        TemplateConfig templateConfig = SystemUtils.getTemplateConfig("index");
        return generate(templateConfig.getRealTemplatePath(), templateConfig.getRealStaticPath(), null);
    }

    @Transactional(readOnly = true)
    public int generateSitemap() {
        int generateCount = 0;
        TemplateConfig sitemapIndexTemplateConfig = SystemUtils.getTemplateConfig("sitemapIndex");
        TemplateConfig sitemapTemplateConfig = SystemUtils.getTemplateConfig("sitemap");
        List<SitemapUrl> sitemapUrls = new ArrayList<SitemapUrl>();

        Setting setting = SystemUtils.getSetting();
        SitemapUrl indexSitemapUrl = new SitemapUrl();
        indexSitemapUrl.setLoc(setting.getSiteUrl());
        indexSitemapUrl.setLastmod(new Date());
        indexSitemapUrl.setChangefreq(SitemapUrl.Changefreq.hourly);
        indexSitemapUrl.setPriority(1);
        sitemapUrls.add(indexSitemapUrl);

//        for (int i = 0; ; i += 100) {
//            List<Article> articles = articleDao.findList(i, 100, null, null);
//            if (CollectionUtils.isNotEmpty(articles)) {
//                for (Article article : articles) {
//                    SitemapUrl articleSitemapUrl = new SitemapUrl();
//                    articleSitemapUrl.setLoc(article.getUrl());
//                    articleSitemapUrl.setLastmod(article.getModifyDate());
//                    articleSitemapUrl.setChangefreq(SitemapUrl.Changefreq.daily);
//                    articleSitemapUrl.setPriority(0.6F);
//                    sitemapUrls.add(articleSitemapUrl);
//                }
//                articleDao.flush();
//                articleDao.clear();
//            }
//            if (articles.size() < 100) {
//                break;
//            }
//        }
//        for (int i = 0; ; i += 100) {
//            List<Goods> goodsList = goodsDao.findList(i, 100, null, null);
//            if (CollectionUtils.isNotEmpty(goodsList)) {
//                for (Goods goods : goodsList) {
//                    SitemapUrl goodsSitemapUrl = new SitemapUrl();
//                    goodsSitemapUrl.setLoc(goods.getUrl());
//                    goodsSitemapUrl.setLastmod(goods.getModifyDate());
//                    goodsSitemapUrl.setChangefreq(SitemapUrl.Changefreq.daily);
//                    goodsSitemapUrl.setPriority(0.8F);
//                    sitemapUrls.add(goodsSitemapUrl);
//                }
//                goodsDao.flush();
//                goodsDao.clear();
//            }
//            if (goodsList.size() < 100) {
//                break;
//            }
//        }

        List<String> sitemapPaths = new ArrayList<String>();
        for (int i = 0, index = 0; i < sitemapUrls.size(); i += SITEMAP_MAX_SIZE, index++) {
            Map<String, Object> model = new HashMap<String, Object>();
            model.put("index", index);
            model.put("sitemapUrls", sitemapUrls.subList(i, i + SITEMAP_MAX_SIZE <= sitemapUrls.size() ? i + SITEMAP_MAX_SIZE : sitemapUrls.size()));
            String sitemapPath = sitemapTemplateConfig.getRealStaticPath(model);
            sitemapPaths.add(sitemapPath);
            generateCount += generate(sitemapTemplateConfig.getRealTemplatePath(), sitemapPath, model);
        }

        Map<String, Object> model = new HashMap<String, Object>();
        model.put("sitemapPaths", sitemapPaths);
        generateCount += generate(sitemapIndexTemplateConfig.getRealTemplatePath(), sitemapIndexTemplateConfig.getRealStaticPath(), model);
        return generateCount;
    }

    @Transactional(readOnly = true)
    public int generateOther() {
        int generateCount = 0;
        TemplateConfig shopCommonJsTemplateConfig = SystemUtils.getTemplateConfig("shopCommonJs");
        TemplateConfig adminCommonJsTemplateConfig = SystemUtils.getTemplateConfig("adminCommonJs");
        generateCount += generate(shopCommonJsTemplateConfig.getRealTemplatePath(), shopCommonJsTemplateConfig.getRealStaticPath(), null);
        generateCount += generate(adminCommonJsTemplateConfig.getRealTemplatePath(), adminCommonJsTemplateConfig.getRealStaticPath(), null);
        return generateCount;
    }

    @Transactional
    public int generateAll() {
        int generateCount = 0;
        generateCount += generateIndex();
        generateCount += generateSitemap();
        generateCount += generateOther();
        return generateCount;
    }

    @Transactional(readOnly = true)
    public int delete(String staticPath) {
        if (StringUtils.isEmpty(staticPath)) {
            return 0;
        }
        File staticFile = new File(servletContext.getRealPath(staticPath));
        return FileUtils.deleteQuietly(staticFile) ? 1 : 0;
    }

    @Transactional(readOnly = true)
    public int deleteIndex() {
        TemplateConfig templateConfig = SystemUtils.getTemplateConfig("index");
        return delete(templateConfig.getRealStaticPath());
    }

    @Transactional(readOnly = true)
    public int deleteOther() {
        int deleteCount = 0;
        TemplateConfig shopCommonJsTemplateConfig = SystemUtils.getTemplateConfig("shopCommonJs");
        TemplateConfig adminCommonJsTemplateConfig = SystemUtils.getTemplateConfig("adminCommonJs");
        deleteCount += delete(shopCommonJsTemplateConfig.getRealStaticPath());
        deleteCount += delete(adminCommonJsTemplateConfig.getRealStaticPath());
        return deleteCount;
    }

    public static class SitemapUrl {

        public enum Changefreq {

            always,

            hourly,

            daily,

            weekly,

            monthly,

            yearly,

            never
        }

        private String loc;

        private Date lastmod;

        private Changefreq changefreq;

        private float priority;

        public String getLoc() {
            return loc;
        }

        public void setLoc(String loc) {
            this.loc = loc;
        }

        public Date getLastmod() {
            return lastmod;
        }

        public void setLastmod(Date lastmod) {
            this.lastmod = lastmod;
        }

        public Changefreq getChangefreq() {
            return changefreq;
        }

        public void setChangefreq(Changefreq changefreq) {
            this.changefreq = changefreq;
        }

        public float getPriority() {
            return priority;
        }

        public void setPriority(float priority) {
            this.priority = priority;
        }

    }

}