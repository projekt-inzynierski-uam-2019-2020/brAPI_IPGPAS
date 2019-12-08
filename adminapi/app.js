const express = require("express");
const bodyParser = require("body-parser");
const mongoose = require("mongoose");
const cors = require("cors");
const uuid = require("uuid");
const session = require("express-session");
const FileStore = require("session-file-store")(session);
const passport = require("passport");
const LocalStrategy = require("passport-local").Strategy;

const server = require("./routes/serverRoute");
const user = require("./routes/userRoute");

let port = 3000;
let mongoUrl = "mongodb://admin:admin@localhost:27017/adminapi-db";

const app = express();

mongoose.connect(mongoUrl, {
  useNewUrlParser: true,
  useUnifiedTopology: true,
  useFindAndModify: false
});

mongoose.Promise = global.Promise;

let db = mongoose.connection;

const users = [{ id: "2f24vvg", email: "test@test.com", password: "password" }];

// configure passport.js to use the local strategy
passport.use(
  new LocalStrategy({ usernameField: "email" }, (email, password, done) => {
    console.log("Inside local strategy callback");
    // here is where you make a call to the database
    // to find the user based on their username or email address
    // for now, we'll just pretend we found that it was users[0]
    const user = users[0];
    if (email === user.email && password === user.password) {
      console.log("Local strategy returned true");
      return done(null, user);
    }
  })
);

// tell passport how to serialize the user
passport.serializeUser((user, done) => {
  console.log(
    "Inside serializeUser callback. User id is save to the session file store here"
  );
  done(null, user.id);
});

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
app.use(passport.initialize());
app.use(passport.session());

app.use("/servers", server);
app.use("/users", user);

app.get("/", (req, res) => {
  console.log("Inside the homepage callback function");
  console.log(req.sessionID);
  res.send(`You hit home page!\n`);
});

app.get("/login", (req, res) => {
  console.log("Inside GET /login callback function");
  console.log(req.sessionID);
  res.send(`You got the login page!\n`);
});

app.post("/login", (req, res, next) => {
  console.log("Inside POST /login callback");
  passport.authenticate("local", (err, user, info) => {
    console.log("Inside passport.authenticate() callback");
    console.log(
      `req.session.passport: ${JSON.stringify(req.session.passport)}`
    );
    console.log(`req.user: ${JSON.stringify(req.user)}`);
    req.login(user, err => {
      console.log("Inside req.login() callback");
      console.log(
        `req.session.passport: ${JSON.stringify(req.session.passport)}`
      );
      console.log(`req.user: ${JSON.stringify(req.user)}`);
      return res.send("You were authenticated & logged in!\n");
    });
  })(req, res, next);
});

app.listen(port, () => {
  console.log(`Server is running on port ${port}`);
});
