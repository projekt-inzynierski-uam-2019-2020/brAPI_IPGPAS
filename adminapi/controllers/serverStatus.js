const request = require("sync-request");
const serverModel = require("../models/servers");

module.exports = {
  getServerStatus: function(req, res, next) {
    let statusList = [];

    serverModel.find({}, function(err, servers) {
      if (err) {
        next(err);
      } else {
        for (let server of servers) {
          let response = request(
            "GET",
            "https://" + server.ipAddress + "/brapi/v1/calls"
          );

          statusList.push({
            id: server._id,
            name: server.name,
            ipAddress: server.ipAddress,
            status: response.statusCode
          });
        }
      }

      res.json({
        status: statusList
      });
    });
  }
};
