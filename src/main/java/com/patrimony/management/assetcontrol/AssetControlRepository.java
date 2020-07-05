package com.patrimony.management.assetcontrol;

import com.patrimony.management.asset.Asset;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AssetControlRepository extends MongoRepository<AssetControl, String> {
}
