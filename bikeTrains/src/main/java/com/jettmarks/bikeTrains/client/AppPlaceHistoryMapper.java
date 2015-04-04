/*
 * Copyright 2010 Daniel Kurka
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
package com.jettmarks.bikeTrains.client;

import com.google.gwt.place.shared.PlaceHistoryMapper;
import com.google.gwt.place.shared.WithTokenizers;
import com.jettmarks.bikeTrains.client.activities.AboutPlace.AboutPlaceTokenizer;
import com.jettmarks.bikeTrains.client.activities.UIPlace.UIPlaceTokenizer;
import com.jettmarks.bikeTrains.client.activities.animation.AnimationPlace.AnimationPlaceTokenizer;
import com.jettmarks.bikeTrains.client.activities.animationdone.AnimationCubePlace;
import com.jettmarks.bikeTrains.client.activities.animationdone.AnimationDissolvePlace.AnimationDissolvePlaceTokenizer;
import com.jettmarks.bikeTrains.client.activities.animationdone.AnimationFadePlace.AnimationFadePlaceTokenizer;
import com.jettmarks.bikeTrains.client.activities.animationdone.AnimationFlipPlace.AnimationFlipPlaceTokenizer;
import com.jettmarks.bikeTrains.client.activities.animationdone.AnimationPopPlace.AnimationPopPlaceTokenizer;
import com.jettmarks.bikeTrains.client.activities.animationdone.AnimationSlidePlace.AnimationSlidePlaceTokenizer;
import com.jettmarks.bikeTrains.client.activities.animationdone.AnimationSlideUpPlace.AnimationSlideUpPlaceTokenizer;
import com.jettmarks.bikeTrains.client.activities.animationdone.AnimationSwapPlace.AnimationSwapPlaceTokenizer;
import com.jettmarks.bikeTrains.client.activities.button.ButtonPlace.ButtonPlaceTokenizer;
import com.jettmarks.bikeTrains.client.activities.buttonbar.ButtonBarPlace.ButtonBarPlaceTokenizer;
import com.jettmarks.bikeTrains.client.activities.carousel.CarouselPlace;
import com.jettmarks.bikeTrains.client.activities.elements.ElementsPlace.ElementsPlaceTokenizer;
import com.jettmarks.bikeTrains.client.activities.forms.FormsPlace;
import com.jettmarks.bikeTrains.client.activities.gcell.GroupedCellListPlace;
import com.jettmarks.bikeTrains.client.activities.popup.PopupPlace.PopupPlaceTokenizer;
import com.jettmarks.bikeTrains.client.activities.progressbar.ProgressBarPlace.ProgressBarPlaceTokenizer;
import com.jettmarks.bikeTrains.client.activities.progressindicator.ProgressIndicatorPlace;
import com.jettmarks.bikeTrains.client.activities.pulltorefresh.PullToRefreshPlace;
import com.jettmarks.bikeTrains.client.activities.scrollwidget.ScrollWidgetPlace.ScrollWidgetPlaceTokenizer;
import com.jettmarks.bikeTrains.client.activities.searchbox.SearchBoxPlace.SearchBoxPlaceTokenizer;
import com.jettmarks.bikeTrains.client.activities.slider.SliderPlace.SliderPlaceTokenizer;
import com.jettmarks.bikeTrains.client.activities.tabbar.TabBarPlace.TabBarPlaceTokenizer;
import com.jettmarks.bikeTrains.client.places.HomePlace.HomePlaceTokenizer;

/**
 * @author Daniel Kurka
 * 
 */
@WithTokenizers({ HomePlaceTokenizer.class, UIPlaceTokenizer.class,
		ScrollWidgetPlaceTokenizer.class, AboutPlaceTokenizer.class,
		ButtonPlaceTokenizer.class, AnimationDissolvePlaceTokenizer.class,
		AnimationFadePlaceTokenizer.class, AnimationFlipPlaceTokenizer.class,
		AnimationPlaceTokenizer.class, AnimationPopPlaceTokenizer.class,
		AnimationSlidePlaceTokenizer.class,
		AnimationSlideUpPlaceTokenizer.class,
		AnimationSwapPlaceTokenizer.class, ButtonBarPlaceTokenizer.class,
		ElementsPlaceTokenizer.class, FormsPlace.Tokenizer.class,
		PopupPlaceTokenizer.class, ProgressBarPlaceTokenizer.class,
		SearchBoxPlaceTokenizer.class, SliderPlaceTokenizer.class,
		TabBarPlaceTokenizer.class, PullToRefreshPlace.Tokenizer.class,
		AnimationCubePlace.Tokenizer.class,
		ProgressIndicatorPlace.Tokenizer.class, CarouselPlace.Tokenizer.class,
		GroupedCellListPlace.Tokenizer.class })
public interface AppPlaceHistoryMapper extends PlaceHistoryMapper {
}
