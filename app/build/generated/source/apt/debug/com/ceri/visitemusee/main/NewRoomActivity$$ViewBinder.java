// Generated code from Butter Knife. Do not modify!
package com.ceri.visitemusee.main;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class NewRoomActivity$$ViewBinder<T extends com.ceri.visitemusee.main.NewRoomActivity> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131492995, "field 'newRoomText'");
    target.newRoomText = finder.castView(view, 2131492995, "field 'newRoomText'");
    view = finder.findRequiredView(source, 2131492996, "field 'newRoomButton'");
    target.newRoomButton = finder.castView(view, 2131492996, "field 'newRoomButton'");
  }

  @Override public void unbind(T target) {
    target.newRoomText = null;
    target.newRoomButton = null;
  }
}
