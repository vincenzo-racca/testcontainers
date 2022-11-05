package com.vincenzoracca.localstack.dao.impl;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.vincenzoracca.localstack.dao.CarDao;
import com.vincenzoracca.localstack.model.Car;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Repository;

@Repository
public class CarDynamoDBDao extends DynamoDBDao<Car, String> implements CarDao {

    public CarDynamoDBDao(AmazonDynamoDB amazonDynamoDB, Environment env) {
        super(amazonDynamoDB, Car.class, env);
    }

}
