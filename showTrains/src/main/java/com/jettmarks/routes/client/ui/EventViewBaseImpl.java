package com.jettmarks.routes.client.ui;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.user.client.ui.HTML;
import com.googlecode.mgwt.dom.client.event.tap.HasTapHandlers;
import com.googlecode.mgwt.ui.client.widget.ScrollPanel;
import com.jettmarks.routes.client.MapDetailViewGwtImpl;
import com.jettmarks.routes.client.bean.Route;

public abstract class EventViewBaseImpl extends MapDetailViewGwtImpl implements EventView {

    protected List<Route> routes = new ArrayList<Route>();
    protected String description;
    protected String displayGroupName;
    protected ScrollPanel scrollPanel;
    protected HeaderButtonBar headerButtonBar;

    public EventViewBaseImpl() {
	super();
    }

    /**
    * 
    */
    protected void setupHeader() {
        headerButtonBar = new HeaderButtonBar();
        title = new HTML();
        headerButtonBar.setTitle(title);
    }

    /**
     * @return the dispGroupName
     */
    public String getDisplayGroupName() {
        return displayGroupName;
    }

    /**
     * @param dispGroupName
     *            the dispGroupName to set
     */
    public void setDisplayGroupName(String dispGroupName) {
        this.displayGroupName = dispGroupName;
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description
     *            the description to set
     */
    public void setDescription(String description) {
        this.description = description;
        title.setText(description);
    }

    @Override
    public HasTapHandlers getBackbutton() {
        return headerButtonBar.getLeftButton();
    }

    /**
     * @see com.jettmarks.routes.client.ui.EventView#getForwardbutton()
     */
    @Override
    public HasTapHandlers getForwardbutton() {
        return headerButtonBar.getRightButton();
    }

    public HTML getHeaderTapHandlers() {
        return title;
    }

    /**
     * @see com.jettmarks.routes.client.ui.EventView#enableBackButton(boolean)
     */
    @Override
    public void enableBackButton(boolean isEnabled) {
        headerButtonBar.setLeftButtonEnabled(isEnabled);
    }

    @Override
    public void enableForwardButton(boolean isEnabled) {
        headerButtonBar.setRightButtonEnabled(isEnabled);
    }

    /**
     * @see com.jettmarks.routes.client.ui.EventView#getHomeButton()
     */
    @Override
    public HasTapHandlers getHomeButton() {
        return headerButtonBar.getHomeButton();
    }

}