package org.hawods.mapper;

import org.hawods.entity.Seo;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by hawods on 5/29/16.
 */
@Repository
public interface SeoMapper {
    Seo selectSeo(int id);

    Seo selectByType(Seo.Type type);
}
