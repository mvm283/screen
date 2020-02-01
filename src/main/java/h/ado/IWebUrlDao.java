package h.ado;

import h.model.WebUrlModel;

import java.io.Serializable;

public interface IWebUrlDao extends IAbstractGenericDAO<WebUrlModel, Serializable> {

    WebUrlModel getByUrl(String url);
}
