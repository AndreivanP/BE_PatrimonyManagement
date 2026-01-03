package com.andreivan.rest.webservices.patrimonymanagement.asset;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class AssetTransactionService {

    @Autowired
    private AssetTransactionRepository assetTransactionRepository;

    public AssetTransaction recordTransaction(String assetId, String username, double amount, AssetTransactionType type, AssetCategory category) {
        AssetTransaction transaction = new AssetTransaction();
        transaction.setAssetId(assetId);
        transaction.setUsername(username);
        transaction.setAmount(amount);
        transaction.setTransactionDate(new Date());
        transaction.setType(type);
        transaction.setCategory(category);
        return assetTransactionRepository.save(transaction);
    }
}
