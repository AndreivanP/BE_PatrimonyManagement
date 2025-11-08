package com.andreivan.rest.webservices.patrimonymanagement.asset;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "*")
@RestController
public class AssetController {

    @Autowired
    private AssetRepository assetRepository;

    @GetMapping("users/{username}/assets")
    public List<Asset> getAllAssets(@PathVariable String username) {
        return assetRepository.findByUsername(username);
    }

    @GetMapping("users/{username}/assets/current-total")
    public Map<String, Double> getTotalValue(@PathVariable String username) {
        HashMap<String, Double> map = new HashMap<>();
        List<Asset> assets = assetRepository.findByUsername(username);
        double total = 0;
        double totalVariableIncome = 0;
        for (Asset asset : assets) {
            total += asset.getCurrent_value();
            // Variable income categories: Ações and Criptomoedas
            if(asset.getCategory() == AssetCategory.ACOES || asset.getCategory() == AssetCategory.CRIPTOMOEDAS){
                totalVariableIncome += asset.getCurrent_value();
            }
        }
        DecimalFormat df = new DecimalFormat("#.00");
        map.put("current_total", Double.valueOf(df.format(total)));
        map.put("variable_income_total", Double.valueOf(df.format(totalVariableIncome)));
        map.put("variable_income_percent", Double.valueOf(df.format(totalVariableIncome / total * 100)));
        return map;
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
    public ResponseEntity<Asset> createAsset(@PathVariable String username, @Valid @RequestBody Asset asset) {
        asset.setUsername(username);
        return new ResponseEntity<>(assetRepository.save(asset), HttpStatus.CREATED);
    }

    @DeleteMapping("users/{username}/assets/{id}")
    public ResponseEntity<Asset> deleteAsset(@PathVariable String username, @PathVariable String id) {
        assetRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("users/{username}/assets/{id}")
    public ResponseEntity<Asset> updateAsset(@PathVariable String username, @PathVariable(value="id") String id, @Valid @RequestBody Asset asset) {
        asset.setUsername(username);
        return new ResponseEntity<>(assetRepository.save(asset), HttpStatus.OK);
    }
}
