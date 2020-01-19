FROM node:10-buster-slim
EXPOSE 3000
COPY . /opt/adminapi
WORKDIR /opt/adminapi
RUN npm install
CMD ["node", "app.js"]