// Generated code from Butter Knife. Do not modify!
package com.ceri.visitemusee.interestpoint;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class InterestPointFragment$$ViewBinder<T extends com.ceri.visitemusee.interestpoint.InterestPointFragment> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131493008, "field 'gridViewPhoto'");
    target.gridViewPhoto = finder.castView(view, 2131493008, "field 'gridViewPhoto'");
    view = finder.findRequiredView(source, 2131493010, "field 'gridView360'");
    target.gridView360 = finder.castView(view, 2131493010, "field 'gridView360'");
    view = finder.findRequiredView(source, 2131493012, "field 'gridViewVideo'");
    target.gridViewVideo = finder.castView(view, 2131493012, "field 'gridViewVideo'");
    view = finder.findRequiredView(source, 2131493004, "field 'interestPointPicture'");
    target.interestPointPicture = finder.castView(view, 2131493004, "field 'interestPointPicture'");
    view = finder.findRequiredView(source, 2131493005, "field 'interestPointTitle'");
    target.interestPointTitle = finder.castView(view, 2131493005, "field 'interestPointTitle'");
    view = finder.findRequiredView(source, 2131493006, "field 'interestPointContent'");
    target.interestPointContent = finder.castView(view, 2131493006, "field 'interestPointContent'");
    view = finder.findRequiredView(source, 2131493007, "field 'interestPointPhotoTitle'");
    target.interestPointPhotoTitle = finder.castView(view, 2131493007, "field 'interestPointPhotoTitle'");
    view = finder.findRequiredView(source, 2131493009, "field 'interestPoint360Title'");
    target.interestPoint360Title = finder.castView(view, 2131493009, "field 'interestPoint360Title'");
    view = finder.findRequiredView(source, 2131493011, "field 'interestPointVideoTitle'");
    target.interestPointVideoTitle = finder.castView(view, 2131493011, "field 'interestPointVideoTitle'");
  }

  @Override public void unbind(T target) {
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
