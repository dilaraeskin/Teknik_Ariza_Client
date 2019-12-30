package com.uniyaz.ariza.ui.components.loginComponents;

import com.uniyaz.ariza.ui.components.Header;
import com.vaadin.ui.VerticalLayout;

public class General extends VerticalLayout {

    public General() {
        Header header = new Header();
        addComponent(header);

        GirisContent content = new GirisContent();
        addComponent(content);

    }
}