package vn.techmaster.finalproject.controller.API;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import vn.techmaster.finalproject.model.entity.Employer;
import vn.techmaster.finalproject.model.entity.FileType;
import vn.techmaster.finalproject.repository.EmployerRepo;
import vn.techmaster.finalproject.service.serviceinterface.EmployerService;
import vn.techmaster.finalproject.service.serviceinterface.FileService;
import vn.techmaster.finalproject.service.serviceinterface.HomeService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/employer")
public class TestController {
    @Autowired private EmployerRepo employerRepo;
    @Autowired private EmployerService employerService;

    @Autowired private HomeService homeService;

    @Autowired private FileService fileService;



    @GetMapping("")
    public ResponseEntity<List<Employer>> getAll() {
        return ResponseEntity.ok().body(employerRepo.findAll());
    }

    @GetMapping(value = "/")
    public ResponseEntity<?> testPage() {
        return ResponseEntity.ok(homeService.findAllByTitleContains("","",PageRequest.of(0,5)));
    }

    @PostMapping(value = "/add")
    public ResponseEntity<Employer> addEmployer(@RequestBody Employer employer) {
        employer.setId(UUID.randomUUID().toString());
        employerRepo.save(employer);
        return ResponseEntity.ok().body(employer);
    }

    @PostMapping("/upload-file/{id}")
    public ResponseEntity<?> Uploadfile(@PathVariable String id, @ModelAttribute("file") MultipartFile file) {
        String path = fileService.uploadFile(id, file, FileType.PHOTO);
        return ResponseEntity.ok(path);
    }


    @GetMapping("/test/ceil/{test}")
    public ResponseEntity<?> testCeil (@PathVariable int test){
        double rate = 10.3579/2;
        double cal = Math.ceil(test/5*100)/100;
        System.out.println();
        return ResponseEntity.ok(cal);
    }
//    @GetMapping("/test-email")
//        public ResponseEntity<?> sendMail(){
//        mailServiceUpdate.sendMail("FPT" , "Nguyen Hoa Khiem" , "Java core" , "khiem31200@gmail.com","accept","Test");
//        return ResponseEntity.ok("OK");
//        }

}
