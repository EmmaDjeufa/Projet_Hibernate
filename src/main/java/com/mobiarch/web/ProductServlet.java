package com.mobiarch.web;
import java.io.IOException;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mobiarch.dao.ProductDao;
import com.mobiarch.model.Product;

@WebServlet("/product")
public class ProductServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

	public ProductServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getRequestDispatcher("product/index.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ProductDao productModel = new ProductDao();
		Product product = new Product();
		product.setName(request.getParameter("name"));
		product.setPrice(Double.parseDouble(request.getParameter("price")));
		product.setQuantity(Integer.parseInt(request.getParameter("quantity")));
		boolean status = request.getParameter("status") != null;
		product.setStatus(status);
		if (productModel.create(product)) {
			request.setAttribute("msg", "Successful");
		} else {
			request.setAttribute("msg", "Failed");
		}
		request.getRequestDispatcher("product/index.jsp").forward(request, response);
	}

}