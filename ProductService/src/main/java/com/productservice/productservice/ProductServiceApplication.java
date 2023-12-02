package com.productservice.productservice;

import com.productservice.productservice.Models.Category;
import com.productservice.productservice.Models.Order;
import com.productservice.productservice.Models.Price;
import com.productservice.productservice.Models.Product;
import com.productservice.productservice.inheritancerelations.mappedsuperclass.Mentor;
import com.productservice.productservice.inheritancerelations.mappedsuperclass.MentorRepository;
import com.productservice.productservice.repositories.CategoryRepository;
import com.productservice.productservice.repositories.OrderRepository;
import com.productservice.productservice.repositories.PriceRepository;
import com.productservice.productservice.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.sound.midi.Soundbank;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@SpringBootApplication
public class ProductServiceApplication implements CommandLineRunner {
	private MentorRepository mentorRepository;
	private ProductRepository productRepository;
	private CategoryRepository categoryRepository;
	private final PriceRepository priceRepository;
	private final OrderRepository orderRepository;

	ProductServiceApplication(MentorRepository mentorRepository,
							  PriceRepository priceRepository,
							  OrderRepository orderRepository) {
		this.mentorRepository = mentorRepository;
		this.priceRepository = priceRepository;
		this.orderRepository = orderRepository;
	}
	public static void main(String[] args) {
		SpringApplication.run(ProductServiceApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Mentor mentor = new Mentor();

		mentor.setName("Suraj");
		mentor.setEmail("s.com");
		mentor.setAvgRating(4.9);

		Category category = new Category();
		category.setName("Apple Devices");

		Optional<Category> optCategory = categoryRepository.findById(UUID.fromString(""));

		Product product = new Product();
		product.setTitle("iPhone 15 Pro");
		product.setDescription("Best iphone ever");
		product.setCategory(null);


		//Find all the products with category = "Apple Devices"
		List<Product> products = category.getProducts();
		for(Product prodct : products){
			System.out.println(prodct.getTitle());
		}

		Price price = new Price();
		price.setCurrency("INR");;
		price.setValue(100000);
		Price savedPrice = priceRepository.save(price);

		Category category1 = new Category();
		category1.setName("Apple Devices");
		Category savedCategory1 = categoryRepository.save(category1);

		Product product1 = new Product();
		product1.setTitle("");
		product1.setDescription("");
		product1.setCategory(savedCategory1);
		product1.setPrice(savedPrice);

		Product savedProduct1 =  productRepository.save(product1);

		Order order = new Order();
		orderRepository.save(order);

		Optional<Price> optPrice = priceRepository.findById(UUID.fromString(""));
		if(optPrice.isEmpty()){
			throw new Exception("Price is empty");
		}
		priceRepository.deleteById(UUID.fromString(""));
	}
}
