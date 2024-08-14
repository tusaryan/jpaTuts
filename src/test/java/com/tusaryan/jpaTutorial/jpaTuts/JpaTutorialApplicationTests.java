package com.tusaryan.jpaTutorial.jpaTuts;

import com.tusaryan.jpaTutorial.jpaTuts.entities.ProductEntity;
import com.tusaryan.jpaTutorial.jpaTuts.repositories.ProductRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@SpringBootTest
class JpaTutorialApplicationTests {

	@Autowired
	ProductRepository productRepository;

	@Test
	void contextLoads() {
	}

	//creating a product first
	@Test
	void testRepository() {
		ProductEntity productEntity = ProductEntity.builder()
				.sku("nestle234")
				.title("Nestle Chocolate")
				.price(BigDecimal.valueOf(23.45))
				.quantity(4)
				.build();

		ProductEntity savedProductEntity = productRepository.save(productEntity);
		System.out.println(savedProductEntity);
	}

	@Test
	void getRepository() {
//		List<ProductEntity> entities = productRepository.findByTitleOrderByPrice("Pepsi");

//		List<ProductEntity> entities = productRepository.findByCreatedAtAfter(
//				LocalDateTime.of(2025, 1, 1, 0, 0, 0));
//		List<ProductEntity> entities = productRepository.findByQuantityGreaterThanOrPriceLessThan(4, BigDecimal.valueOf(23.45));

		// if we directly pass "Choco" in findByTitleLike("Choco") it will check for exact match.
		// so to match whether "Choco" is contained in/part of the String, we've to use wildcard character % at start and end.
//		List<ProductEntity> entities = productRepository.findByTitleLike("%Choco%");
		//or
		List<ProductEntity> entities = productRepository.findByTitleContainingIgnoreCase("CHOco");
		System.out.println(entities);
	}

	@Test
	void getSingleFromRepository() {
		Optional<ProductEntity> productEntity = productRepository
				.findByTitleAndPrice("Pepsi cooc", BigDecimal.valueOf(14.4));//the entity that you want to get is single actually else you get error
		//we cannot get two or more result for a single query
		productEntity.ifPresent(System.out::println);//this is with help of method inference in java
		//only if present i.e. not null then run this println method with this value
	}
}
