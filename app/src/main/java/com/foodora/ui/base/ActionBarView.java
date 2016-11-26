package com.foodora.ui.base;

/**
 * Created by AhmedEltaher on 25/11/2016
 */

public interface ActionBarView {

    void openSettingsView();

    void openHomeView();

    void setUpIconVisibility(boolean visible);

    void setTitle(String titleKey);

    void setSettingsIconVisibility(boolean visibility);

    void setHomeIconVisibility(boolean visibility);
}
