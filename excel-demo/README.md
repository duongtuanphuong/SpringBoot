# UploadExcel
### Cài đặt
Đầu tiên cần thêm dependency poi-ooxml để thao tác với excel.

_pom.xml_
```
		<dependency>
			<groupId>org.apache.poi</groupId>
			<artifactId>poi-ooxml</artifactId>
			<version>5.0.0</version>
		</dependency>
```

### Tạo clas ExcelUtils
Hàm ExcelUtils có tác dụng đọc file Excel theo kiểu từng dòng rồi split ra thành từng mảng để dùng

```
public class ExcelUltils {

	public static List<Customer> parseExcelFile(InputStream is) {
		try {
    		Workbook workbook = new XSSFWorkbook(is);
     
    		Sheet sheet = workbook.getSheet("Customers");
    		Iterator<Row> rows = sheet.iterator();
    		
    		List<Customer> lstCustomers = new ArrayList<Customer>();
    		
    		int rowNumber = 0;
    		while (rows.hasNext()) {
    			Row currentRow = rows.next();
    			
    			// skip header
    			if(rowNumber == 0) {
    				rowNumber++;
    				continue;
    			}
    			
    			Iterator<Cell> cellsInRow = currentRow.iterator();

    			Customer cust = new Customer();
    			
    			int cellIndex = 0;
    			while (cellsInRow.hasNext()) {
    				Cell currentCell = cellsInRow.next();
    				
    				if(cellIndex==0) { // ID
    					cust.setId((long) currentCell.getNumericCellValue());
    				} else if(cellIndex==1) { // Name
    					cust.setName(currentCell.getStringCellValue());
    				} else if(cellIndex==2) { // Address
    					cust.setAddress(currentCell.getStringCellValue());
    				} else if(cellIndex==3) { // Age
    					cust.setAge((int) currentCell.getNumericCellValue());
    				}
    				
    				cellIndex++;
    			}
    			
    			lstCustomers.add(cust);
    		}
    	
    		// Close WorkBook
    		workbook.close();
    		
    		return lstCustomers;
        } catch (IOException e) {
        	throw new RuntimeException("FAIL! -> message = " + e.getMessage());
        }
	}
}
```

### Hàm FileService
Hàm FileService có tác dụng tạo logic để lưu dữ liệu đã split ra từ file excel sang bên repository
```
@Service
public class FileService {
    @Autowired
    CustomerRepository customerRepository;

    public void store(MultipartFile file) {
        try {
            List<Customer> lstCustomers = ExcelUltils.parseExcelFile(file.getInputStream());
            // Save Customers to DataBase
            customerRepository.saveAll(lstCustomers);
        } catch (IOException e) {
            throw new RuntimeException("FAIL! -> message = " + e.getMessage());
        }
    }

}

```

### Controller
Hàm controller tạo api để giao tiếp với người dùng
```
@Controller
public class UploadController {
    @Autowired
    FileService fileService;
    
    @GetMapping("/")
    public String getHome(){
        return "home";
    }

    @PostMapping("/")
    public String uploadMultipartFile(@RequestParam("uploadfile") MultipartFile file, Model model) {
		try {
			fileService.store(file);
			model.addAttribute("message", "File uploaded successfully!");
		} catch (Exception e) {
			model.addAttribute("message", "Fail! -> uploaded filename: " + file.getOriginalFilename());
		}
        return "home";
    }
}
```

