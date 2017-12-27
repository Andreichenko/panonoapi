package de.andreichenko.panonoapi;


import de.andreichenko.panonoapi.model.Panorama;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.LinkedHashMap;
import java.util.List;

public class PanonoRestApiAppTestClient{

    public static final String REST_SERVICE_URI = "http://localhost:8080/PanonoRestApiApp/api";

    /*GET*/
    @SuppressWarnings("uncheked")
    private static void listAllPanorama(){
        System.out.println("Testing listAllPanorama API -----");
        RestTemplate restTemplate = new RestTemplate();
        List<LinkedHashMap<String, Object>> panoramaMap = restTemplate.getForObject(REST_SERVICE_URI+"/panorama/", List.class);

        if (panoramaMap!=null){
            for (LinkedHashMap<String, Object> map : panoramaMap){
                System.out.println("Panorama : count=" + map.get("count")+ "timestamp = " + map.get("timestamp"));
            }
        }else {
            System.out.println("no panorama exist-----");
        }
    }


    private static void getPanorama(){
        System.out.println("Testing getPanorama API----------");
        RestTemplate restTemplate = new RestTemplate();
        Panorama panorama = restTemplate.getForObject(REST_SERVICE_URI+"/panorama/1", Panorama.class);
        System.out.println(panorama);
    }

    /* POST */
    private static void createPanorama() {
        System.out.println("Testing create Panorama API----------");
        RestTemplate restTemplate = new RestTemplate();
        Panorama panorama = new Panorama(0,123123,51,134, 2, 1);
        URI uri = restTemplate.postForLocation(REST_SERVICE_URI+"/panorama/", panorama, Panorama.class);
        System.out.println("Location : "+uri.toASCIIString());
    }

    /* PUT */
    private static void updatePanorama() {
        System.out.println("Testing update Panorama API----------");
        RestTemplate restTemplate = new RestTemplate();
        Panorama panorama  = new Panorama(1,123123,33, 70000, 1, 1);
        restTemplate.put(REST_SERVICE_URI+"/panorama/1", panorama);
        System.out.println(panorama);
    }

    /* DELETE */
    private static void deletePanorama() {
        System.out.println("Testing delete Panorama API----------");
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.delete(REST_SERVICE_URI+"/panorama/3");
    }


    /* DELETE */
    private static void deleteAllPanorama() {
        System.out.println("Testing all delete Panorama API----------");
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.delete(REST_SERVICE_URI+"/panorama/");
    }


    public static void main(String[] args) {
        listAllPanorama();
        getPanorama();
        createPanorama();
        updatePanorama();
        deletePanorama();
        deleteAllPanorama();
    }


}