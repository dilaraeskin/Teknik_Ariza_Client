package com.uniyaz;

import com.uniyaz.ariza.ui.components.loginComponents.General;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.annotations.Widgetset;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.UI;

import javax.servlet.annotation.WebServlet;

/**
 *
 */
@Theme("mytheme")
@Widgetset("com.uniyaz.MyAppWidgetset")
public class MyUI extends UI {


    @Override
    protected void init(VaadinRequest vaadinRequest) {

//        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
//        Session sesion= sessionFactory.openSession();

        General general=new General();
        setContent(general);

    }


    @WebServlet(urlPatterns = "/*", name = "MyUIServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = MyUI.class, productionMode = false)
    public static class MyUIServlet extends VaadinServlet {
    }
}
