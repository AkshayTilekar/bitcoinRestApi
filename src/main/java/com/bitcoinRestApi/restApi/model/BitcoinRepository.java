package com.bitcoinRestApi.restApi.model;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

public interface BitcoinRepository extends MongoRepository<Bitcoin, String> {

}
