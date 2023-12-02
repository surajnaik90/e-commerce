package com.productservice.productservice;

import com.productservice.productservice.Models.Category;
import com.productservice.productservice.Models.Product;
import com.productservice.productservice.inheritancerelations.mappedsuperclass.Mentor;
import com.productservice.productservice.inheritancerelations.mappedsuperclass.MentorRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ProductServiceApplication implements CommandLineRunner {

	private MentorRepository mentorRepository;

	ProductServiceApplication(MentorRepository mentorRepository) {
		this.mentorRepository = mentorRepository;
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



		Product product = new Product();
		product.setTitle("iPhone 15 Pro");
		product.setDescription("Best iphone ever");
		product.setCategory(null);



	}
}
