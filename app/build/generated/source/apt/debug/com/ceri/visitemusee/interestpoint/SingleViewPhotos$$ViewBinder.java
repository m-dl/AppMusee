// Generated code from Butter Knife. Do not modify!
package com.ceri.visitemusee.interestpoint;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class SingleViewPhotos$$ViewBinder<T extends com.ceri.visitemusee.interestpoint.SingleViewPhotos> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131427434, "field 'm_DrawerLayout'");
    target.m_DrawerLayout = finder.castView(view, 2131427434, "field 'm_DrawerLayout'");
    view = finder.findRequiredView(source, 2131427437, "field 'm_Toolbar'");
    target.m_Toolbar = finder.castView(view, 2131427437, "field 'm_Toolbar'");
    view = finder.findRequiredView(source, 2131427467, "field 'imageView'");
    target.imageView = finder.castView(view, 2131427467, "field 'imageView'");
  }

  @Override public void unbind(T target) {
    target.m_DrawerLayout = null;
    target.m_Toolbar = null;
    target.imageView = null;
  }
}
