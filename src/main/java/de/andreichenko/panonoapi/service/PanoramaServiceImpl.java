package de.andreichenko.panonoapi.service;


import de.andreichenko.panonoapi.model.Panorama;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@Service("panoramaService")
public class PanoramaServiceImpl implements PanoramaService{


    public static final AtomicLong counter = new AtomicLong();

    public static List<Panorama> panoramaList;

    static {
        panoramaList = populatePanoramaList();
    }


    @Override
    public Panorama findByCount(long count) {

        for(Panorama panorama : panoramaList){
            if (panorama.getCount() == count){
                return panorama;
            }
        }

        return null;
    }

    @Override
    public Panorama findByTimeStamp(long timeStamp) {
        for (Panorama panorama : panoramaList){
            if (panorama.getTimeStamp() == timeStamp){
                return panorama;
            }
        }
        return null;
    }

    @Override
    public void savePanorama(Panorama panorama) {
            panorama.setCount(counter.incrementAndGet());
            panoramaList.add(panorama);
    }

    @Override
    public void deletePanorama() {
        panoramaList.clear();
    }

    @Override
    public void updatePanorama(Panorama panorama) {
        int index = panoramaList.indexOf(panorama);
        panoramaList.set(index, panorama);
    }

    @Override
    public void deletePanoramaByCount(long count) {
        for(Iterator<Panorama> iterator = panoramaList.iterator(); iterator.hasNext();){
            Panorama panorama = iterator.next();
            if (panorama.getCount() == count){
                iterator.remove();
            }
        }
    }

    @Override
    public List<Panorama> findAllPanorama() {
        return panoramaList;
    }

    @Override
    public boolean isExist(Panorama panorama) {
        return findByCount(panorama.getCount())!=null;
    }


    private static List<Panorama> populatePanoramaList(){
        List<Panorama> panoramas = new ArrayList<Panorama>();
        panoramas.add(new Panorama(counter.incrementAndGet(),12890212, 3, 1.0, 2, 1));
        panoramas.add(new Panorama(counter.incrementAndGet(), 18900613,2,2.0,3,1));
        return  panoramas;
    }
}
