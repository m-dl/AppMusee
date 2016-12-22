// Generated code from Butter Knife. Do not modify!
package com.ceri.visitemusee.basket;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class BasketItemActivity$$ViewBinder<T extends com.ceri.visitemusee.basket.BasketItemActivity> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131558506, "field 'm_DrawerLayout'");
    target.m_DrawerLayout = finder.castView(view, 2131558506, "field 'm_DrawerLayout'");
    view = finder.findRequiredView(source, 2131558509, "field 'm_Toolbar'");
    target.m_Toolbar = finder.castView(view, 2131558509, "field 'm_Toolbar'");
    view = finder.findRequiredView(source, 2131558516, "field 'itemPicture'");
    target.itemPicture = finder.castView(view, 2131558516, "field 'itemPicture'");
    view = finder.findRequiredView(source, 2131558517, "field 'itemTitle'");
    target.itemTitle = finder.castView(view, 2131558517, "field 'itemTitle'");
    view = finder.findRequiredView(source, 2131558518, "field 'itemPrice'");
    target.itemPrice = finder.castView(view, 2131558518, "field 'itemPrice'");
    view = finder.findRequiredView(source, 2131558520, "field 'itemContent'");
    target.itemContent = finder.castView(view, 2131558520, "field 'itemContent'");
    view = finder.findRequiredView(source, 2131558519, "field 'addBasket'");
    target.addBasket = finder.castView(view, 2131558519, "field 'addBasket'");
  }

  @Override public void unbind(T target) {
    target.m_DrawerLayout = null;
    target.m_Toolbar = null;
    target.itemPicture = null;
    target.itemTitle = null;
    target.itemPrice = null;
    target.itemContent = null;
    target.addBasket = null;
  }
}
