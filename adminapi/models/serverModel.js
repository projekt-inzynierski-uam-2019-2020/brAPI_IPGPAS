const mongoose = require("mongoose");
const Schema = mongoose.Schema;

let ServerSchema = new Schema({
  name: { type: String, required: true },
  ipAddress: { type: String, required: true },
  description: {type: String, required: true}
});

module.exports = mongoose.model("Server", ServerSchema);
