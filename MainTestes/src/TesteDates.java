import java.sql.Timestamp;
import java.util.Date;

public class TesteDates {

  
  public static void main(String[] args) {
    
    
    
    
    Timestamp dataDeHoje = new Timestamp(System.currentTimeMillis());
    Date d = new Date();
    int n= 0;
    while(n < 100000000) {
      n++;
    }
    
    Timestamp dataDeHoje3 = new Timestamp(System.currentTimeMillis());

    
   System.out.println(dataDeHoje);
   System.out.println(dataDeHoje.getMinutes());
   System.out.println(dataDeHoje.getSeconds());
   System.out.println(dataDeHoje.getYear());
   System.out.println(d.getSeconds());
   System.out.println(d.getTime());
   System.out.println(dataDeHoje3);
   System.out.println(dataDeHoje.compareTo(dataDeHoje3));
   

   Long novo = (dataDeHoje3.getTime() - dataDeHoje.getTime());
   System.out.println(novo);
    
  }
}
