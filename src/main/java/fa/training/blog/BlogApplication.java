package fa.training.blog;

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
//		tinhNgay();
		
	}
	
//	private static void tinhNgay() {
//		DateFormat format = new SimpleDateFormat("dd/MM/yyyy");
//		Date dateCurrent = new Date();
//		Date oldDate;
//		try {
////			oldDate = format.parse("01/01/1998");
//			oldDate = format.parse("11/04/2021");
//			Date cal = new Date(dateCurrent.getTime() - oldDate.getTime());
//			System.out.println("Now: "+  format.format(dateCurrent));
//			System.out.println("OLD: "+  format.format(oldDate));
////			System.out.println("Tinh: "+  format.format(cal));
//			System.out.println("Tinh: "+getDateDiff(oldDate, dateCurrent, TimeUnit.MINUTES));
//			System.out.println("Tinh day: "+getDateDiff(oldDate, dateCurrent, TimeUnit.DAYS));
//			
//		} catch (ParseException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//	}
//	public static long getDateDiff(Date date1, Date date2, TimeUnit timeUnit) {
//	    long diffInMillies = date2.getTime() - date1.getTime();
//	    return timeUnit.convert(diffInMillies,TimeUnit.MILLISECONDS);
//	}
}
