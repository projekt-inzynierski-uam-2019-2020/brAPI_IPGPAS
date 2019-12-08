const Server = require("../models/serverModel");

exports.get_servers = (req, res, next) => {
  Server.find({}, (err, servers) => {
    if (err) {
      return next(err);
    }
    res.send(servers);
  });
};

exports.get_server = (req, res, next) => {
  Server.findById(req.params.id, (err, server) => {
    if (err) {
      return next(err);
    }
    res.send(server);
  });
};

exports.create_server = (req, res, next) => {
  let server = new Server({
    name: req.body.name,
    ipAddress: req.body.ipAddress,
    description: req.body.description
  });

  server.save(err => {
    if (err) {
      return next(err);
    }
    res.send("Server created successfully");
  });
};

exports.update_server = (req, res, next) => {
  Server.findByIdAndUpdate(req.params.id, { $set: req.body }, err => {
    if (err) {
      return next(err);
    }
    res.send("Server updated successfully");
  });
};

exports.delete_server = (req, res, next) => {
  Server.findByIdAndRemove(req.params.id, err => {
    if (err) {
      return next(err);
    }
    res.send("Server deleted successfully");
  });
};
