package com.patrimony.management.assetcontrol;

import com.patrimony.management.asset.Asset;
import com.patrimony.management.asset.AssetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AssetControlController {

    @Autowired
    private AssetControlRepository assetControlRepository;
    @Autowired
    private AssetRepository assetRepository;

    @PostMapping("/assets-control")
    public ResponseEntity<AssetControl> createAssetControl(@RequestBody AssetControl assetControl) {
        List<Asset> asset = assetRepository.findStringById(assetControl.getAsset_id());
        if(asset.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity<>(assetControlRepository.save(assetControl), HttpStatus.CREATED);
        }
    }
}
