package com.jettmarks.bikeTrains.client;

import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.web.bindery.event.shared.EventBus;
import com.googlecode.mgwt.dom.client.event.tap.TapEvent;
import com.googlecode.mgwt.dom.client.event.tap.TapHandler;
import com.googlecode.mgwt.mvp.client.MGWTAbstractActivity;
import com.jettmarks.bikeTrains.client.event.ActionEvent;
import com.jettmarks.bikeTrains.client.event.ActionNames;

public class DetailActivity extends MGWTAbstractActivity {

	private final DetailView detailView;

	public DetailActivity(DetailView detailView, String eventId) {
		this.detailView = detailView;
	}

	@Override
	public void start(AcceptsOneWidget panel, final EventBus eventBus) {

		addHandlerRegistration(detailView.getBackbutton().addTapHandler(
				new TapHandler() {

					@Override
					public void onTap(TapEvent event) {
						ActionEvent.fire(eventBus, ActionNames.BACK);
					}
				}));

	}

}
