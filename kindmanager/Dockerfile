FROM registry.cn-shenzhen.aliyuncs.com/aa1435889856/dota2:base
COPY target/kindmanager.jar /usr/kindmanager.jar
#¹«²¼tomcatµÄ¶Ë¿Ú
ENV DUBBO_IP_TO_REGISTRY 106.12.203.66
ENV DUBBO_PORT_TO_REGISTRY 8880
ENV JAVA_HOME=/javasdk
ENV JRE_HOME=/javasdk/jre
ENV PATH $JAVA_HOME/bin:$PATH
  EXPOSE 8880

ENTRYPOINT ["java","-jar","-Duser.timezone=GMT+8","/usr/kindmanager.jar"]
