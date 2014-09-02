package com.jettmarks.routes.client.service;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.ServiceDefTarget;
import com.jettmarks.routes.client.bean.DisplayGroupDTO;

public interface GetTagsAsync {

    /**
     * GWT-RPC service asynchronous (client-side) interface
     * 
     * @see com.jettmarks.routes.client.service.GetTags
     */
    void getTagList(AsyncCallback<java.lang.String[]> callback);

    /**
     * GWT-RPC service asynchronous (client-side) interface
     * 
     * @see com.jettmarks.routes.client.service.GetTags
     */
    void getTagList(java.lang.String token,
	    AsyncCallback<java.lang.String[]> callback);

    /**
     * GWT-RPC service asynchronous (client-side) interface
     * 
     * @see com.jettmarks.routes.client.service.GetTags
     */
    void getDisplayGroupList(AsyncCallback<String[]> callback);

    /**
     * GWT-RPC service asynchronous (client-side) interface
     * 
     * @see com.jettmarks.routes.client.service.GetTags
     */
    void getLatestDisplayGroupID(AsyncCallback<Integer> callback);

    /**
     * Utility class to get the RPC Async interface from client-side code
     */
    public static class Util {
	private static GetTagsAsync instance;

	public static GetTagsAsync getInstance() {
	    if (instance == null) {
		instance = (GetTagsAsync) GWT.create(GetTags.class);
		ServiceDefTarget target = (ServiceDefTarget) instance;
		// target.setServiceEntryPoint( GWT.getModuleBaseURL() +
		// "../getTags" );
		target.setServiceEntryPoint("/getTags");
	    }
	    return instance;
	}
    }

    void getDisplayGroups(AsyncCallback<DisplayGroupDTO[]> callback);

    void getActiveDisplayGroups(AsyncCallback<DisplayGroupDTO[]> callback);

    void saveDisplayGroup(String description, String displayGroupName,
	    AsyncCallback<Integer> callback);

    void getBikeTrains(AsyncCallback<DisplayGroupDTO[]> callback);

    void getBikeTrain(String displayGroupName,
	    AsyncCallback<DisplayGroupDTO> callback);

}
