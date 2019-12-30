package com.uniyaz.ariza.ui.components.adminComponents;


import com.uniyaz.ariza.ui.components.Header;
import com.vaadin.ui.VerticalLayout;

public class AdminGeneral extends VerticalLayout {

public AdminGeneral() {

        Header header = new Header();
        addComponent(header);

        AdminContainer container = new AdminContainer(header);
        addComponent(container);


        }
}