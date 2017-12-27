package de.andreichenko.panonoapi.controller;

import de.andreichenko.panonoapi.model.Panorama;
import de.andreichenko.panonoapi.service.PanoramaService;
import de.andreichenko.panonoapi.util.CustomError;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping ("/api")
public class RestApiController {

    public static final Logger logger = LoggerFactory.getLogger(RestApiController.class);

    @Autowired
    PanoramaService panoramaService;

    // - statistics

    @RequestMapping(value = "/panorama/", method = RequestMethod.GET)
    public ResponseEntity<List<Panorama>> listAllPanorama(){
            List<Panorama> panoramas = panoramaService.findAllPanorama();

            if (panoramas.isEmpty()){
                return new ResponseEntity(HttpStatus.NO_CONTENT);
            }

        return new ResponseEntity<List<Panorama>>(panoramas, HttpStatus.OK);
    }

    // -- statistic by count

    @RequestMapping(value = "/panorama/{count}", method = RequestMethod.GET)
    public ResponseEntity<?> getPanorama(@PathVariable("count") long count){
        logger.info("Fetching Panorama with timestamp {}", count);
        Panorama panorama = panoramaService.findByTimeStamp(count);
        if (panorama == null){
            logger.error("Panorama with timestamp {} not found", count);
            return  new ResponseEntity(new CustomError("Panorama with timestamp" + count
                    + "not found"), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Panorama>(panorama, HttpStatus.OK);
    }

    // - Create Panorama..

    @RequestMapping(value = "/panorama/", method = RequestMethod.GET)
    public ResponseEntity<?> createPanorama(@RequestBody Panorama panorama, UriComponentsBuilder ucBuilder){
        logger.info("creating Panorama: {}", panorama);

        if (panoramaService.isExist(panorama)){
            logger.error("Unable to create. A Panorama with count {} already exist", panorama.getCount());
            return  new ResponseEntity(new CustomError("Unnable to create. A Panorama with count" +
                    panorama.getCount() + "already exist."), HttpStatus.CONFLICT);
        }
        panoramaService.savePanorama(panorama);

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/api/panorama/{count}").buildAndExpand(panorama.getCount()).toUri());
        return  new ResponseEntity<String>(headers, HttpStatus.CREATED);

    }

   // -- update Panorama

    @RequestMapping(value = "/panorama/{count}", method = RequestMethod.PUT)
    public ResponseEntity<?> updatePanorama(@PathVariable("count") long count, @RequestBody Panorama panorama){
      logger.info("Updating User with count{}", count);

      Panorama currentPanorama = panoramaService.findByCount(count);

      if (currentPanorama == null){
          logger.error("Unnable to update, Panorama with count {} not found.", count);
          return new ResponseEntity(new CustomError("Unnable to update. Panorama with count" +
           count + "not found"), HttpStatus.NOT_FOUND);
      }
      currentPanorama.setCount(panorama.getCount());
      currentPanorama.setAvg(panorama.getAvg());
      currentPanorama.setMax(panorama.getMax());
      currentPanorama.setMin(panorama.getMin());
      currentPanorama.setSum(panorama.getSum());
      currentPanorama.setTimeStamp(panorama.getTimeStamp());

      panoramaService.updatePanorama(currentPanorama);
      return  new ResponseEntity<Panorama>(currentPanorama, HttpStatus.OK);
    }

    // delete Panorama

    @RequestMapping(value = "/panorama/{count}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deletePanorama(@PathVariable("count") long count){
        logger.info("Fetching and deleting Pnorama with count {}", count);

        Panorama panorama = panoramaService.findByCount(count);
        if (panorama == null){
            logger.error("Unnable to delete. Panorama with count {} not found", count);
            return new ResponseEntity(new CustomError("Unnable to delete. Panorama with count" + count +
            "not found"), HttpStatus.NOT_FOUND);
        }
        panoramaService.deletePanoramaByCount(count);
        return  new ResponseEntity<Panorama>(HttpStatus.NO_CONTENT);
    }

    //-- delete all panorama

    @RequestMapping(value = "/panorama/", method = RequestMethod.DELETE)
    public ResponseEntity<Panorama> deleteAllPanorama(){
        logger.info("Deleting all Panorama");

        panoramaService.deletePanorama();
        return new ResponseEntity<Panorama>(HttpStatus.NO_CONTENT);
    }

}
