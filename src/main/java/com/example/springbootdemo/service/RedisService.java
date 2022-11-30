package com.example.springbootdemo.service;
import com.example.springbootdemo.model.OrderModel;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.lettuce.core.RedisClient;
import io.lettuce.core.api.StatefulRedisConnection;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Value;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.ArrayList;
import java.util.List;


@Service
public class RedisService {

    @Value("${com.example.springbootdemo.service.redis.ttl}")
    private Integer ttl;

    @Value("${com.example.springbootdemo.service.redis.url}")
    private String url;

    private RedisClient redisClient;

    private StatefulRedisConnection<String, String> statefulRedisConnection;

    //
    public String get(String key) {
        return statefulRedisConnection.sync().get(key);
    }

    public void set(String key, String value) {
        statefulRedisConnection.sync().set(key,value);
    }


    //object
    public void setList(String key, OrderModel orderModel) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        statefulRedisConnection.sync().lpush(key, objectMapper.writeValueAsString(orderModel));
    }

    public List<OrderModel> getOrders(String key, long start, long stop) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();

        List<OrderModel> orderModelList = new ArrayList<>();

        System.out.println("Entering loop...");
        for(String jsonObj : statefulRedisConnection.sync().lrange(key, start, stop)) {
            System.out.println(jsonObj);
            orderModelList.add(objectMapper.readValue(jsonObj, OrderModel.class));
        }

       return orderModelList;
    }

    @PostConstruct
    private void init(){
        redisClient = RedisClient.create(url);
        statefulRedisConnection = redisClient.connect();
    }

    @PreDestroy
    private void destroy(){
        if(statefulRedisConnection != null){
            statefulRedisConnection.close();
        }

        if(redisClient != null){
            redisClient.shutdown();
        }

    }
    public void listPush(String key, String value){
        statefulRedisConnection.sync().lpush(key,value);
    }
    public List listRange(String key, long start, long stop){
        return statefulRedisConnection.sync().lrange(key, start, stop);
    }

}

