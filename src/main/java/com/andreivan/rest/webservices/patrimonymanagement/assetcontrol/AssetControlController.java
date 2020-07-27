package com.andreivan.rest.webservices.patrimonymanagement.assetcontrol;

import com.andreivan.rest.webservices.patrimonymanagement.asset.Asset;
import com.andreivan.rest.webservices.patrimonymanagement.asset.AssetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class AssetControlController {

    @Autowired
    private AssetControlRepository assetControlRepository;
    @Autowired
    private AssetRepository assetRepository;

    @PostMapping("/assets-control")
    public ResponseEntity<AssetControl> createAssetControl(@RequestBody AssetControl assetControl) {
        List<Asset> assetID = assetRepository.findStringById(assetControl.getAssetId());
        List<AssetControl> controlDate = assetControlRepository.findDateByControlDate(assetControl.getControlDate());
        if(assetID.isEmpty() || !controlDate.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity<>(assetControlRepository.save(assetControl), HttpStatus.CREATED);
        }
    }

    @DeleteMapping("/assets-control/{id}")
    public ResponseEntity<AssetControl> deleteAssetControl(@PathVariable String id) {
        List<AssetControl> assetControl = assetControlRepository.findStringById(id);
        if(assetControl.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            assetControlRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }
    }

    @GetMapping("/assets-control")
    public ResponseEntity<List<AssetControl>> getAllAssetsControl() {
        return new ResponseEntity<>(assetControlRepository.findAll(), HttpStatus.OK);
    }

    @GetMapping("/assets-control/asset/{id}")
    public ResponseEntity<List<AssetControl>> getAssetsControlForSpecificAsset(@PathVariable String id) {
        List<Asset> asset = assetRepository.findStringById(id);
        if(asset.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(assetControlRepository.findByAssetId(id, Sort.by(Sort.Direction.DESC, "controlDate")), HttpStatus.OK);
        }
    }

    @GetMapping("/assets-control/{id}")
    public ResponseEntity<AssetControl> getAssetControl(@PathVariable String id) {
        List<AssetControl> assetControl = assetControlRepository.findStringById(id);
        if(assetControl.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(assetControl.get(0), HttpStatus.OK);
        }
    }

    @PutMapping("/assets-control/{id}")
    public ResponseEntity<AssetControl> updateAssetControl (@PathVariable String id, @RequestBody AssetControl assetControl) {
        List<AssetControl> assetControlList = assetControlRepository.findStringById(id);
        if(assetControlList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(assetControlRepository.save(assetControl), HttpStatus.OK);
        }
    }
}
