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
          httpsPrefix = "https://";
          brapiSuffix = "/brapi/v1/calls";
          address = server.ipAddress;

          if (!address.startsWith(httpsPrefix)) {
            address = "https://" + address;
          }

          if (!address.endsWith(brapiSuffix)) {
            if (address.endsWith("/")) {
              address.trimEnd("/");
            }
            address = address + brapiSuffix;
          }

          try {
            let response = request("GET", address);

            statusList.push({
              id: server._id,
              name: server.name,
              ipAddress: server.ipAddress,
              status: response.statusCode
            });
          } catch (error) {
            statusList.push({
              id: server._id,
              name: server.name,
              ipAddress: server.ipAddress,
              status: 404
            });
          }
        }
      }

      res.json({
        status: statusList
      });
    });
  }
};
