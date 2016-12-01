// Generated code from Butter Knife. Do not modify!
package com.ceri.visitemusee.info;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class InfoActivity$$ViewBinder<T extends com.ceri.visitemusee.info.InfoActivity> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131427434, "field 'm_DrawerLayout'");
    target.m_DrawerLayout = finder.castView(view, 2131427434, "field 'm_DrawerLayout'");
    view = finder.findRequiredView(source, 2131427437, "field 'm_Toolbar'");
    target.m_Toolbar = finder.castView(view, 2131427437, "field 'm_Toolbar'");
    view = finder.findRequiredView(source, 2131427444, "field 'gridViewPhoto'");
    target.gridViewPhoto = finder.castView(view, 2131427444, "field 'gridViewPhoto'");
    view = finder.findRequiredView(source, 2131427440, "field 'infoPicture'");
    target.infoPicture = finder.castView(view, 2131427440, "field 'infoPicture'");
    view = finder.findRequiredView(source, 2131427441, "field 'infoTitle'");
    target.infoTitle = finder.castView(view, 2131427441, "field 'infoTitle'");
    view = finder.findRequiredView(source, 2131427442, "field 'infoContent'");
    target.infoContent = finder.castView(view, 2131427442, "field 'infoContent'");
    view = finder.findRequiredView(source, 2131427443, "field 'infoPhotoTitle'");
    target.infoPhotoTitle = finder.castView(view, 2131427443, "field 'infoPhotoTitle'");
  }

  @Override public void unbind(T target) {
    target.m_DrawerLayout = null;
    target.m_Toolbar = null;
    target.gridViewPhoto = null;
    target.infoPicture = null;
    target.infoTitle = null;
    target.infoContent = null;
    target.infoPhotoTitle = null;
  }
}
