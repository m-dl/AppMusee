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
    view = finder.findRequiredView(source, 2131492988, "field 'gridViewPhoto'");
    target.gridViewPhoto = finder.castView(view, 2131492988, "field 'gridViewPhoto'");
    view = finder.findRequiredView(source, 2131492990, "field 'gridView360'");
    target.gridView360 = finder.castView(view, 2131492990, "field 'gridView360'");
    view = finder.findRequiredView(source, 2131492992, "field 'gridViewVideo'");
    target.gridViewVideo = finder.castView(view, 2131492992, "field 'gridViewVideo'");
    view = finder.findRequiredView(source, 2131492984, "field 'interestPointPicture'");
    target.interestPointPicture = finder.castView(view, 2131492984, "field 'interestPointPicture'");
    view = finder.findRequiredView(source, 2131492985, "field 'interestPointTitle'");
    target.interestPointTitle = finder.castView(view, 2131492985, "field 'interestPointTitle'");
    view = finder.findRequiredView(source, 2131492986, "field 'interestPointContent'");
    target.interestPointContent = finder.castView(view, 2131492986, "field 'interestPointContent'");
    view = finder.findRequiredView(source, 2131492987, "field 'interestPointPhotoTitle'");
    target.interestPointPhotoTitle = finder.castView(view, 2131492987, "field 'interestPointPhotoTitle'");
    view = finder.findRequiredView(source, 2131492989, "field 'interestPoint360Title'");
    target.interestPoint360Title = finder.castView(view, 2131492989, "field 'interestPoint360Title'");
    view = finder.findRequiredView(source, 2131492991, "field 'interestPointVideoTitle'");
    target.interestPointVideoTitle = finder.castView(view, 2131492991, "field 'interestPointVideoTitle'");
  }

  @Override public void unbind(T target) {
    target.m_DrawerLayout = null;
    target.m_Toolbar = null;
    target.gridViewPhoto = null;
    target.gridView360 = null;
    target.gridViewVideo = null;
    target.interestPointPicture = null;
    target.interestPointTitle = null;
    target.interestPointContent = null;
    target.interestPointPhotoTitle = null;
    target.interestPoint360Title = null;
    target.interestPointVideoTitle = null;
  }
}
