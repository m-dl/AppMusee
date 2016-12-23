// Generated code from Butter Knife. Do not modify!
package com.ceri.visitemusee.custom;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class CustomVisitActivity$$ViewBinder<T extends com.ceri.visitemusee.custom.CustomVisitActivity> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131558506, "field 'm_DrawerLayout'");
    target.m_DrawerLayout = finder.castView(view, 2131558506, "field 'm_DrawerLayout'");
    view = finder.findRequiredView(source, 2131558509, "field 'm_Toolbar'");
    target.m_Toolbar = finder.castView(view, 2131558509, "field 'm_Toolbar'");
    view = finder.findRequiredView(source, 2131558526, "field 'visitArtists'");
    target.visitArtists = finder.castView(view, 2131558526, "field 'visitArtists'");
    view = finder.findRequiredView(source, 2131558524, "field 'visitRooms'");
    target.visitRooms = finder.castView(view, 2131558524, "field 'visitRooms'");
    view = finder.findRequiredView(source, 2131558522, "field 'visitItems'");
    target.visitItems = finder.castView(view, 2131558522, "field 'visitItems'");
    view = finder.findRequiredView(source, 2131558521, "field 'customText'");
    target.customText = finder.castView(view, 2131558521, "field 'customText'");
    view = finder.findRequiredView(source, 2131558525, "field 'visitRoomsSpinner'");
    target.visitRoomsSpinner = finder.castView(view, 2131558525, "field 'visitRoomsSpinner'");
    view = finder.findRequiredView(source, 2131558527, "field 'visitArtistsSpinner'");
    target.visitArtistsSpinner = finder.castView(view, 2131558527, "field 'visitArtistsSpinner'");
    view = finder.findRequiredView(source, 2131558523, "field 'visitItemsSpinner'");
    target.visitItemsSpinner = finder.castView(view, 2131558523, "field 'visitItemsSpinner'");
    view = finder.findRequiredView(source, 2131558528, "field 'startCustom'");
    target.startCustom = finder.castView(view, 2131558528, "field 'startCustom'");
  }

  @Override public void unbind(T target) {
    target.m_DrawerLayout = null;
    target.m_Toolbar = null;
    target.visitArtists = null;
    target.visitRooms = null;
    target.visitItems = null;
    target.customText = null;
    target.visitRoomsSpinner = null;
    target.visitArtistsSpinner = null;
    target.visitItemsSpinner = null;
    target.startCustom = null;
  }
}
