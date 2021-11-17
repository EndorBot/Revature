package com.revature.app;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.revature.controller.AccountController;
import com.revature.controller.ClientController;
import com.revature.controller.ExceptionMappingController;

import io.javalin.Javalin;

public class Application {

	public static void main(String[] args) {

		Javalin app = Javalin.create();

		Logger logger = LoggerFactory.getLogger(Application.class);

		app.before(ctx -> { // runs before every request is made
			logger.info(ctx.method() + " request received to the " + ctx.path() + " endpoint");
		});

		ClientController controller = new ClientController();
		controller.registerEndpoint(app);

		ExceptionMappingController exceptionController = new ExceptionMappingController();
		exceptionController.mapExceptions(app);

		AccountController accountController = new AccountController();
		accountController.registerEndpoints(app);

		app.start();
	}

}
