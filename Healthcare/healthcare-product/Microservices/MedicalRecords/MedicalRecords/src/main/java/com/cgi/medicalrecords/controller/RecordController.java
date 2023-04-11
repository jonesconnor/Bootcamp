package com.cgi.medicalrecords.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cgi.medicalrecords.exception.RecordAlreadyExistsException;
import com.cgi.medicalrecords.exception.RecordNotExistException;
import com.cgi.medicalrecords.model.MedicalRecord;
import com.cgi.medicalrecords.service.RecordService;
import com.cgi.medicalrecords.service.RecordServiceImpl;

@RestController
@RequestMapping("/medicalrecords")
@CrossOrigin
public class RecordController {
	
	@Autowired
	RecordService service;
	
	@PostMapping("/add")
	public ResponseEntity<?> addRecord(@RequestBody MedicalRecord record){
		try {
			MedicalRecord recordobj = service.addRecord(record);
			return new ResponseEntity<MedicalRecord>(recordobj,HttpStatus.CREATED);
		} catch (RecordAlreadyExistsException e) {
			return new ResponseEntity<String>("Record id already exists",HttpStatus.CONFLICT);
		}
		
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> getRecord(@PathVariable("id") String id) {
		try {
			MedicalRecord recordobj = service.getRecordById(Integer.parseInt(id));
			return new ResponseEntity<MedicalRecord>(recordobj,HttpStatus.OK);
		} catch(RecordNotExistException e) {
			return new ResponseEntity<String>("Record does not exist",HttpStatus.CONFLICT);
		}
	}
	
	@GetMapping("/patient/{patientid}")
	public ResponseEntity<?> getRecordsByPatientId(@PathVariable("patientid") String patientid) {
		List<MedicalRecord> records = service.getRecordsByPatientId(patientid);
		return new ResponseEntity<List<MedicalRecord>>(records,HttpStatus.OK);
	}
	
	@GetMapping("/doctorpatient/{doctorid}/{patientid}")
	public ResponseEntity<?> getRecordsByDoctorIdAndPatientId(@PathVariable("patientid") String patientid, @PathVariable("doctorid") String doctorid) {
		List<MedicalRecord> records = service.getRecordsByDoctorIdAndPatientId(doctorid, patientid);
		return new ResponseEntity<List<MedicalRecord>>(records,HttpStatus.OK);
	}
	
	@GetMapping("/patientdate/{patientid}/{date}")
	public ResponseEntity<?> getRecordsByPatientIdAndDate(@PathVariable("patientid") String patientid, @PathVariable("date") String date) {
		List<MedicalRecord> records = service.getRecordsByPatientidAndDate(patientid, LocalDate.parse(date));
		return new ResponseEntity<List<MedicalRecord>>(records,HttpStatus.OK);
	}
	
	@GetMapping("/doctordate/{doctorid}/{date}")
	public ResponseEntity<?> getRecordsByDoctorIdAndDate(@PathVariable("doctorid") String doctorid, @PathVariable("date") String date) {
		List<MedicalRecord> records = service.getRecordsByDoctoridAndDate(doctorid, LocalDate.parse(date));
		return new ResponseEntity<List<MedicalRecord>>(records,HttpStatus.OK);
	}

}
