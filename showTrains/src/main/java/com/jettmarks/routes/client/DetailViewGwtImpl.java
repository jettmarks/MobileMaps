package com.jettmarks.routes.client;

import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.LayoutPanel;
import com.google.gwt.user.client.ui.Widget;
import com.googlecode.mgwt.dom.client.event.tap.HasTapHandlers;
import com.googlecode.mgwt.ui.client.MGWT;
import com.googlecode.mgwt.ui.client.widget.button.image.NextitemImageButton;
import com.googlecode.mgwt.ui.client.widget.button.image.PreviousitemImageButton;
import com.googlecode.mgwt.ui.client.widget.header.HeaderPanel;

public abstract class DetailViewGwtImpl implements DetailView {

	protected LayoutPanel main;
	// protected ScrollPanel scrollPanel;
	protected AbsolutePanel mapPanel;
	protected HeaderPanel headerPanel;
	protected PreviousitemImageButton headerBackButton;
	protected NextitemImageButton headerMainButton;
	// protected HeaderButton headerMainButton;
	protected HTML title;

	public DetailViewGwtImpl() {
		main = new LayoutPanel();
		mapPanel = new AbsolutePanel();

		// scrollPanel = new ScrollPanel();

		headerPanel = new HeaderPanel();
		title = new HTML();
		// headerPanel.setCenterWidget(title);
		headerPanel.add(title);
		headerBackButton = new PreviousitemImageButton();
		// headerBackButton = new HeaderButton();
		// headerBackButton.setBackButton(true);
		headerBackButton.setVisible(!MGWT.getOsDetection().isAndroid());

		// headerMainButton = new HeaderButton();
		headerMainButton = new NextitemImageButton();
		// headerMainButton.setRoundButton(true);

		if (!MGWT.getOsDetection().isPhone()) {
			// headerPanel.setLeftWidget(headerMainButton);
			headerPanel.add(headerMainButton);
			// headerMainButton.addStyleName(MGWTStyle.getTheme()
			// .getMGWTClientBundle().getUtilCss().portraitonly());
		} else {
			// headerPanel.setLeftWidget(headerBackButton);
			headerPanel.add(headerBackButton);
		}

		main.add(headerPanel);
		// main.add(scrollPanel);
		mapPanel.setSize("100%", "100%");
		main.add(mapPanel);
	}

	@Override
	public Widget asWidget() {
		return main;
	}

	@Override
	public HasText getHeader() {
		return title;
	}

	@Override
	public HasText getBackbuttonText() {
		return headerBackButton;
	}

	@Override
	public HasTapHandlers getBackbutton() {
		return headerBackButton;
	}

	@Override
	public HasText getMainButtonText() {
		return headerMainButton;
	}

	@Override
	public HasTapHandlers getMainButton() {
		return headerMainButton;
	}

}
