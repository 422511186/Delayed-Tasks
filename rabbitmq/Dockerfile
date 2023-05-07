FROM rabbitmq:3.8-management

# 添加插件到指定目录 可按照此方式自行扩展其他插件
ADD ./rabbitmq_delayed_message_exchange.ez /plugins/

# 开启插件
RUN rabbitmq-plugins enable rabbitmq_management
RUN rabbitmq-plugins enable rabbitmq_delayed_message_exchange

# 设置默认用户名和密码
ENV RABBITMQ_DEFAULT_USER=root
ENV RABBITMQ_DEFAULT_PASS=123123

# 暴露 RabbitMQ 默认端口
EXPOSE 5672
EXPOSE 15672