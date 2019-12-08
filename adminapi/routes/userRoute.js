const express = require("express");
const router = express.Router();

const userController = require("../controllers/userController");

router.get("/", userController.get_users);
router.get("/:id", userController.get_user);
router.post("/create", userController.create_user);
router.put("/:id/update", userController.update_user);
router.delete("/:id/delete", userController.delete_user);

module.exports = router;
