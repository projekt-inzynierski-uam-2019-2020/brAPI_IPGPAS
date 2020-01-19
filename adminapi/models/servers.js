const mongoose = require("mongoose");

const Schema = mongoose.Schema;

const ServerSchema = new Schema({
  name: { type: String, trim: true, required: true },
  ipAddress: { type: String, trim: true, required: true },
  description: { type: String, trim: true, required: true }
});

module.exports = mongoose.model("Server", ServerSchema);
