package com.andreivan.rest.webservices.patrimonymanagement.assetcontrol;

import com.andreivan.rest.webservices.patrimonymanagement.asset.Asset;
import com.andreivan.rest.webservices.patrimonymanagement.asset.AssetController;
import com.andreivan.rest.webservices.patrimonymanagement.asset.AssetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class AssetControlController {

    @Autowired
    private AssetControlRepository assetControlRepository;
    @Autowired
    private AssetRepository assetRepository;

    public double getTotalValue2(String username) {
        List<Asset> assets = assetRepository.findByUsername(username);
        double total = 0;
        for (Asset asset : assets) {
            total += asset.getCurrent_value();
        }
        return total;
    }

    @PostMapping("user/{username}/assets-control")
    public ResponseEntity<AssetControl> createAssetControl(@PathVariable String username) {
            AssetControl assetControl = new AssetControl();
            assetControl.setControlDate(new Date());
            assetControl.setCurrentValue(getTotalValue2(username));
            assetControl.setUsername(username);

        return new ResponseEntity<>(assetControlRepository.save(assetControl), HttpStatus.CREATED);
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
