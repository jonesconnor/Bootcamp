package com.cgi.medicalrecords.service;

import java.time.LocalDate;
import java.util.List;

import com.cgi.medicalrecords.exception.RecordAlreadyExistsException;
import com.cgi.medicalrecords.exception.RecordNotExistException;
import com.cgi.medicalrecords.model.MedicalRecord;

public interface RecordService {
	MedicalRecord getRecordById(int id) throws RecordNotExistException;
	MedicalRecord addRecord (MedicalRecord record) throws RecordAlreadyExistsException;
	List<MedicalRecord> getRecordsByDoctorIdAndPatientId(String doctorid,String patientid);
	List<MedicalRecord> getRecordsByPatientId(String patientid);
	List<MedicalRecord> getRecordsByPatientidAndDate(String patientid, LocalDate date);
	List<MedicalRecord> getRecordsByDoctoridAndDate(String doctorid, LocalDate date);
}
