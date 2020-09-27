package com.empsystem.employeesystem.escontroller;

import com.empsystem.employeesystem.esmodel.Employee;
import com.empsystem.employeesystem.esservice.ElasticSearchService;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

import static org.elasticsearch.common.xcontent.XContentFactory.jsonBuilder;

@RestController
@RequestMapping("/api/employee")
public class ElasticSearchController {

    @Autowired
    private ElasticSearchService elasticSearchService;

    @GetMapping
    public Iterable<Employee> getAllEmployee() {
        return elasticSearchService.getAllEmployee();
    }

    @PostMapping
    public String saveEmployee(@RequestBody Employee emp) {
        elasticSearchService.createEmployee(emp);
        return "Records saved in the db.";
    }

   /* @Autowired
    Client client;

    @PostMapping("/create")
    public String create(@RequestBody Employee emp) throws IOException {
        IndexResponse response = client.prepareIndex("users", "employee", emp.getUserId())
                .setSource(jsonBuilder()
                        .startObject()
                        .field("name", emp.getName())
                        .field("userSettings", emp.getUserSettings())
                        .endObject()
                )
                .get();
        System.out.println("response id:"+response.getId());
        return response.getResult().toString();
    }*/
}
