package h.manager;

import h.model.WebUrlModel;

public interface IWebUrlManager {
    WebUrlModel addWebUrl(WebUrlModel webUrlModel);

    WebUrlModel getbUrl(String url);
}
