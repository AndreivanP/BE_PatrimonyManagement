package com.andreivan.rest.webservices.patrimonymanagement.asset;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface AssetTransactionRepository extends MongoRepository<AssetTransaction, String> {
    List<AssetTransaction> findByAssetIdOrderByTransactionDateDesc(String assetId);
}
