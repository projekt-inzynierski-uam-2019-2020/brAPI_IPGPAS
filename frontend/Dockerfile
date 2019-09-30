FROM node:10-alpine
COPY . /root/brapi-frontend-app
WORKDIR /root/brapi-frontend-app
RUN npm install -g npm
RUN npm install -g @angular/cli
RUN npm install
RUN ng build

FROM httpd:2.4
COPY --from=0 /root/brapi-frontend-app/dist/frontend/. /usr/local/apache2/htdocs
EXPOSE 80
CMD ["httpd-foreground"]