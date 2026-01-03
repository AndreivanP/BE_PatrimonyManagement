package com.andreivan.rest.webservices.patrimonymanagement.asset;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import java.text.DecimalFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "*")
@RestController
public class AssetController {

    @Autowired
    private AssetRepository assetRepository;
    @Autowired
    private AssetTransactionService assetTransactionService;
    @Autowired
    private AssetTransactionRepository assetTransactionRepository;

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

    @PostMapping("users/{username}/assets/{id}/top-up")
    public ResponseEntity<?> topUpAsset(@PathVariable String username,
                                        @PathVariable(value = "id") String id,
                                        @Valid @RequestBody AssetTransactionRequest request) {
        Asset asset = findAssetById(id);
        if(asset == null || !asset.getUsername().equals(username)) {
            return buildErrorResponse(HttpStatus.NOT_FOUND, "Asset not found");
        }
        asset.setCurrent_value(asset.getCurrent_value() + request.getAmount());
        assetRepository.save(asset);
        AssetTransaction transaction = assetTransactionService.recordTransaction(asset.getId(), username,
                request.getAmount(), AssetTransactionType.TOP_UP, asset.getCategory());
        return new ResponseEntity<>(transaction, HttpStatus.CREATED);
    }

    @PostMapping("users/{username}/assets/{id}/withdraw")
    public ResponseEntity<?> withdrawFromAsset(@PathVariable String username,
                                               @PathVariable(value = "id") String id,
                                               @Valid @RequestBody AssetTransactionRequest request) {
        Asset asset = findAssetById(id);
        if(asset == null || !asset.getUsername().equals(username)) {
            return buildErrorResponse(HttpStatus.NOT_FOUND, "Asset not found");
        }
        if(request.getAmount() > asset.getCurrent_value()) {
            return buildErrorResponse(HttpStatus.BAD_REQUEST, "Withdrawal amount exceeds current asset value");
        }
        asset.setCurrent_value(asset.getCurrent_value() - request.getAmount());
        assetRepository.save(asset);
        AssetTransaction transaction = assetTransactionService.recordTransaction(asset.getId(), username,
                request.getAmount(), AssetTransactionType.WITHDRAW, asset.getCategory());
        return new ResponseEntity<>(transaction, HttpStatus.CREATED);
    }

    private Asset findAssetById(String id) {
        List<Asset> assets = assetRepository.findStringById(id);
        if(assets.isEmpty()) {
            return null;
        }
        return assets.get(0);
    }

    private ResponseEntity<Map<String, String>> buildErrorResponse(HttpStatus status, String message) {
        Map<String, String> response = new HashMap<>();
        response.put("message", message);
        return new ResponseEntity<>(response, status);
    }

    @GetMapping("users/{username}/assets/{assetId}/transactions")
    public ResponseEntity<List<AssetTransaction>> getAssetTransactions(@PathVariable String username,
                                                                       @PathVariable String assetId,
                                                                       @RequestParam(required = false) Integer month,
                                                                       @RequestParam(required = false) Integer year) {
        Asset asset = findAssetById(assetId);
        if(asset == null || !asset.getUsername().equals(username)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        List<AssetTransaction> transactions;
        if(month != null && year != null) {
            if(month < 1 || month > 12) {
                return buildErrorResponse(HttpStatus.BAD_REQUEST, "Month must be between 1 and 12");
            }
            Date start = TransactionDateRangeUtil.startOfMonth(year, month);
            Date end = TransactionDateRangeUtil.endOfMonth(year, month);
            transactions = assetTransactionRepository.findByAssetIdAndTransactionDateBetweenOrderByTransactionDateDesc(assetId, start, end);
        } else {
            transactions = assetTransactionRepository.findByAssetIdOrderByTransactionDateDesc(assetId);
        }
        return new ResponseEntity<>(transactions, HttpStatus.OK);
    }

    @GetMapping("users/{username}/transactions")
    public ResponseEntity<List<AssetTransaction>> getTransactionsByCategory(@PathVariable String username,
                                                                            @RequestParam(required = false) Integer month,
                                                                            @RequestParam(required = false) Integer year) {
        List<AssetTransaction> transactions;
        if(month != null && year != null) {
            if(month < 1 || month > 12) {
                return buildErrorResponse(HttpStatus.BAD_REQUEST, "Month must be between 1 and 12");
            }
            Date start = TransactionDateRangeUtil.startOfMonth(year, month);
            Date end = TransactionDateRangeUtil.endOfMonth(year, month);
            transactions = assetTransactionRepository.findByUsernameAndTransactionDateBetweenOrderByTransactionDateDesc(username, start, end);
        } else {
            transactions = assetTransactionRepository.findByUsernameOrderByTransactionDateDesc(username);
        }
        return new ResponseEntity<>(transactions, HttpStatus.OK);
    }
}
