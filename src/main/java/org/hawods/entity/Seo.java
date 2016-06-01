package org.hawods.entity;

import org.hawods.entity.base.BaseEntity;

/**
 * Created by hawods.pan on 2016/5/27 0025.
 */
public class Seo extends BaseEntity<Long> {

	public enum Type {

		index,

		articleList,

		articleSearch,

		articleContent,

		goodsList,

		goodsSearch,

		goodsContent,

		brandList,

		brandContent
	}

	private Seo.Type type;

	private String title;

	private String keywords;

	private String description;

	public Seo.Type getType() {
		return type;
	}

	public void setType(Seo.Type type) {
		this.type = type;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getKeywords() {
		return keywords;
	}

	public void setKeywords(String keywords) {
		if (keywords != null) {
			keywords = keywords.replaceAll("[,\\s]*,[,\\s]*", ",").replaceAll("^,|,$", "");
		}
		this.keywords = keywords;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
