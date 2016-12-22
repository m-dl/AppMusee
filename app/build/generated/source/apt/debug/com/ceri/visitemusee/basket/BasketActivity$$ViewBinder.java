// Generated code from Butter Knife. Do not modify!
package com.ceri.visitemusee.basket;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class BasketActivity$$ViewBinder<T extends com.ceri.visitemusee.basket.BasketActivity> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131558506, "field 'm_DrawerLayout'");
    target.m_DrawerLayout = finder.castView(view, 2131558506, "field 'm_DrawerLayout'");
    view = finder.findRequiredView(source, 2131558509, "field 'm_Toolbar'");
    target.m_Toolbar = finder.castView(view, 2131558509, "field 'm_Toolbar'");
    view = finder.findRequiredView(source, 2131558511, "field 'validateBasketButton'");
    target.validateBasketButton = finder.castView(view, 2131558511, "field 'validateBasketButton'");
    view = finder.findRequiredView(source, 2131558512, "field 'emptyBasketButton'");
    target.emptyBasketButton = finder.castView(view, 2131558512, "field 'emptyBasketButton'");
    view = finder.findRequiredView(source, 2131558513, "field 'itemList'");
    target.itemList = finder.castView(view, 2131558513, "field 'itemList'");
  }

  @Override public void unbind(T target) {
    target.m_DrawerLayout = null;
    target.m_Toolbar = null;
    target.validateBasketButton = null;
    target.emptyBasketButton = null;
    target.itemList = null;
  }
}
