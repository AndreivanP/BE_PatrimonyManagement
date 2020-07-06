package com.patrimony.management.asset;

import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.List;

public interface AssetRepository  extends MongoRepository<Asset, String> {
    List<Asset> findByUsername(String username);
    List<Asset> findStringById(String id);
}
