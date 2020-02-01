package h.dao.Impl;

import h.dao.IWebUrlDao;
import h.model.WebUrlModel;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.List;

@Repository
@Transactional
public class WebUrlDao extends AbstractGenericDAO<WebUrlModel, Serializable> implements IWebUrlDao {
    public WebUrlDao() {
        setClazz(WebUrlModel.class);
    }

    @Override
    public WebUrlModel getByUrl(String url) {
        DetachedCriteria criteria = DetachedCriteria.forClass(WebUrlModel.class);
        criteria.add(Restrictions.eq("url", url));
        List<WebUrlModel> list = criteria.getExecutableCriteria(getCurrentSession())
                .list();
        if (list != null && !list.isEmpty()) {
            return list.get(0);
        } else {
            return null;
        }
    }
}
