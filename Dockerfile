# FROM maven:3.8.2-ibmjava
#
# # Working directory
# ENV WORKDIR=/app/
#
# # Set working directory
# WORKDIR ${WORKDIR}
#
# COPY . ${WORKDIR}/

FROM openjdk:11
ADD target/asset-management-api.jar asset-management-api.jar
ENTRYPOINT ["java", "-jar","asset-management-api.jar"]
EXPOSE 8080
