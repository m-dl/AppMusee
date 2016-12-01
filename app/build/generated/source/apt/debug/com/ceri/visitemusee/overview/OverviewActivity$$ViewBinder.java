// Generated code from Butter Knife. Do not modify!
package com.ceri.visitemusee.overview;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class OverviewActivity$$ViewBinder<T extends com.ceri.visitemusee.overview.OverviewActivity> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131427434, "field 'm_DrawerLayout'");
    target.m_DrawerLayout = finder.castView(view, 2131427434, "field 'm_DrawerLayout'");
    view = finder.findRequiredView(source, 2131427457, "field 'imageContainer'");
    target.imageContainer = finder.castView(view, 2131427457, "field 'imageContainer'");
    view = finder.findRequiredView(source, 2131427437, "field 'm_Toolbar'");
    target.m_Toolbar = finder.castView(view, 2131427437, "field 'm_Toolbar'");
    view = finder.findRequiredView(source, 2131427458, "field 'overviewPicture1'");
    target.overviewPicture1 = finder.castView(view, 2131427458, "field 'overviewPicture1'");
    view = finder.findRequiredView(source, 2131427459, "field 'overviewPicture2'");
    target.overviewPicture2 = finder.castView(view, 2131427459, "field 'overviewPicture2'");
    view = finder.findRequiredView(source, 2131427460, "field 'overviewPicture3'");
    target.overviewPicture3 = finder.castView(view, 2131427460, "field 'overviewPicture3'");
    view = finder.findRequiredView(source, 2131427455, "field 'overviewTitle'");
    target.overviewTitle = finder.castView(view, 2131427455, "field 'overviewTitle'");
    view = finder.findRequiredView(source, 2131427456, "field 'overviewLength'");
    target.overviewLength = finder.castView(view, 2131427456, "field 'overviewLength'");
    view = finder.findRequiredView(source, 2131427461, "field 'overviewContent'");
    target.overviewContent = finder.castView(view, 2131427461, "field 'overviewContent'");
    view = finder.findRequiredView(source, 2131427463, "field 'cancelVisit'");
    target.cancelVisit = finder.castView(view, 2131427463, "field 'cancelVisit'");
    view = finder.findRequiredView(source, 2131427464, "field 'validateVisit'");
    target.validateVisit = finder.castView(view, 2131427464, "field 'validateVisit'");
  }

  @Override public void unbind(T target) {
    target.m_DrawerLayout = null;
    target.imageContainer = null;
    target.m_Toolbar = null;
    target.overviewPicture1 = null;
    target.overviewPicture2 = null;
    target.overviewPicture3 = null;
    target.overviewTitle = null;
    target.overviewLength = null;
    target.overviewContent = null;
    target.cancelVisit = null;
    target.validateVisit = null;
  }
}
