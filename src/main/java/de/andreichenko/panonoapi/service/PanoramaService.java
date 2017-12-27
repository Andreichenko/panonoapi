package de.andreichenko.panonoapi.service;


import de.andreichenko.panonoapi.model.Panorama;

import java.util.List;

public interface PanoramaService {

 Panorama findByCount(long count);

 Panorama findByTimeStamp(long timeStamp);

 void savePanorama(Panorama panorama);

 void deletePanorama();

 void updatePanorama(Panorama panorama);

 void deletePanoramaByCount(long count);

 List<Panorama> findAllPanorama();

 boolean isExist(Panorama panorama);

}
