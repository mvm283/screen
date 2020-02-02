package database.manager;

import database.model.WebUrlModel;

public interface IWebUrlManager {
    WebUrlModel addWebUrl(WebUrlModel webUrlModel);

    WebUrlModel getbUrl(String url);
}
