// Generated code from Butter Knife. Do not modify!
package com.ceri.visitemusee.interestpoint;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class InterestPointActivity$$ViewBinder<T extends com.ceri.visitemusee.interestpoint.InterestPointActivity> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131492970, "field 'm_DrawerLayout'");
    target.m_DrawerLayout = finder.castView(view, 2131492970, "field 'm_DrawerLayout'");
    view = finder.findRequiredView(source, 2131492973, "field 'm_Toolbar'");
    target.m_Toolbar = finder.castView(view, 2131492973, "field 'm_Toolbar'");
  }

  @Override public void unbind(T target) {
    target.m_DrawerLayout = null;
    target.m_Toolbar = null;
  }
}
