package com.cgi.medicalrecords.service;


import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cgi.medicalrecords.exception.RecordAlreadyExistsException;
import com.cgi.medicalrecords.exception.RecordNotExistException;
import com.cgi.medicalrecords.model.MedicalRecord;
import com.cgi.medicalrecords.repository.RecordRepo;

@Service
public class RecordServiceImpl implements RecordService {
	
	@Autowired
	RecordRepo repo;

	@Override
	public MedicalRecord getRecordById(int id) throws RecordNotExistException {
		Optional<MedicalRecord> optrecord = repo.findById(id);
		if(optrecord.isPresent()) {
			MedicalRecord record = optrecord.get();
			return record;
		}
		else
			throw new RecordNotExistException();
	}


	@Override
	public MedicalRecord addRecord(MedicalRecord record) throws RecordAlreadyExistsException {
		Optional<MedicalRecord> optrecord = repo.findById(record.getId());
		if(optrecord.isPresent()) {
			throw new RecordAlreadyExistsException();
		}
		else {
			return repo.save(record);
		}
	}


	@Override
	public List<MedicalRecord> getRecordsByDoctorIdAndPatientId(String doctorid,String patientid) {
		return repo.findMedicalRecordByDoctoridAndPatientid(doctorid, patientid);
	}
	
	@Override
	public List<MedicalRecord> getRecordsByPatientId(String patientid) {
		return repo.findMedicalRecordByPatientid(patientid);
	}

	@Override
	public List<MedicalRecord> getRecordsByPatientidAndDate(String patientid, LocalDate date) {
		return repo.findMedicalRecordByPatientidAndDate(patientid, date);
	}
	
	@Override
	public List<MedicalRecord> getRecordsByDoctoridAndDate(String doctorid, LocalDate date) {
		return repo.findMedicalRecordByDoctoridAndDate(doctorid, date);
	}

}
