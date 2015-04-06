package com.jettmarks.routes.client;

import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.web.bindery.event.shared.EventBus;
import com.googlecode.mgwt.mvp.client.MGWTAbstractActivity;

public class DetailActivity extends MGWTAbstractActivity {

	private final DetailView detailView;

	private final String eventId;

	public DetailActivity(DetailView detailView, String eventId) {
		this.detailView = detailView;
		this.eventId = eventId;
	}

	@Override
	public void start(AcceptsOneWidget panel, final EventBus eventBus) {
		// if (detailView.getMainButton() != null) {
		// addHandlerRegistration(detailView.getMainButton().addTapHandler(
		// new TapHandler() {
		//
		// @Override
		// public void onTap(TapEvent event) {
		// eventBus.fireEvent(new ShowMasterEvent(eventId));
		//
		// }
		// }));
		// }

		// Back Button
		// if (detailView.getBackbutton() != null) {
		// addHandlerRegistration(detailView.getBackbutton().addTapHandler(
		// new TapHandler() {
		//
		// @Override
		// public void onTap(TapEvent event) {
		// ActionEvent.fire(eventBus, ActionNames.BACK);
		//
		// }
		// }));
		// }

	}

}
