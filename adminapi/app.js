const express = require("express");
const logger = require("morgan");
const servers = require("./routes/servers");
const users = require("./routes/users");
const bodyParser = require("body-parser");
const mongoose = require("./config/database");
const cors = require('cors')
var jwt = require("jsonwebtoken");
const app = express();

app.set("secretKey", "nodeRestApi");

mongoose.connection.on(
  "error",
  console.error.bind(console, "MongoDB connection error:")
);

app.use(bodyParser.urlencoded({ extended: false }));
app.use(cors());

app.get("/", function(req, res) {
  res.send("PlantPheno Analytics Admin API");
});

app.use("/users", users);
app.use("/servers", validateUser, servers);

function validateUser(req, res, next) {
  jwt.verify(req.headers["x-access-token"], req.app.get("secretKey"), function(
    err,
    decoded
  ) {
    if (err) {
      res.json({ status: "error", message: err.message, data: null });
    } else {
      req.body.userId = decoded.id;
      next();
    }
  });
}

app.use(function(req, res, next) {
  let err = new Error("Not Found");
  err.status = 404;
  next(err);
});

app.use(function(err, req, res, next) {
  console.log(err);
  if (err.status === 404) {
    res.status(404);
  } else {
    res.status(500);
  }
});

app.listen(3000, function() {
  console.log("Node server listening on port 3000.");
});
