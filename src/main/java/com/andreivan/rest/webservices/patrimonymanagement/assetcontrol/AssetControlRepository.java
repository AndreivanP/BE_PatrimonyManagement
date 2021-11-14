package com.andreivan.rest.webservices.patrimonymanagement.assetcontrol;

import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.Date;
import java.util.List;

public interface AssetControlRepository extends MongoRepository<AssetControl, String> {
    List<AssetControl> findStringById(String id);
    List<AssetControl> findByUsername(String username);
    List<AssetControl> findByControlDateBetween(Date startDate, Date endDate);
}