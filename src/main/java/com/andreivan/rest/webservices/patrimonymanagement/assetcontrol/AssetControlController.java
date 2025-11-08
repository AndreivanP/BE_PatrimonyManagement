package com.andreivan.rest.webservices.patrimonymanagement.assetcontrol;

import com.andreivan.rest.webservices.patrimonymanagement.asset.Asset;
import com.andreivan.rest.webservices.patrimonymanagement.asset.AssetCategory;
import com.andreivan.rest.webservices.patrimonymanagement.asset.AssetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
public class AssetControlController {

    @Autowired
    private AssetControlRepository assetControlRepository;
    @Autowired
    private AssetRepository assetRepository;

    public double getTotalValue2(String username) {
        return calculateTotalValue(username, null);
    }

    @PostMapping("users/{username}/assets-control")
    public ResponseEntity<AssetControl> createAssetControl(@PathVariable String username,
                                                           @RequestParam(value = "category", required = false) AssetCategory category) {
        AssetControl assetControl = new AssetControl();
        assetControl.setControlDate(new Date());
        assetControl.setCurrentTotalValue(calculateTotalValue(username, category));
        assetControl.setUsername(username);
        assetControl.setCategory(category);
        return new ResponseEntity<>(assetControlRepository.save(assetControl), HttpStatus.CREATED);
    }

    @PostMapping("users/{username}/assets-control-custom")
    public ResponseEntity<AssetControl> createAssetControlCustom(@RequestBody AssetControl assetControl, @PathVariable String username) {
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

    @GetMapping("users/{username}/assets-control")
    public ResponseEntity<List<AssetControl>> getAllAssetsControl(@PathVariable String username,
                                                                  @RequestParam(required = false, name = "since") Date since,
                                                                  @RequestParam(required = false, name = "till") Date till,
                                                                  @RequestParam(required = false, name = "category") AssetCategory category) {
        Sort sort = Sort.by(Sort.Direction.DESC, "controlDate");
        if(category != null && since != null && till != null) {
            return new ResponseEntity<>(assetControlRepository.findByUsernameAndCategoryAndControlDateBetween(username, category, since, till, sort), HttpStatus.OK);
        } else if(category != null) {
            return new ResponseEntity<>(assetControlRepository.findByUsernameAndCategory(username, category, sort), HttpStatus.OK);
        } else if(since != null && till != null) {
            return new ResponseEntity<>(assetControlRepository.findByUsernameAndControlDateBetween(username, since, till, sort), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(assetControlRepository.findByUsername(username, sort), HttpStatus.OK);
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

    private double calculateTotalValue(String username, AssetCategory category) {
        List<Asset> assets = assetRepository.findByUsername(username);
        double total = 0;
        for (Asset asset : assets) {
            if (category == null || category.equals(asset.getCategory())) {
                total += asset.getCurrent_value();
            }
        }
        return total;
    }
}
