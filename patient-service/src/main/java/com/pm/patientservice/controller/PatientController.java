package com.pm.patientservice.controller;

import com.pm.patientservice.dto.PatientRequestDTO;
import com.pm.patientservice.dto.PatientResponseDTO;
import com.pm.patientservice.dto.validators.CreatePatientValidationGroup;
import com.pm.patientservice.service.PatientService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.groups.Default;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/patients")
@Tag(name = "Patient", description = "API for managing Patient")
public class PatientController {

    private final PatientService patientService;
    //constructor injection make sure is final

    private PatientController(PatientService patientService){
        this.patientService = patientService;
    }
    @GetMapping
    @Operation(summary = " get Patients")
    public ResponseEntity<List<PatientResponseDTO>> getPatients(){
        List<PatientResponseDTO> patients = patientService.getPatients();
        return ResponseEntity.ok().body(patients);
    }
    @PostMapping
    @Operation(summary = "  create Patient")
    public ResponseEntity<PatientResponseDTO>  createPatient(@Validated({Default.class, CreatePatientValidationGroup.class}) @RequestBody PatientRequestDTO patientRequestDTO){
        return ResponseEntity.ok().body(patientService.createPatient(patientRequestDTO));
    }

    @PutMapping("/{id}")
    @Operation(summary = " update Patient")
    public ResponseEntity<PatientResponseDTO> updatePatient(@PathVariable UUID id,  @Validated({Default.class})@RequestBody PatientRequestDTO patientRequestDTO){
        return ResponseEntity.ok().body(patientService.updatePatient(id,patientRequestDTO));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = " delete a Patient")
    public ResponseEntity<Void> deletePatient(@PathVariable UUID id){
        patientService.deletePatient(id);
        return ResponseEntity.noContent().build();
    }


}
