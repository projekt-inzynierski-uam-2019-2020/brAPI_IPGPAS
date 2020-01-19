const serverModel = require("../models/servers");

module.exports = {
  getById: function(req, res, next) {
    console.log(req.body);
    serverModel.findById(req.params.id, function(err, serverInfo) {
      if (err) {
        next(err);
      } else {
        res.send(serverInfo);
      }
    });
  },

  getAll: function(req, res, next) {
    serverModel.find({}, function(err, serverList) {
      if (err) {
        next(err);
      } else {
        res.send(serverList);
      }
    });
  },

  updateById: function(req, res, next) {
    serverModel.findByIdAndUpdate(
      req.params.id,
      { name: req.body.name },
      function(err, serverInfo) {
        if (err) {
          next(err);
        } else {
          res.status(200);
        }
      }
    );
  },

  deleteById: function(req, res, next) {
    serverModel.findByIdAndRemove(req.params.id, function(err, serverInfo) {
      if (err) {
        next(err);
      } else {
        res.status(200);
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
        if (err) {
          next(err);
        } else {
          res.status(200);
        }
      }
    );
  }
};
