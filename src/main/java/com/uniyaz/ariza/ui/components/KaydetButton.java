package com.uniyaz.ariza.ui.components;

import com.vaadin.server.FontAwesome;
import com.vaadin.ui.Button;
import com.vaadin.ui.themes.ValoTheme;

public class KaydetButton extends Button {

    public KaydetButton() {
        setIcon(FontAwesome.CHECK);
        setCaption("Kaydet");
        addStyleName(ValoTheme.BUTTON_QUIET);
    }
}
