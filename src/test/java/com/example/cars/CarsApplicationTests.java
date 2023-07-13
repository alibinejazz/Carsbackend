package com.example.cars;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.springframework.http.MediaType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.example.cars.Controller.CarsController;
import com.example.cars.Model.Cars;
import com.example.cars.Repository.CarsRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
class CarsApplicationTests {

	private MockMvc mvc;
	@Mock
	private CarsRepository carsRepo;

	@InjectMocks
	private CarsController carsController;

	private JacksonTester<Cars> jsonCar;

	private JacksonTester<Collection<Cars>> jsonCars;

	@BeforeEach
	public void setUp() {
		JacksonTester.initFields(this, new ObjectMapper());
		mvc = MockMvcBuilders.standaloneSetup(carsController).build();
	}

	@Test
	void contextLoads() {
	}

	@Test
	public void canAddANewHotel() throws Exception {
		Cars car = new Cars(1L,"abc","abc","abc", "abc",100 );
       when(carsRepo.save(car)).thenReturn((car));
		mvc.perform(post("/cars/add")
				.contentType(MediaType.APPLICATION_JSON)
				.content(jsonCar.write(car).getJson()))
				.andExpect(status().isOk());

	}

	@Test
	public void canGetAllHotels() throws Exception {
		Cars car1 = new Cars(1L,"abc","abc","abc", "abc",100 );
		Cars car2 = new Cars(1L,"abc","abc","abc", "abc",100 );

       
		List<Cars> carList = new ArrayList<>();
		carList.add(car1);
		carList.add(car2);
		when(carsRepo.findAll()).thenReturn(carList);
		mvc.perform(get("/cars/getall")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(content().json(jsonCars.write(carList).getJson()));
	}

	@Test
	public void canGetAHotel() throws Exception {
		Cars car1 = new Cars(1L,"abc","abc","abc", "abc",100 );
		when(carsRepo.findById(1L)).thenReturn(Optional.of(car1));
		mvc.perform(MockMvcRequestBuilders.get("/cars/get/1")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.content().json(jsonCar.write(car1).getJson()));
	}
}
