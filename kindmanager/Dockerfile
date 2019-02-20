FROM registry.cn-shenzhen.aliyuncs.com/aa1435889856/dota2:base
COPY target/kindmanager.jar /usr/kindmanager.jar
#¹«²¼tomcatµÄ¶Ë¿Ú

ENV JAVA_HOME=/javasdk
ENV JRE_HOME=/javasdk/jre
ENV PATH $JAVA_HOME/bin:$PATH
  EXPOSE 8888

ENTRYPOINT ["java","-jar","-Duser.timezone=GMT+8","/usr/kindmanager.jar"]
