package com.uniyaz.ariza.ui.views;

import com.uniyaz.ariza.domain.Ariza;
import com.uniyaz.ariza.domain.enumPackage.EnumArizaDurum;
import com.uniyaz.ariza.service.dto.ArizaDto;
import com.uniyaz.ariza.service.rest.ClientArizaDto;
import com.uniyaz.ariza.service.rest.RestClient;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.data.Item;
import com.vaadin.data.util.IndexedContainer;
import com.vaadin.event.ItemClickEvent;
import com.vaadin.shared.ui.MultiSelectMode;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Table;
import java.util.Date;
import java.util.List;


public class ListArizaView extends VerticalLayout {

    private Table table;

    private IndexedContainer indexedContainer;
    private AddArizaView addArizaView;


    Ariza ariza;

    public ListArizaView() {
        buildTableContainer();

        HorizontalLayout layout = new HorizontalLayout();
        buildTable();
        layout.addComponent(table);
        layout.setComponentAlignment(table, Alignment.MIDDLE_CENTER);

        addArizaView = new AddArizaView();
        layout.addComponent(addArizaView);
        layout.setComponentAlignment(addArizaView, Alignment.TOP_RIGHT);

        layout.setSpacing(true);
        layout.setMargin(true);
        layout.setMargin(true);
        addComponent(layout);
        setComponentAlignment(layout,Alignment.MIDDLE_CENTER);
        fillTable();
    }


    public void fillTable() {

        RestClient restClient=new RestClient();
        List<ClientArizaDto> arizaDtoList=restClient.arizaList();

        for (ClientArizaDto clientArizaDto : arizaDtoList) {
            Item item = indexedContainer.addItem(clientArizaDto);
            item.getItemProperty("id").setValue(clientArizaDto.getId());
            item.getItemProperty("date").setValue(clientArizaDto.getMusteriIsim());
            item.getItemProperty("date").setValue(clientArizaDto.getAriza());
            item.getItemProperty("date").setValue(clientArizaDto.getArizaAciklama());
            item.getItemProperty("date").setValue(clientArizaDto.getKayitTarih());
            item.getItemProperty("Ders").setValue(clientArizaDto.getEnumArizaDurum());


        }
    }

    public void buildTableContainer() {

        indexedContainer = new IndexedContainer();
        indexedContainer.addContainerProperty("id", Long.class, null);
        indexedContainer.addContainerProperty("musteriIsim", String.class, null);
        indexedContainer.addContainerProperty("ariza", String.class, null);
        indexedContainer.addContainerProperty("arizaAciklama", String.class, null);
        indexedContainer.addContainerProperty("kayitTarih", Date.class, null);
        indexedContainer.addContainerProperty("arizaDurum", EnumArizaDurum.class, null);

    }


    public void buildTable() {
        table = new Table();

        table.setContainerDataSource(indexedContainer);
        table.setColumnHeaders("ID","MÜŞTERİ İSİM", "ARIZA", "ARIZA AÇIKLAMA","KAYIT TARİHİ","ARIZA DURUM");
        table.setSelectable(true);
        table.setMultiSelectMode(MultiSelectMode.SIMPLE);
        table.setMultiSelect(false);

        table.addItemClickListener(new ItemClickEvent.ItemClickListener() {
            @Override
            public void itemClick(ItemClickEvent itemClickEvent) {
                ariza = (Ariza) itemClickEvent.getItemId();

                addArizaView.fillViewByAriza(ariza);
            }
        });
    }
}
