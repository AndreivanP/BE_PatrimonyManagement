package com.patrimony.management.asset;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
public class AssetController {

    @Autowired
    private AssetRepository assetRepository;

    @GetMapping("users/{username}/assets")
    public List<Asset> getAllUserAssets(@PathVariable String username) {
        return assetRepository.findByUsername(username);
    }

    @PostMapping("users/{username}/assets")
    public ResponseEntity<Asset> createAsset(@PathVariable String username, @RequestBody Asset asset) {
        asset.setUsername(username);
        return new ResponseEntity<Asset>(assetRepository.save(asset), HttpStatus.CREATED);
    }
}
