package com.cgi.medicalrecords.repository;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cgi.medicalrecords.model.MedicalRecord;

@Repository
public interface RecordRepo extends JpaRepository<MedicalRecord,Integer>{

	List<MedicalRecord> findMedicalRecordByPatientid(String id);
	List<MedicalRecord> findMedicalRecordByDoctoridAndPatientid(String doctorid,String patientid);
	List<MedicalRecord> findMedicalRecordByPatientidAndDate(String patientid, LocalDate date);
	List<MedicalRecord> findMedicalRecordByDoctoridAndDate(String doctorid, LocalDate date);
}
