package com.andreivan.rest.webservices.patrimonymanagement.assetcontrol;

import com.andreivan.rest.webservices.patrimonymanagement.asset.AssetCategory;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Date;
import java.util.List;

public interface AssetControlRepository extends MongoRepository<AssetControl, String> {
    List<AssetControl> findStringById(String id);

    List<AssetControl> findByUsername(String username, Sort sort);

    List<AssetControl> findByUsernameAndControlDateBetween(String username, Date startDate, Date endDate, Sort sort);

    List<AssetControl> findByUsernameAndCategory(String username, AssetCategory category, Sort sort);

    List<AssetControl> findByUsernameAndCategoryAndControlDateBetween(String username, AssetCategory category,
                                                                      Date startDate, Date endDate, Sort sort);
}
