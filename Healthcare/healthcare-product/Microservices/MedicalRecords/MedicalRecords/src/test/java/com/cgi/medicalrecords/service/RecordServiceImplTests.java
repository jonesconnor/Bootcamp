package com.cgi.medicalrecords.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.cgi.medicalrecords.exception.RecordAlreadyExistsException;
import com.cgi.medicalrecords.exception.RecordNotExistException;
import com.cgi.medicalrecords.model.MedicalRecord;
import com.cgi.medicalrecords.repository.RecordRepo;
import com.cgi.medicalrecords.service.RecordServiceImpl;

public class RecordServiceImplTests {
	
	@InjectMocks
	RecordServiceImpl service;
	
	@Mock
	RecordRepo repo;
	
	MedicalRecord r1;
	
	List<MedicalRecord> records = new ArrayList<MedicalRecord>();
	
	@BeforeEach
	public void setup() {
		// ensure mock objects are properly created and injected
		MockitoAnnotations.openMocks(this);
		// Create new record
		r1 = new MedicalRecord();
		r1.setId(1);
		r1.setDoctorid("10");
		r1.setPatientid("100");
		r1.setNotes("Patient presented with fever. Prescribed Tylenol.");
		r1.setDate(LocalDate.of(2023, 3, 21));
		
		// Create new record
		MedicalRecord r2 = new MedicalRecord();
		r2.setId(2);
		r2.setDoctorid("20");
		r2.setPatientid("200");
		r2.setNotes("Patient presented with broken arm. Advised to go to hospital for further care.");
		r2.setDate(LocalDate.of(2023, 3, 22));

		// Create new record
		MedicalRecord r3 = new MedicalRecord();
		r3.setId(3);
		r3.setDoctorid("30");
		r3.setPatientid("100");
		r3.setNotes("Patient wanted to order pizza. Advised to go to Domino's");
		r3.setDate(LocalDate.of(2023, 3, 23));
		
		records.add(r1);
		records.add(r2);
		records.add(r3);
	}
	
	@Test
	public void addRecordShouldSucceed() throws RecordAlreadyExistsException {
		Mockito.when(repo.save(r1)).thenReturn(r1);
		MedicalRecord resultobj = service.addRecord(r1);
		assertEquals(resultobj.getPatientid(), "100");
		verify(repo,times(1)).findById(r1.getId());
		verify(repo,times(1)).save(r1);
	}
	
	@Test
	public void addRecordShouldThrowRecordAlreadyExistsException() throws RecordAlreadyExistsException {
		Mockito.when(repo.findById(r1.getId())).thenReturn(Optional.of(r1));
		assertThrows(RecordAlreadyExistsException.class,()->service.addRecord(r1));
	}
	
	@Test
	public void getRecordByIdShouldSucceed() throws RecordNotExistException {
		Mockito.when(repo.findById(r1.getId())).thenReturn(Optional.of(r1));
		MedicalRecord result = service.getRecordById(r1.getId());
		assertEquals(r1,result);
		verify(repo,times(1)).findById(r1.getId());
	}
	
	@Test
	public void getRecordByIdShouldThrowRecordNotExistException() throws RecordNotExistException {
		Mockito.when(repo.findById(r1.getId())).thenReturn(Optional.empty());
		assertThrows(RecordNotExistException.class,()->service.getRecordById(r1.getId()));
		
	}
	
	@Test
	public void getRecordsByDoctoridAndPatientidShouldReturnCorrectRecord() {
		Mockito.when(repo.findMedicalRecordByDoctoridAndPatientid(r1.getDoctorid(), r1.getPatientid())).thenReturn(Arrays.asList(records.get(0)));
		List<MedicalRecord> result = service.getRecordsByDoctorIdAndPatientId(r1.getDoctorid(), r1.getPatientid());
		assertEquals(1, result.size());
	    assertEquals(r1, result.get(0));
	    verify(repo, times(1)).findMedicalRecordByDoctoridAndPatientid(r1.getDoctorid(), r1.getPatientid());
	}
	
	@Test
	public void getRecordsByPatientidShouldReturnCorrectRecords() {
		Mockito.when(repo.findMedicalRecordByPatientid(r1.getPatientid())).thenReturn(Arrays.asList(records.get(0), records.get(2)));
		List<MedicalRecord> result = service.getRecordsByPatientId(r1.getPatientid());
		assertEquals(2, result.size());
	    assertEquals(r1, result.get(0));
	    assertEquals(records.get(2), result.get(1));
	    verify(repo,times(1)).findMedicalRecordByPatientid(r1.getPatientid());
	}
	
	@Test
	public void getRecordsByPatientidAndDateShouldReturnCorrectRecords() {
		Mockito.when(repo.findMedicalRecordByPatientidAndDate(r1.getPatientid(), r1.getDate())).thenReturn(Arrays.asList(records.get(0)));
		List<MedicalRecord> result = service.getRecordsByPatientidAndDate(r1.getPatientid(), r1.getDate());
		assertEquals(1, result.size());
	    assertEquals(r1, result.get(0));
		assertEquals(LocalDate.of(2023, 3, 21), result.get(0).getDate());
		verify(repo,times(1)).findMedicalRecordByPatientidAndDate(r1.getPatientid(), r1.getDate());
	}
	
	@Test
	public void getRecordsByDoctoridAndDateShouldReturnCorrectRecords() {
		Mockito.when(repo.findMedicalRecordByDoctoridAndDate(r1.getDoctorid(), r1.getDate())).thenReturn(Arrays.asList(records.get(0)));
		List<MedicalRecord> result = service.getRecordsByDoctoridAndDate(r1.getDoctorid(), r1.getDate());
		assertEquals(1, result.size());
	    assertEquals(r1.getDoctorid(), result.get(0).getDoctorid());
	    assertEquals(LocalDate.of(2023, 3, 21), result.get(0).getDate());
	    verify(repo,times(1)).findMedicalRecordByDoctoridAndDate(r1.getDoctorid(), r1.getDate());   
	}

}
