const express = require("express");
const router = express.Router();

const serverController = require("../controllers/serverController");

router.get("/", serverController.get_servers);
router.get("/:id", serverController.get_server);
router.post("/create", serverController.create_server);
router.put("/:id/update", serverController.update_server);
router.delete("/:id/delete", serverController.delete_server);

module.exports = router;
