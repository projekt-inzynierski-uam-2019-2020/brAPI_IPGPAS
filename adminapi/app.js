const express = require("express");
const bodyParser = require("body-parser");
const mongoose = require("mongoose");
const cors = require("cors");
const uuid = require("uuid");
const session = require("express-session");
const FileStore = require('session-file-store')(session);

const server = require("./routes/serverRoute");
const user = require("./routes/userRoute");

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
app.use(
  session({
    genid: req => {
      console.log("Inside the session middleware");
      console.log(req.sessionID);
      return uuid();
    },
    store: new FileStore(),
    secret: "replace this",
    resave: false,
    saveUninitialized: true
  })
);

app.use("/servers", server);
app.use("/users", user);

app.get("/", (req, res) => {
  console.log('Inside the homepage callback function')
  console.log(req.sessionID)
  res.send(`You hit home page!\n`)
});

app.listen(port, () => {
  console.log(`Server is running on port ${port}`);
});
