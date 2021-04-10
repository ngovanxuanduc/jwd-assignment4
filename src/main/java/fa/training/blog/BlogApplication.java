package fa.training.blog;

import fa.training.blog.repository.DBContext;
import fa.training.blog.service.HomeService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class BlogApplication {

	public static void main(String[] args) {
		ApplicationContext context =  SpringApplication.run(BlogApplication.class, args);
//		DBContext dbContext = context.getBean(DBContext.class);
//		dbContext.tagRepository.findAllByNameIgnoreCaseIn(new String[]{"taG-1","tag-2"}).forEach(System.out::println);
//		HomeService homeService = context.getBean(HomeService.class);
//		System.out.println(homeService.getHomPubliceContent());

	}

}
