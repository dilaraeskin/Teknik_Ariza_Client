package com.uniyaz.ariza.ui.views;

import com.uniyaz.ariza.domain.Ariza;
import com.uniyaz.ariza.domain.enumPackage.EnumArizaDurum;
import com.uniyaz.ariza.service.rest.ClientArizaDto;
import com.uniyaz.ariza.service.rest.RestClient;
import com.uniyaz.ariza.ui.components.KaydetButton;
import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.fieldgroup.PropertyId;
import com.vaadin.data.util.BeanItem;
import com.vaadin.shared.ui.datefield.Resolution;
import com.vaadin.ui.*;

public class AddArizaView  extends VerticalLayout {

    @PropertyId("id")
    private TextField idField;
    @PropertyId("musteriIsim")
    private TextField musteriIsimField;
    @PropertyId("ariza")
    private TextField arizaField;
    @PropertyId("arizaAciklama")
    private TextField arizaAciklamaField;
    @PropertyId("date")
    private DateField dateField;
    @PropertyId("enumArizaDurum")
    private ComboBox enumArizaDurumComboCox;
    private FormLayout mainLayout;
    private BeanItem<Ariza> item;
    private FieldGroup binder;


    public AddArizaView() {
        buildMainLayout();
        fillViewByAriza(new Ariza());
    }

    public void fillViewByAriza(Ariza Ariza) {

        item = new BeanItem<Ariza>(Ariza);
        binder = new FieldGroup(item);
        binder.bindMemberFields(this);


    }

    public void buildMainLayout() {

        mainLayout = new FormLayout();
        addComponent(mainLayout);

        VerticalLayout layout = new VerticalLayout();


        idField = new TextField("Id");
        idField.setEnabled(false);
        layout.addComponent(idField);
        layout.setComponentAlignment(idField, Alignment.MIDDLE_CENTER);


        musteriIsimField = new TextField("Müşteri İsim:");
        layout.addComponent(musteriIsimField);
        layout.setComponentAlignment(musteriIsimField, Alignment.MIDDLE_CENTER);

        arizaField = new TextField("Arıza:");
        layout.addComponent(arizaField);
        layout.setComponentAlignment(arizaField, Alignment.MIDDLE_CENTER);

        arizaAciklamaField = new TextField("Arıza Açıklama:");
        layout.addComponent(arizaAciklamaField);
        layout.setComponentAlignment(arizaAciklamaField, Alignment.MIDDLE_CENTER);


        dateField = new DateField("Tarih:");
        layout.addComponent(dateField);
        layout.setComponentAlignment(dateField, Alignment.MIDDLE_CENTER);



        enumArizaDurumComboCox = new ComboBox("Arıza Durumu:");
        for (EnumArizaDurum value : EnumArizaDurum.values()) {
            enumArizaDurumComboCox.addItem(value);
        }

        layout.addComponent(enumArizaDurumComboCox);
        layout.setComponentAlignment(enumArizaDurumComboCox, Alignment.MIDDLE_CENTER);


        KaydetButton kaydetButton = new KaydetButton();
        kaydetButton.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent clickEvent) {

                saveAriza();
            }

        });

        layout.addComponent(kaydetButton);
        layout.setComponentAlignment(kaydetButton, Alignment.MIDDLE_CENTER);
        mainLayout.addComponent(layout);

    }

    private void saveAriza() {
        try {
            binder.commit();
            //Ariza ariza = item.getBean();

            ClientArizaDto clientArizaDto=new ClientArizaDto();
            RestClient restClient= new RestClient();
            restClient.saveAriza(clientArizaDto);


            idField.setValue(clientArizaDto.getId().toString());
            musteriIsimField.setValue(clientArizaDto.getMusteriIsim());
            arizaField.setValue(clientArizaDto.getAriza());
            arizaAciklamaField.setValue(clientArizaDto.getArizaAciklama());
            dateField.setValue(clientArizaDto.getKayitTarih());
            enumArizaDurumComboCox.setValue(clientArizaDto.getEnumArizaDurum().toString());

        } catch (FieldGroup.CommitException e) {
            System.out.println(e.getMessage());
        }
    }


}
