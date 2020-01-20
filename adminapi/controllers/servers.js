const serverModel = require("../models/servers");

module.exports = {
  getById: function(req, res, next) {
    console.log(req.body);
    serverModel.findById(req.params.id, function(err, serverInfo) {
      if (err) {
        next(err);
      } else {
        res.send(serverInfo)
      }
    });
  },

  getAll: function(req, res, next) {
    let serversList = [];

    serverModel.find({}, function(err, servers) {
      if (err) {
        next(err);
      } else {
        for (let server of servers) {
          serversList.push({
            id: server._id,
            name: server.name,
            ipAddress: server.ipAddress,
            description: server.description
          });
        }
        res.json({
          status: "Success",
          message: "Server list found.",
          servers: serversList 
        });
      }
    });
  },

  updateById: function(req, res, next) {
    serverModel.findByIdAndUpdate(
      req.params.id,
      {
        name: req.body.name,
        ipAddress: req.body.ipAddress,
        description: req.body.description
      },
      function(err, serverInfo) {
        if (err) next(err);
        else {
          res.json({
            status: "Success",
            message: "Server updated successfully.",
            data: null
          });
        }
      }
    );
  },

  deleteById: function(req, res, next) {
    serverModel.findByIdAndRemove(req.params.id, function(err, serverInfo) {
      if (err) next(err);
      else {
        res.json({
          status: "Success",
          message: "Server deleted successfully.",
          data: null
        });
      }
    });
  },

  create: function(req, res, next) {
    serverModel.create(
      {
        name: req.body.name,
        ipAddress: req.body.ipAddress,
        description: req.body.description
      },
      function(err, result) {
        if (err) next(err);
        else
          res.json({
            status: "Success",
            message: "Server added successfully.",
            data: null
          });
      }
    );
  }
};
