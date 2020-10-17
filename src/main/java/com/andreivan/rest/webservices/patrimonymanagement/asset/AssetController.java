package com.andreivan.rest.webservices.patrimonymanagement.asset;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class AssetController {

    @Autowired
    private AssetRepository assetRepository;

    @GetMapping("users/{username}/assets")
    public List<Asset> getAllAssets(@PathVariable String username) {
        return assetRepository.findByUsername(username);
    }

    @GetMapping("users/{username}/assets/current-total")
    public double getTotalValue(@PathVariable String username) {
        List<Asset> assets = assetRepository.findByUsername(username);
        double total = 0;
        for (Asset asset : assets) {
            total += asset.getCurrent_value();
        }
        return total;
    }

    @GetMapping("users/{username}/assets/{id}")
    public ResponseEntity<Asset> getAsset(@PathVariable String username, @PathVariable String id) {
        List<Asset> assetO = assetRepository.findStringById(id);
        if(assetO.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(assetO.get(0), HttpStatus.OK);
        }
    }

    @PostMapping("users/{username}/assets")
    public ResponseEntity<Asset> createAsset(@PathVariable String username, @RequestBody Asset asset) {
        asset.setUsername(username);
        return new ResponseEntity<>(assetRepository.save(asset), HttpStatus.CREATED);
    }

    @DeleteMapping("users/{username}/assets/{id}")
    public ResponseEntity<Asset> deleteAsset(@PathVariable String username, @PathVariable String id) {
        assetRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("users/{username}/assets/{id}")
    public ResponseEntity<Asset> updateAsset(@PathVariable String username, @PathVariable(value="id") String id, @RequestBody Asset asset) {
        asset.setUsername(username);
        return new ResponseEntity<>(assetRepository.save(asset), HttpStatus.OK);
    }
}
