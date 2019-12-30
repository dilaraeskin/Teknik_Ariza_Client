package com.uniyaz.ariza.ui.components.adminComponents;


import com.uniyaz.MyUI;
import com.uniyaz.ariza.ui.components.Content;
import com.uniyaz.ariza.ui.components.Header;
import com.uniyaz.ariza.ui.components.MenuButton;
import com.uniyaz.ariza.ui.components.loginComponents.General;
import com.uniyaz.ariza.ui.views.AddArizaView;
import com.uniyaz.ariza.ui.views.ListArizaView;
import com.vaadin.server.FontAwesome;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.UI;

public class AdminHeaderMenu extends HorizontalLayout {

    private Header header;
    private Content content;

    private MenuButton btnArizaEkle;
    private MenuButton btnArizaListele;


    private MenuButton btnCikis;


    public AdminHeaderMenu(Header header, Content content) {

        this.header = header;
        this.content = content;

        setSpacing(true);
        setMargin(true);

        buildArizaEkleButon();
        addComponent(btnArizaEkle);

        buildArizaListeleButon();
        addComponent(btnArizaListele);



        buildCikisButon();
        addComponent(btnCikis);

    }

    private void buildArizaEkleButon() {
        btnArizaEkle = new MenuButton(FontAwesome.PLUS_SQUARE);
        btnArizaEkle.setCaption("Arıza Ekle");
        btnArizaEkle.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent clickEvent) {
                AddArizaView addArizaView = new AddArizaView();
                content.setContent(addArizaView);
            }
        });
    }

    private void buildArizaListeleButon() {
        btnArizaListele = new MenuButton(FontAwesome.PLUS_SQUARE);
        btnArizaListele.setCaption("Ariza Listele");
        btnArizaListele.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent clickEvent) {
                ListArizaView listArizaView = new ListArizaView();
                content.setContent(listArizaView);
            }
        });
    }


    private void buildCikisButon() {
        btnCikis = new MenuButton(FontAwesome.PLUS_SQUARE);
        btnCikis.setCaption("Çıkış");

        btnCikis.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent clickEvent) {


                MyUI myUI;
                myUI = (MyUI) UI.getCurrent();
                General general = new General();
                myUI.setContent(general);
            }
        });
    }
}