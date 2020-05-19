package com.empsystem.employeesystem.controller;

import com.empsystem.employeesystem.Exception.NotFoundException;
import com.empsystem.employeesystem.model.Users;
import com.empsystem.employeesystem.repo.UserRepository;
import com.empsystem.employeesystem.services.UserService;
import com.empsystem.employeesystem.specification.SearchOperation;
import com.empsystem.employeesystem.specification.UsersSpecificationsBuilder;
import com.google.common.base.Joiner;
import org.hibernate.annotations.AnyMetaDef;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.StringJoiner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@RestController
    @RequestMapping("/api/users")
public class UserController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    private UserService userService;

    @GetMapping
    public List<Users> list(){
        return userService.getAllUsers();
    }

    @GetMapping
    @RequestMapping("{empid}")
    public Optional<Users> get(@PathVariable Long empid){
        return userService.getUserById(empid);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestBody final Users users){
        userService.addUser(users);
    }


    @RequestMapping(value = "{empid}", method = RequestMethod.PUT)
    public Users update(@PathVariable Long empid, @RequestBody Users users){
        return userService.updateUser(empid, users);
    }

    @DeleteMapping("{empid}")
    public String deleteStudent(@PathVariable Long empid) {
        return userRepository.findById(empid).map(users -> { userRepository.delete(users);
                return "Delete Successfully!";}).orElseThrow(() -> new
                        NotFoundException("Student not found with id " + empid));
    }

    /*@GetMapping("/search")
    //@RequestMapping("{username}")
    public List<Users> getByFirstAndLastName (@RequestParam String fname, @RequestParam String lname){
        return userRepository.findByFnameAndLname(fname,lname);
    }*/

   /*@GetMapping("/search")
    public List<Users> getByFirstName (@RequestParam String fname){
       return  userRepository.findByFirstName(fname);
   }*/

  /* @GetMapping("/search")
    public Page<Users> getByWord (@RequestParam Optional<String> fname,
                                  @RequestParam Optional<Integer> page,
                                  @RequestParam Optional<String> sortBy){
       return userRepository.findByWord(fname.orElse("_"),
               PageRequest.of(page.orElse(0), 5, Sort.Direction.ASC,sortBy.orElse("empid")));
   }*/

   @GetMapping("/www")
    @ResponseBody
    public List<Users> findAllBySpecification(@RequestParam(value = "search") String search){
       UsersSpecificationsBuilder builder = new UsersSpecificationsBuilder();
       String operationSetExper = Joiner.on("|").join(SearchOperation.SIMPLE_OPERATION_SET);
       Pattern pattern = Pattern.compile(
               "(\\w+?)(" + operationSetExper + ")(\\p{Punct}?)(\\w+?)(\\p{Punct}?),");
       Matcher matcher = pattern.matcher(search + ",");
       while (matcher.find()) {
           builder.with(matcher.group(1),
                        matcher.group(2),
                        matcher.group(4),
                        matcher.group(3),
                        matcher.group(5));
       }
       Specification<Users> spec = builder.build();
       return userRepository.findAll(spec);
   }
}
