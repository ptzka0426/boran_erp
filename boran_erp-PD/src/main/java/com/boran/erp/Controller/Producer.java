package com.boran.erp.Controller;

import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import redis.clients.jedis.Jedis;
/**
 * @Author LT
 * @create 2021-08-16 16:11
 */
public class Producer {
    public static void main(String[] args) throws MQClientException {
        //创建生产者并指定组
        DefaultMQProducer producer = new DefaultMQProducer("my-group");
        //指定服务地址
        producer.setNamesrvAddr("8.135.54.170:9876");
        //创建生产者实例
        producer.setInstanceName("producer");
        //启动生成者
        producer.start();
        try {
            for (int i = 0; i < 10; i++) {
              /*  Thread.sleep(1000); */// 每秒发送一次MQ

                Message msg = new Message("my-topic", // topic 主题名称
                        "TagA", // tag 临时值
                        ("message-"+i).getBytes()// body 内容
                );

                SendResult sendResult = producer.send(msg);
                //打印消息的完整信息
                System.out.println(sendResult.toString());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        //发送完所有信息停掉生产者
      /*  producer.shutdown();*/
    }
}
