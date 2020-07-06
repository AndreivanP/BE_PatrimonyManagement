package com.patrimony.management.assetcontrol;

import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.Date;
import java.util.List;

public interface AssetControlRepository extends MongoRepository<AssetControl, String> {
    List<AssetControl> findStringById(String id);
    List<AssetControl> findByAssetId(String assetId, Sort sort);
    List<AssetControl> findDateByControlDate(Date controlDate);
}
