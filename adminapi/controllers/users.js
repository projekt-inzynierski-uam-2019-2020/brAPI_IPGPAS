const userModel = require("../models/users");
const bcrypt = require("bcrypt");
const jwt = require("jsonwebtoken");

module.exports = {
  create: function(req, res, next) {
    userModel.create(
      {
        username: req.body.username,
        email: req.body.email,
        password: req.body.password
      },
      function(err, result) {
        if (err) {
          next(err);
        } else {
          res.json({
            status: "Success",
            message: "User added successfully.",
            data: null
          });
        }
      }
    );
  },

  authenticate: function(req, res, next) {
    userModel.findOne({ email: req.body.email }, function(err, userInfo) {
      if (err) {
        next(err);
      } else {
        if (bcrypt.compareSync(req.body.password, userInfo.password)) {
          const token = jwt.sign(
            { id: userInfo._id },
            req.app.get("secretKey"),
            { expiresIn: "1h" }
          );
          res.json({
            status: "Success",
            message: "User found.",
            data: { user: userInfo, token: token }
          });
        } else {
          res.status(404);
        }
      }
    });
  }
};
