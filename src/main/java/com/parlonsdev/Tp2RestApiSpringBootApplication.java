package com.parlonsdev;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import com.parlonsdev.entities.Student;
import com.parlonsdev.repository.StudentRepository;

@SpringBootApplication
@EnableJpaAuditing
public class Tp2RestApiSpringBootApplication {

	public static void main(String[] args) {
		SpringApplication.run(Tp2RestApiSpringBootApplication.class, args);
	}
	
	// SAVE SOME STUDENTS FROM DATABASE
	@Bean
	CommandLineRunner commandLineRunner(StudentRepository studentRepository) {
		return args -> {
			studentRepository.save(new Student("Khadim", "Diop Ndiaye", "diopkhadim@saraya.edu.sn", "+221785421024", "egdfrt-85478-hgdfert-1254", "https://bootdey.com/img/Content/avatar/avatar1.png", true));
			studentRepository.save(new Student("Moussa", "Gaye Sylla", "moussagaye@saraya.edu.sn", "+221774520124", "egdfrt-85478-hgdfert-1452", "https://bootdey.com/img/Content/avatar/avatar2.png", true));
			studentRepository.save(new Student("Penda", "Mbaye", "mbayependa@saraya.edu.sn", "+221771254871", "egdfrt-85478-hglkert-1212", "https://bootdey.com/img/Content/avatar/avatar4.png", false));
			studentRepository.save(new Student("Courra", "Gueye", "gueyecourra@saraya.edu.sn", "+221774521024", "egdfrt-85478-hglmkrt-4521", "https://bootdey.com/img/Content/avatar/avatar3.png", true));
			studentRepository.save(new Student("Soda", "Fall Diouf", "diouffallsoda@saraya.edu.sn", "+221765871247", "egdfrt-85478-kiufert-7854", "https://bootdey.com/img/Content/avatar/avatar8.png", false));
			studentRepository.save(new Student("Sophie", "Sylla", "syllasophie01@saraya.edu.sn", "+221764510245", "egdfrt-85478-hgolert-5401", "https://bootdey.com/img/Content/avatar/avatar7.png", true));
			studentRepository.save(new Student("Amina", "Diop", "diopamina01@saraya.edu.sn", "+221788745214", "egdfrt-85478-hgdflki-1207", "https://bootdey.com/img/Content/avatar/avatar10.png", false));
			studentRepository.save(new Student("Fatimata", "Diop", "diopfatimata@saraya.edu.sn", "+221771245210", "egdfrt-85478-hgdflmt-1084", "https://bootdey.com/img/Content/avatar/avatar6.png", true));
			studentRepository.save(new Student("Fama", "Aidara", "aidarafama@saraya.edu.sn", "+221774521000", "egdfrt-85478-mldfert-1410", "https://bootdey.com/img/Content/avatar/avatar5.png", true));
			studentRepository.save(new Student("Soukeyna", "Bah", "bahsoukeyna@saraya.edu.sn", "+221770452145", "egdfrt-85478-hgdokyt-4007", "https://bootdey.com/img/Content/avatar/avatar9.png", false));
			studentRepository.findAll().forEach(student -> {
				System.out.println(student.getFirstName());
			});
		};
	}

}
