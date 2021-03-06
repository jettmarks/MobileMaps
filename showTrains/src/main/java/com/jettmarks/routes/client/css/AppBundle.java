package com.jettmarks.routes.client.css;

import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.resources.client.TextResource;
import com.jettmarks.routes.client.ui.HeaderButtonBarCss;

public interface AppBundle extends ClientBundle {
    // This is a very nasty workaround because GWT CssResource does not support
    // @media correctly!
    @Source("app.css")
    TextResource css();

    public static final AppBundle INSTANCE = GWT.create(AppBundle.class);

    @Source("list.png")
    ImageResource tabBarListImage();

    @Source("map.png")
    ImageResource tabBarMapImage();

    @Source("headerButtonBar.css")
    HeaderButtonBarCss headerButtonBarCss();

    /**
     * @return
     */
    @Source("home.png")
    ImageResource getButtonBarHomeImage();
}
