FROM node:10-alpine
EXPOSE 3000
COPY . /opt/adminapi
WORKDIR /opt/adminapi
RUN npm install
CMD ["node", "app.js"]