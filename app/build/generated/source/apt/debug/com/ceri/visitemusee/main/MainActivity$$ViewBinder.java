// Generated code from Butter Knife. Do not modify!
package com.ceri.visitemusee.main;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class MainActivity$$ViewBinder<T extends com.ceri.visitemusee.main.MainActivity> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131427434, "field 'm_DrawerLayout'");
    target.m_DrawerLayout = finder.castView(view, 2131427434, "field 'm_DrawerLayout'");
    view = finder.findRequiredView(source, 2131427480, "field 'm_NavigationView'");
    target.m_NavigationView = finder.castView(view, 2131427480, "field 'm_NavigationView'");
    view = finder.findRequiredView(source, 2131427437, "field 'm_Toolbar'");
    target.m_Toolbar = finder.castView(view, 2131427437, "field 'm_Toolbar'");
    view = finder.findRequiredView(source, 2131427477, "field 'm_FABInfo' and method 'onInfoClick'");
    target.m_FABInfo = finder.castView(view, 2131427477, "field 'm_FABInfo'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.onInfoClick();
        }
      });
    view = finder.findRequiredView(source, 2131427478, "field 'm_FABFloorsUp' and method 'onFloorUpClick'");
    target.m_FABFloorsUp = finder.castView(view, 2131427478, "field 'm_FABFloorsUp'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.onFloorUpClick();
        }
      });
    view = finder.findRequiredView(source, 2131427479, "field 'm_FABFloorsDown' and method 'onFloorDownClick'");
    target.m_FABFloorsDown = finder.castView(view, 2131427479, "field 'm_FABFloorsDown'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.onFloorDownClick();
        }
      });
  }

  @Override public void unbind(T target) {
    target.m_DrawerLayout = null;
    target.m_NavigationView = null;
    target.m_Toolbar = null;
    target.m_FABInfo = null;
    target.m_FABFloorsUp = null;
    target.m_FABFloorsDown = null;
  }
}
