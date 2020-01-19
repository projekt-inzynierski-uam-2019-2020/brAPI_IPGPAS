const express = require("express");
const logger = require("morgan");
const bodyParser = require("body-parser");
const mongoose = require("./config/database");
const users = require("./routes/users");
const servers = require("./routes/servers");
const cors = require("cors");
var jwt = require("jsonwebtoken");

const app = express();

function validateUser(req, res, next) {
  jwt.verify(req.headers["x-access-token"], req.app.get("secretKey"), function(
    err,
    decoded
  ) {
    if (err) {
      res.status(401);
    } else {
      req.body.id = decoded.id;
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
    res.status(404).json({ message: "Not found" });
  } else {
    res.status(500).json({ message: "Something looks wrong :( !!!" });
  }
});

app.set("secretKey", "nodeRestApi");

app.use(cors());
app.use(logger("dev"));
app.use(bodyParser.json());
app.use(bodyParser.urlencoded({ extended: false }));

app.use("/users", users);
app.use("/servers", validateUser, servers);

mongoose.connection.on(
  "error",
  console.error.bind(console, "MongoDB connection error:")
);

app.get("/", function(req, res) {
  res.send("PlantPheno Analytics Admin API");
});

app.listen(3000, () => {
  console.log("Node server listening on port 3000");
});
