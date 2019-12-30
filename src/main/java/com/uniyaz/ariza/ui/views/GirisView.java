package com.uniyaz.ariza.ui.views;

import com.uniyaz.MyUI;
import com.uniyaz.ariza.dao.KullaniciDao;
import com.uniyaz.ariza.domain.Kullanici;
import com.uniyaz.ariza.ui.components.adminComponents.AdminGeneral;
import com.vaadin.server.FontAwesome;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;

import java.util.List;

public class GirisView extends VerticalLayout {

    String dbUsername;
    String dbPassword;
    String  dbYetki;
    private TextField username;
    private PasswordField password;
    MyUI myUI = new MyUI();
    private FormLayout mainLayout;



    public GirisView() {

        myUI=(MyUI) UI.getCurrent();
        buildMainLayout();

    }


    private void  buildMainLayout() {


        mainLayout = new FormLayout();
        addComponent(mainLayout);
        VerticalLayout layout = new VerticalLayout();

        username = new TextField("Kullanıcı Adı:");
        layout.addComponent(username);
        layout.setComponentAlignment(username, Alignment.MIDDLE_CENTER);


        password = new PasswordField("Şifre:");
        layout.addComponent(password);
        layout.setComponentAlignment(password, Alignment.MIDDLE_CENTER);


        Button girisButton = new Button();
        girisButton.setCaption("Giriş Yap");
        girisButton.setIcon(FontAwesome.CHECK);
        girisButton.addStyleName(ValoTheme.BUTTON_QUIET);
        girisButton.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent clickEvent) {
                girisKontrol();

            }
        });
        layout.addComponent(girisButton);
        layout.setComponentAlignment(girisButton, Alignment.MIDDLE_CENTER);
        mainLayout.addComponent(layout);


   }


    private void girisKontrol() {


        KullaniciDao kullaniciDao = new KullaniciDao();
        List<Kullanici> kullaniciList = kullaniciDao.findAllKullanici();
        for (Kullanici kullanici : kullaniciList) {

            String usernameValue = username.getValue();
            String passwordValue = password.getValue();

            dbYetki = kullanici.getEnumKullaniciYetki().toString();
            dbUsername = kullanici.getUsername();
            dbPassword = kullanici.getPassword();

            if (dbUsername.equals(usernameValue)) {

                if (dbPassword.equals(passwordValue)) {


                 if (dbYetki.equals("ADMIN")) {
                    AdminGeneral adminGeneral = new AdminGeneral();
                    myUI.setContent(adminGeneral);

                } else {
                    Label label = new Label("Giriş bilgileriniz yanlış. Lütfen kontrol ediniz.");
                    addComponent(label);
                }
                }
            }
        }
    }

    public TextField getUsername() {
        return username;
    }

    public void setUsername(TextField username) {
        this.username = username;
    }

    public PasswordField getPassword() {
        return password;
    }

    public void setPassword(PasswordField password) {
        this.password = password;
    }
}
