import java.awt.datatransfer.StringSelection;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;


public class Run {
        //this method converts csv file to a list of violations
    private static List<Violation> getViolationsFromFile (String filePath) {
            String lineSplitter = " ";
            String fieldSplitter = ",";
            SimpleDateFormat parser = new SimpleDateFormat("yyyy-MM-dd H:mm");
            boolean headerLine = true;

            List<Violation> violationList = new ArrayList<>();
            try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
                while ((lineSplitter = br.readLine()) != null) {
                    if (headerLine){
                        headerLine = false;
                        continue;
                    }
                    String[] co = lineSplitter.split(fieldSplitter);

                    Violation nViolation = new Violation(
                            (co[0] == null || co[0].isEmpty()) ? null : Integer.parseInt(co[0]),
                            (co[0] == null || co[1].isEmpty()) ? null : Integer.parseInt(co[1]),
                            (co[0] == null || co[2].isEmpty()) ? null : co[2],
                            (co[0] == null || co[3].isEmpty()) ? null : parser.parse(co[3]),
                            (co[0] == null || co[4].isEmpty()) ? null : parser.parse(co[4]),
                            (co[0] == null || co[5].isEmpty()) ? null : co[5]);
                    violationList.add(nViolation);
                }
            } catch (IOException | ParseException e) {
                System.out.println("File read failed! Exception: " + e.getMessage() );
            }
            return violationList;
        }
        //this method finds the unique categories and the number of times they've occured
    private static HashMap<String,Integer> getUniqueCategoryAndCount (List<Violation> violations){
            HashMap<String, Integer> hashMap = new HashMap<>();
            for (Violation violation : violations) {
                if (hashMap.containsKey(violation.getViolationCategory())){
                   hashMap.replace(
                           violation.getViolationCategory(),
                           hashMap.get(violation.getViolationCategory()) + 1);
                } else{

                    hashMap.put(violation.getViolationCategory(), 1);
                }
            }

            return hashMap;
        }
        //this method finds the min and max violation date in each category
    private static HashMap<String,DateTuple> getCategoryMinMaxViolationDate (List<Violation> violations) {
        HashMap<String, DateTuple> hashMap = new HashMap<>();
        Date maxDate, minDate;
        for (Violation v : violations) {
            if (hashMap.containsKey(v.getViolationCategory())){
                DateTuple existingTuple = hashMap.get(v.getViolationCategory());
                minDate = existingTuple.getMinDate();
                maxDate = existingTuple.getMaxDate();

                if(existingTuple.getMaxDate().compareTo(v.getViolationDate()) < 0){
                    maxDate =  v.getViolationDate();
                }
                if(existingTuple.getMinDate().compareTo(v.getViolationDate()) > 0){
                    minDate =  v.getViolationDate();
                }

                if(maxDate != null || minDate != null) {
                    hashMap.replace(v.getViolationCategory(), new DateTuple(minDate, maxDate));
                }

            } else{

                hashMap.put(v.getViolationCategory(), new DateTuple(v.getViolationDate(), v.getViolationDate()));
            }
        }

        return hashMap;
    }
    public static void main(String[] args) {

        String file = "D:\\csa.csv";
        List<Violation> violations = getViolationsFromFile(file);
//        violations.get(0).getViolationCategory();

//        System.out.println(getUniqueCategoryAndCount(violations));

        System.out.println("Categories and their count:");
        getUniqueCategoryAndCount(violations).entrySet().forEach(entry -> {
            System.out.println(entry.getKey()+  "  ===>  " +entry.getValue());
        });

//        System.out.println(getCategoryMinMaxViolationDate(violations));
        System.out.println("   ");
        System.out.println("Categories and max-min date:");
        getCategoryMinMaxViolationDate(violations).entrySet().forEach(entry -> {
            System.out.println(entry.getKey()+  " ||| MAX DATE ==>>  " +entry.getValue().getMaxDate()+
                    "  |||  MIN DATE  ==>>" +entry.getValue().getMinDate());

        });
    }



}

