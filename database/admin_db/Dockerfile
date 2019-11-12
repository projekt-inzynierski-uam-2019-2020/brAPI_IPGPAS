FROM mongo
EXPOSE 27017
COPY ./init-mongo.js /docker-entrypoint-initdb.d/
ENV MONGO_INITDB_DATABASE=adminapi-db
ENV MONGO_INITDB_ROOT_USERNAME=admin
ENV MONGO_INITDB_ROOT_PASSWORD=admin
CMD ["mongod"]