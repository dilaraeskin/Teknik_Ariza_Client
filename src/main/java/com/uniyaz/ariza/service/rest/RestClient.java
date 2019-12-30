package com.uniyaz.ariza.service.rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Type;
import java.nio.charset.Charset;
import java.util.List;

public class RestClient {


    public static void main(String[] args) throws UnsupportedEncodingException {

        ClientArizaDto clientArizaDto = new ClientArizaDto();
        saveAriza(clientArizaDto);
        arizaList();
    }

    public static void saveAriza(ClientArizaDto clientArizaDto) {
        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").create();
        String categoryDtoJson = gson.toJson(clientArizaDto);


        HttpPost post = new HttpPost("http://localhost:8080/rest/ariza/saveAriza");
        HttpEntity httpEntity = new StringEntity(categoryDtoJson, Charset.forName("utf-8"));
        post.setEntity(httpEntity);
        post.addHeader("content-type", "application/json");

        try (CloseableHttpClient httpClient = HttpClients.createDefault();
             CloseableHttpResponse response = httpClient.execute(post)) {

            HttpEntity entity = response.getEntity();
            String dataAsJsonStr = EntityUtils.toString(entity);

            ClientArizaDto savedAriza = gson.fromJson(dataAsJsonStr, ClientArizaDto.class);
            System.out.println(savedAriza.getId() + " " + savedAriza.getMusteriIsim() + " " +
                    savedAriza.getAriza() + " " + savedAriza.getArizaAciklama() + " " +
                    savedAriza.getKayitTarih() + " " + savedAriza.getEnumArizaDurum());
        } catch (
                ClientProtocolException e) {
            e.printStackTrace();
        } catch (
                IOException e) {
            e.printStackTrace();
        }
    }


    public static List<ClientArizaDto> arizaList() {
        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").create();

        ClientArizaDto clientArizaDto = new ClientArizaDto();

        String categoryDtoJson = gson.toJson(clientArizaDto);


        HttpPost post = new HttpPost("http://localhost:8080/rest/ariza/saveAriza");
        HttpEntity httpEntity = new StringEntity(categoryDtoJson, Charset.forName("utf-8"));
        post.setEntity(httpEntity);
        post.addHeader("content-type", "application/json");

        try (CloseableHttpClient httpClient = HttpClients.createDefault();
             CloseableHttpResponse response = httpClient.execute(post)) {

            HttpEntity entity = response.getEntity();
            String dataAsJsonStr = EntityUtils.toString(entity);

            List<ClientArizaDto> arizaDtoList = gson.fromJson(dataAsJsonStr, (Type) ClientArizaDto.class);
            return arizaDtoList;
        } catch (
                ClientProtocolException e) {
            e.printStackTrace();
        } catch (
                IOException e) {
            e.printStackTrace();
        }

        return arizaList();
    }
}