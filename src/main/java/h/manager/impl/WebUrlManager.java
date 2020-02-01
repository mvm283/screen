package h.manager.impl;

import h.dao.IWebUrlDao;
import h.manager.IWebUrlManager;
import h.model.WebUrlModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

@Service("webUrlManagerService")
@Scope("prototype")
public class WebUrlManager  implements IWebUrlManager {

    @Autowired
    private IWebUrlDao iWebUrlDao;

    @Override
    public WebUrlModel addWebUrl(WebUrlModel webUrlModel) {
        return iWebUrlDao.create(webUrlModel);

    }

    @Override
    public WebUrlModel getbUrl(String url) {
        return iWebUrlDao.getByUrl(url);
    }
}
