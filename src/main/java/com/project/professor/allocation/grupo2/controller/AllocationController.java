package com.project.professor.allocation.grupo2.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.project.professor.allocation.grupo2.entity.Allocation;
import com.project.professor.allocation.grupo2.service.AllocationService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping(path = "/allocations")
public class AllocationController {

	private final AllocationService allocationService;

	public AllocationController(AllocationService allocationService) {
		super();
		this.allocationService = allocationService;
	}
	
	@ApiOperation(value = "Find all allocations")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK")
    })
	@ResponseStatus(HttpStatus.OK)
	@GetMapping( produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Allocation>> findAll() {

		List<Allocation> allocations = allocationService.findAll();
		return new ResponseEntity<>(allocations, HttpStatus.OK);
	}
	
	@ApiOperation(value = "Find an allocation by id")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 404, message = "Not Found")
    })
	@ResponseStatus(HttpStatus.OK)
	@GetMapping(path = "/{allocation_id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Allocation> findById(@PathVariable(name = "allocation_id") Long id) {

		Allocation allocation = allocationService.findById(id);

		if (allocation != null) {
			return new ResponseEntity<>(allocation,HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);	
		}
	}
	
	@ApiOperation(value = "Find allocations by course")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 400, message = "Bad Request")
    })
	@ResponseStatus(HttpStatus.OK)
	@GetMapping(path = "/course/{course_id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity <List<Allocation>> findByCourseId(@PathVariable(name = "course_id") Long id) {

		List <Allocation> allocation = allocationService.findByCourseId(id);
		
		if (allocation != null) {
			return new ResponseEntity<>(allocation, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);		
		}
	}
	
	@ApiOperation(value = "Find allocations by professor")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 400, message = "Bad Request")
    })
	@ResponseStatus(HttpStatus.OK)
	@GetMapping(path = "/professor/{professor_id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity <List<Allocation>> findByProfessorId(@PathVariable(name = "professor_id") Long id) {

		List <Allocation> allocation = allocationService.findByProfessorId(id);
		
		if (allocation != null) {
			return new ResponseEntity<>(allocation, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);		
		}
	}
	
	@ApiOperation(value = "Create an allocation")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Created"),
            @ApiResponse(code = 400, message = "Bad Request")
    })
	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Allocation> create(@RequestBody Allocation allocation) {
		
		try {
			Allocation newAllocation = allocationService.create(allocation);
			return new ResponseEntity<>(newAllocation, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}
	
	
	@ApiOperation(value = "Update an allocation")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "OK!"),
			@ApiResponse(code = 400, message = "Bad Request"),
			@ApiResponse(code = 404, message = "Not Found")
	})
	@ResponseStatus(HttpStatus.OK)
	@PutMapping(path = "/{allocation_id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Allocation> update(@PathVariable(name = "allocation_id") Long id,@RequestBody Allocation allocation) {
		
		allocation.setId(id);
		
		try {
			Allocation newAllocation = allocationService.update(allocation);
			if(newAllocation != null) {
				return new ResponseEntity<>(newAllocation, HttpStatus.OK);
			} else {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
			
		} catch (Exception e){
			return new ResponseEntity<>( HttpStatus.BAD_REQUEST);
		}	
	}
	
	@ApiOperation(value = "Delete an allocation")
    @ApiResponses({
            @ApiResponse(code = 204, message = "No Content")
    })
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@DeleteMapping(path = "/{allocation_id}")
	public ResponseEntity<Void> deleteById (@PathVariable (name = "allocation_id") Long id) {
		
		allocationService.deleteById(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		
	}
	
	@ApiOperation(value = "Delete all allocations")
    @ApiResponses({
            @ApiResponse(code = 204, message = "No Content")
    })
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@DeleteMapping
	public ResponseEntity<Void> deleteAll() {
		
		allocationService.deleteAll();
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
}
