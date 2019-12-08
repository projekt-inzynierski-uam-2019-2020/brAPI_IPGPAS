const User = require("../models/userModel");

exports.get_users = (req, res, next) => {
  User.find({}, (err, servers) => {
    if (err) {
      return next(err);
    }
    res.send(servers);
  });
};

exports.get_user = (req, res, next) => {
  User.findById(req.params.id, (err, server) => {
    if (err) {
      return next(err);
    }
    res.send(server);
  });
};

exports.create_user = (req, res, next) => {
  let user = new User({
    username: req.body.username,
    password: req.body.password,
    email: req.body.email
  });

  user.save(err => {
    if (err) {
      return next(err);
    }
    res.send("User created successfully");
  });
};

exports.update_user = (req, res, next) => {
  Server.findByIdAndUpdate(req.params.id, { $set: req.body }, err => {
    if (err) {
      return next(err);
    }
    res.send("User updated successfully");
  });
};

exports.delete_user = (req, res, next) => {
  Server.findByIdAndRemove(req.params.id, err => {
    if (err) {
      return next(err);
    }
    res.send("User deleted successfully");
  });
};
