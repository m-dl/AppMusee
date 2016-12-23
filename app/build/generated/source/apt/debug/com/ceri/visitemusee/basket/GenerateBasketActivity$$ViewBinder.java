// Generated code from Butter Knife. Do not modify!
package com.ceri.visitemusee.basket;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class GenerateBasketActivity$$ViewBinder<T extends com.ceri.visitemusee.basket.GenerateBasketActivity> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131558509, "field 'm_Toolbar'");
    target.m_Toolbar = finder.castView(view, 2131558509, "field 'm_Toolbar'");
    view = finder.findRequiredView(source, 2131558506, "field 'm_DrawerLayout'");
    target.m_DrawerLayout = finder.castView(view, 2131558506, "field 'm_DrawerLayout'");
    view = finder.findRequiredView(source, 2131558529, "field 'generateBasketText'");
    target.generateBasketText = finder.castView(view, 2131558529, "field 'generateBasketText'");
    view = finder.findRequiredView(source, 2131558531, "field 'basketValueText'");
    target.basketValueText = finder.castView(view, 2131558531, "field 'basketValueText'");
    view = finder.findRequiredView(source, 2131558530, "field 'qrcode'");
    target.qrcode = finder.castView(view, 2131558530, "field 'qrcode'");
  }

  @Override public void unbind(T target) {
    target.m_Toolbar = null;
    target.m_DrawerLayout = null;
    target.generateBasketText = null;
    target.basketValueText = null;
    target.qrcode = null;
  }
}
