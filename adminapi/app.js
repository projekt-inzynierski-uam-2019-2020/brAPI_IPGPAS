const express = require("express");
const bodyParser = require("body-parser");
const mongoose = require("mongoose");
const cors = require("cors");

const server = require("./routes/serverRoute");
const user = require("./routes/userRoute")

let port = 3000;
let mongoUrl = "mongodb://admin:admin@db.admin.local:27017/adminapi-db";

const app = express();

mongoose.connect(mongoUrl, {
  useNewUrlParser: true,
  useUnifiedTopology: true,
  useFindAndModify: false
});

mongoose.Promise = global.Promise;

let db = mongoose.connection;

app.use(bodyParser.json());
app.use(bodyParser.urlencoded({ extended: false }));
app.use(cors());

app.use("/servers", server);
app.use("/users", user);

app.listen(port, () => {
  console.log(`Server is running on port ${port}`);
});
