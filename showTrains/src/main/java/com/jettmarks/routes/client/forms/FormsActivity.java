/*
 * Copyright 2012 Daniel Kurka
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package com.jettmarks.routes.client.forms;

import com.google.gwt.event.shared.EventBus;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.place.shared.Place;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.googlecode.mgwt.dom.client.event.tap.TapEvent;
import com.googlecode.mgwt.dom.client.event.tap.TapHandler;
import com.jettmarks.routes.client.ClientFactory;
import com.jettmarks.routes.client.DetailActivity;
import com.jettmarks.routes.client.place.HomePlace;
import com.jettmarks.routes.client.service.GetTagsAsync;

public class FormsActivity extends DetailActivity {

	private final ClientFactory clientFactory;
	HandlerRegistration handlerRegistration = null;

	public FormsActivity(Place newPlace, ClientFactory clientFactory) {
		super(clientFactory.getFormsView(), "nav");
		if (newPlace instanceof FormsPlace) {
		  String displayGroupName = ((FormsPlace)newPlace).getDisplayGroupName();
		  clientFactory.getFormsView().setDisplayGroupName(displayGroupName);
		}
		this.clientFactory = clientFactory;
	}

	@Override
  	public void start(AcceptsOneWidget panel, EventBus eventBus) {
  		super.start(panel, eventBus);
  		final FormsView view = clientFactory.getFormsView();
  		view.getMainButtonText().setText("Nav");
  		view.getBackbuttonText().setText("UI");
  		view.getHeader().setText("Forms");
  
  		handlerRegistration = view.getSaveButton().addTapHandler(new TapHandler(){
  
        @Override
        public void onTap(TapEvent event)
        {
          String desc = view.getDescription();
          String name = view.getDisplayGroupName();
          GetTagsAsync tagService = GetTagsAsync.Util.getInstance();
          tagService.saveDisplayGroup(desc, name, new SaveDisplayGroupCallback<Integer>());
          
          if (handlerRegistration != null) {
            handlerRegistration.removeHandler();
          }
          
        }});
  		
  		panel.setWidget(view);
  	}

  /**
   * Callback response for saving a Display Group.
   * 
   * After saving, we go to the Home Place.
   *
   * @author jett
   */
  public class SaveDisplayGroupCallback<T> implements AsyncCallback<Integer>
  {
  
    /* (non-Javadoc)
     * @see com.google.gwt.user.client.rpc.AsyncCallback#onFailure(java.lang.Throwable)
     */
    @Override
    public void onFailure(Throwable caught)
    {
      // Not sure what to do here
    }
  
    /* (non-Javadoc)
     * @see com.google.gwt.user.client.rpc.AsyncCallback#onSuccess(java.lang.Object)
     */
    @Override
    public void onSuccess(Integer result)
    {
      Window.alert("Saved Display Group as ID: "+result);
      clientFactory.getPlaceController().goTo(new HomePlace());
    }
  
  }

}
