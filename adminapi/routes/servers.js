const express = require("express");
const router = express.Router();
const serverController = require("../controllers/servers");

router.get("/", serverController.getAll);
router.post("/", serverController.create);
router.get("/:id", serverController.getById);
router.put("/:id", serverController.updateById);
router.delete("/:id", serverController.deleteById);

module.exports = router;
