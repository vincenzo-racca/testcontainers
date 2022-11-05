package com.vincenzoracca.localstack.dao.impl;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.vincenzoracca.localstack.dao.UserDao;
import com.vincenzoracca.localstack.model.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Repository;

@Repository
@Slf4j
public class UserDynamoDBDao extends DynamoDBDao<User, String> implements UserDao {

    public UserDynamoDBDao(AmazonDynamoDB amazonDynamoDB, Environment env) {
        super(amazonDynamoDB, User.class, env);
    }

}
