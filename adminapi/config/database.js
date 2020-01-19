const mongoose = require("mongoose");
const mongoDB = "mongodb://admin:admin@localhost:27017/adminapi-db";

mongoose.connect(mongoDB, {
  useNewUrlParser: true,
  useUnifiedTopology: true,
  useFindAndModify: false
});
mongoose.Promise = global.Promise;

module.exports = mongoose;
