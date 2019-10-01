FROM postgres:11-alpine
USER postgres
COPY *.sh /docker-entrypoint-initdb.d/
COPY *.psql /home/postgres/sql/
EXPOSE 5432
CMD ["postgres"]