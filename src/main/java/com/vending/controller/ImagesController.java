package com.vending.controller;

import java.io.IOException;
import java.io.InputStream;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import io.swagger.annotations.ApiOperation;

/**
 * Controller class for inserting images.
 * 
 * @author Sindhuja Dasari
 */
@Controller
@RequestMapping(value = "/images/*")
public class ImagesController {
	@GetMapping
	@ApiOperation(value = "Importing images is done calling this operation")
	public @ResponseBody byte[] getImage(HttpServletRequest request) throws IOException {
		String imageUri = request.getRequestURI();

		InputStream in = getClass().getResourceAsStream(imageUri);
		return IOUtils.toByteArray(in);
	}

}